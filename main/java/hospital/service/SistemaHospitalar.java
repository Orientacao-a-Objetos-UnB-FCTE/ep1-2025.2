package hospital.service;

import hospital.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class SistemaHospitalar {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Quarto> quartos = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();
    private List<Internacao> internacoes = new ArrayList<>();

    private final String REL_DIR = "relatorios";

    public SistemaHospitalar(int totalQuartos) {
        for (int i = 1; i <= totalQuartos; i++) quartos.add(new Quarto(i));
        criarDirRelatorios();
    }

    public SistemaHospitalar() {
        this(10);
    }

    // ------------------- CADASTROS -------------------
    public void cadastrarPaciente(Paciente p) { pacientes.add(p); }
    public void cadastrarMedico(Medico m) { medicos.add(m); }

    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getMedicos() { return medicos; }
    public List<Quarto> getQuartos() { return quartos; }
    public List<Consulta> getConsultas() { return consultas; }
    public List<Internacao> getInternacoes() { return internacoes; }

    // ------------------- CONSULTAS -------------------
    public boolean agendarConsulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String local) {
        // verifica conflito médico horário e local/hora
        for (Consulta c : consultas) {
            if (c.getMedico().equals(medico) && c.getDataHora().equals(dataHora)) {
                System.out.println("Erro: médico já tem consulta nesse horário.");
                return false;
            }
            if (c.getLocal().equals(local) && c.getDataHora().equals(dataHora)) {
                System.out.println("Erro: local ocupado nesse horário.");
                return false;
            }
        }

        double valor = medico.getCustoConsulta();
        // desconto simples se paciente for especial
        if (paciente instanceof PacienteEspecial) {
            PacienteEspecial pe = (PacienteEspecial) paciente;
            if (pe.getPlano() != null) {
                valor = valor * (1 - pe.getPlano().getDescontoPadrao());
                // desconto extra p/ 60+
                if (paciente.getIdade() >= 60) valor = valor * 0.9;
            }
        }

        Consulta nova = new Consulta(paciente, medico, dataHora, local, valor);
        consultas.add(nova);
        paciente.adicionarConsulta(nova);
        medico.adicionarConsulta(nova);
        return true;
    }

    // listar consultas agendadas (opção para menu)
    public List<Consulta> listarConsultasAgendadas() {
        return consultas.stream()
                .filter(c -> c.getStatus() == Consulta.Status.AGENDADA)
                .sorted(Comparator.comparing(Consulta::getDataHora))
                .collect(Collectors.toList());
    }

    // registrar diagnostico/prescricao
    public boolean registrarDiagnostico(Consulta consulta, String diagnostico, String prescricao) {
        if (consulta == null) return false;
        consulta.registrarDiagnostico(diagnostico, prescricao);
        return true;
    }

    public boolean atualizarStatusConsulta(Consulta consulta, Consulta.Status status) {
        if (consulta == null) return false;
        consulta.setStatus(status);
        return true;
    }

    // buscar consulta por índice (usado no menu)
    public Consulta buscarConsultaPorIndex(int idx) {
        if (idx < 0 || idx >= consultas.size()) return null;
        return consultas.get(idx);
    }

    // ------------------- INTERNAÇÕES -------------------
    public boolean registrarInternacao(Paciente paciente, Medico medico, Quarto quarto, double custo) {
        if (quarto == null || quarto.isOcupado()) return false;
        LocalDateTime agora = LocalDateTime.now();
        Internacao i = new Internacao(paciente, medico, agora, quarto, custo);
        internacoes.add(i);
        paciente.adicionarInternacao(i);
        quarto.setOcupado(true);
        return true;
    }

    public boolean liberarInternacao(Internacao i) {
        if (i == null) return false;
        i.setDataSaida(LocalDateTime.now());
        i.getQuarto().setOcupado(false);
        return true;
    }

    // ------------------- RELATÓRIOS CSV -------------------
    private void criarDirRelatorios() {
        File dir = new File(REL_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    public String gerarRelatorioPacientesCSV() {
        criarDirRelatorios();
        String nome = REL_DIR + File.separator + "relatorio_pacientes_" + timestamp() + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nome))) {
            bw.write("cpf,nome,idade,consultas,internacoes\n");
            for (Paciente p : pacientes) {
                bw.write(String.format("\"%s\",\"%s\",%d,%d,%d\n",
                    p.getDocumento(),
                    p.getNome(),
                    p.getIdade(),
                    p.getConsultas().size(),
                    p.getInternacoes().size()));
            }
            System.out.println("Relatório de pacientes gerado: " + nome);
            return nome;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String gerarRelatorioMedicosCSV() {
        criarDirRelatorios();
        String nome = REL_DIR + File.separator + "relatorio_medicos_" + timestamp() + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nome))) {
            bw.write("crm,nome,especialidade,consultas_realizadas\n");
            for (Medico m : medicos) {
                bw.write(String.format("\"%s\",\"%s\",\"%s\",%d\n",
                    m.getDocumento(),
                    m.getNome(),
                    m.getEspecialidade(),
                    m.getConsultas().size()));
            }
            System.out.println("Relatório de médicos gerado: " + nome);
            return nome;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String gerarRelatorioInternacoesCSV() {
        criarDirRelatorios();
        String nome = REL_DIR + File.separator + "relatorio_internacoes_" + timestamp() + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nome))) {
            bw.write("paciente,cpf,medico,entrada,saida,quarto,custo\n");
            DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            for (Internacao it : internacoes) {
                bw.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d,%.2f\n",
                    it.getPaciente().getNome(),
                    it.getPaciente().getDocumento(),
                    it.getMedicoResponsavel().getNome(),
                    it.getDataEntrada().format(f),
                    it.getDataSaida() == null ? "" : it.getDataSaida().format(f),
                    it.getQuarto().getNumero(),
                    it.getCusto()));
            }
            System.out.println("Relatório de internações gerado: " + nome);
            return nome;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String gerarEstatisticasCSV() {
        criarDirRelatorios();
        String nome = REL_DIR + File.separator + "estatisticas_" + timestamp() + ".csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nome))) {
            bw.write("especialidade,consultas\n");
            Map<String, Long> byEsp = consultas.stream()
                    .collect(Collectors.groupingBy(c -> c.getMedico().getEspecialidade(), Collectors.counting()));
            for (Map.Entry<String, Long> e : byEsp.entrySet()) {
                bw.write(String.format("\"%s\",%d\n", e.getKey(), e.getValue()));
            }
            System.out.println("Estatísticas geradas: " + nome);
            return nome;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------- RELATÓRIOS NO CONSOLE -------------------
    public void relatorioPacientesConsole() {
        System.out.println("---- PACIENTES ----");
        for (Paciente p : pacientes) {
            System.out.printf("%s (CPF: %s, Idade: %d) | Consultas: %d | Internações: %d\n",
                p.getNome(), p.getDocumento(), p.getIdade(), p.getConsultas().size(), p.getInternacoes().size());
            // imprime histórico breve
            for (Consulta c : p.getConsultas()) {
                System.out.println("   - " + c.toString() + (c.getDiagnostico().isEmpty() ? "" : " | DX: " + c.getDiagnostico()));
            }
        }
    }

    public void relatorioMedicosConsole() {
        System.out.println("---- MEDICOS ----");
        for (Medico m : medicos) {
            System.out.printf("%s | CRM: %s | Especialidade: %s | Consultas: %d\n",
                m.getNome(), m.getDocumento(), m.getEspecialidade(), m.getConsultas().size());
        }
    }

    public void relatorioInternacoesConsole() {
        System.out.println("---- INTERNACOES ATUAIS ----");
        for (Internacao i : internacoes) {
            if (i.getDataSaida() == null) {
                System.out.println(i.toString());
            }
        }
    }

    public void relatorioEstatisticasConsole() {
        System.out.println("---- ESTATÍSTICAS ----");
        Map<String, Long> byEsp = consultas.stream()
                .collect(Collectors.groupingBy(c -> c.getMedico().getEspecialidade(), Collectors.counting()));
        for (Map.Entry<String, Long> e : byEsp.entrySet()) {
            System.out.println("Especialidade: " + e.getKey() + " | Consultas: " + e.getValue());
        }
    }
}
