package br.com.seuhospital.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hospital {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<PlanoDeSaude> planosDeSaude;
    private List<Consulta> consultas;
    private List<Internacao> internacoes;
    private Map<String, Boolean> quartosOcupacao; // Quarto ID -> Ocupado/Livre)
    private Triagem triagem;

    public Hospital() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.planosDeSaude = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.internacoes = new ArrayList<>();
        
        this.quartosOcupacao = new HashMap<>();
        // Inicializa alguns quartos para exemplo
        for (int i = 1; i <= 10; i++) {
            quartosOcupacao.put("Q" + i, false);
        }
        this.triagem = new Triagem(); // Inicializa o sistema de triagem
    }

    // Getters
    public List<Paciente> getPacientesCadastrados() {
        return pacientes;
    }

    public List<Medico> getMedicosCadastrados() {
        return medicos;
    }

    public List<PlanoDeSaude> getPlanosDeSaude() {
        return planosDeSaude;
    }

    public List<Consulta> getHistoricoConsultas() {
        return consultas;
    }

    public List<Internacao> getHistoricoInternacoes() {
        return internacoes;
    }

    public Triagem getTriagem() {
        return triagem;
    }

    // MÉTODO ADICIONADO
    public Map<String, Boolean> getQuartosOcupacao() {
        return this.quartosOcupacao;
    }

    // Métodos de Cadastro
    public void cadastrarPaciente(Paciente paciente) {
        if (pacientes.stream().noneMatch(p -> p.getCpf().equals(paciente.getCpf()))) {
            this.pacientes.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Erro: Paciente com CPF " + paciente.getCpf() + " já cadastrado.");
        }
    }

    public void cadastrarMedico(Medico medico) {
        if (medicos.stream().noneMatch(m -> m.getCrm().equals(medico.getCrm()))) {
            this.medicos.add(medico);
            System.out.println("Médico " + medico.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Erro: Médico com CRM " + medico.getCrm() + " já cadastrado.");
        }
    }

    public void cadastrarPlanoDeSaude(PlanoDeSaude plano) {
        if (planosDeSaude.stream().noneMatch(p -> p.getNome().equalsIgnoreCase(plano.getNome()))) {
            this.planosDeSaude.add(plano);
            System.out.println("Plano de saúde " + plano.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Erro: Plano de saúde com nome " + plano.getNome() + " já cadastrado.");
        }
    }

    // Métodos de Busca
    public Paciente buscarPacientePorCpf(String cpf) {
        return pacientes.stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public Medico buscarMedicoPorCrm(String crm) {
        return medicos.stream()
                .filter(m -> m.getCrm().equals(crm))
                .findFirst()
                .orElse(null);
    }

    public PlanoDeSaude buscarPlanoDeSaudePorNome(String nome) {
        return planosDeSaude.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    // Métodos de Agendamento e Gerenciamento
    public Consulta agendarConsulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String local) {
        if (paciente == null || medico == null) {
            System.out.println("Erro: Paciente ou Médico inválido para agendamento.");
            return null;
        }
        if (medico.getAgendaHorarios().contains(dataHora)) {
            System.out.println("Erro: Horário já ocupado para este médico.");
            return null;
        }

        Consulta novaConsulta = new Consulta(paciente, medico, dataHora, local);
        consultas.add(novaConsulta);
        paciente.getHistoricoConsultas().add(novaConsulta);
        medico.adicionarHorarioAgenda(dataHora);
        System.out.println("Consulta agendada com sucesso para " + paciente.getNome() + " com Dr. " + medico.getNome() + " em " + dataHora);
        return novaConsulta;
    }

    public void concluirConsulta(Consulta consulta, String diagnostico, String prescricao) {
        if (consulta != null && consulta.getStatus() == StatusConsulta.Agendada) {
            consulta.setDiagnostico(diagnostico);
            consulta.setPrescricao(prescricao);
            consulta.setStatus(StatusConsulta.Concluida);
            System.out.println("Consulta concluída para " + consulta.getPaciente().getNome() + ".");
        } else {
            System.out.println("Erro: Consulta inválida ou já concluída/cancelada.");
        }
    }

    public void cancelarConsulta(Consulta consulta) {
        if (consulta != null && consulta.getStatus() == StatusConsulta.Agendada) {
            consulta.setStatus(StatusConsulta.Cancelada);
            consulta.getMedico().removerHorarioAgenda(consulta.getDataHora());
            System.out.println("Consulta cancelada para " + consulta.getPaciente().getNome() + ".");
        } else {
            System.out.println("Erro: Consulta inválida ou já concluída/cancelada.");
        }
    }

    public Internacao internarPaciente(Paciente paciente, Medico medicoResponsavel, LocalDateTime dataEntrada, String quarto) {
        if (paciente == null || medicoResponsavel == null) {
            System.out.println("Erro: Paciente ou Médico inválido para internação.");
            return null;
        }
        if (quartosOcupacao.getOrDefault(quarto, true)) { // true se não existir ou estiver ocupado
            System.out.println("Erro: Quarto " + quarto + " está ocupado ou não existe.");
            return null;
        }

        Internacao novaInternacao = new Internacao(paciente, medicoResponsavel, dataEntrada, quarto);
        internacoes.add(novaInternacao);
        paciente.getHistoricoInternacoes().add(novaInternacao);
        quartosOcupacao.put(quarto, true); // Ocupa o quarto
        System.out.println("Paciente " + paciente.getNome() + " internado no quarto " + quarto + ".");
        return novaInternacao;
    }

    public void encerrarInternacao(Internacao internacao, LocalDateTime dataSaida) {
        if (internacao != null && internacao.getStatus() == StatusInternacao.Ativa) {
            internacao.setDataSaida(dataSaida);
            internacao.setStatus(StatusInternacao.Encerrada);
            quartosOcupacao.put(internacao.getQuarto(), false); // Libera o quarto
            System.out.println("Internação do paciente " + internacao.getPaciente().getNome() + " encerrada.");
        } else {
            System.out.println("Erro: Internação inválida ou já encerrada/cancelada.");
        }
    }

    public void cancelarInternacao(Internacao internacao) {
        if (internacao != null && internacao.getStatus() == StatusInternacao.Ativa) {
            internacao.setStatus(StatusInternacao.Cancelada);
            quartosOcupacao.put(internacao.getQuarto(), false); // Libera o quarto
            System.out.println("Internação do paciente " + internacao.getPaciente().getNome() + " cancelada.");
        } else {
            System.out.println("Erro: Internação inválida ou já encerrada/cancelada.");
        }
    }

    public List<Consulta> getConsultasFuturas() {
        return consultas.stream()
                .filter(c -> c.getDataHora().isAfter(LocalDateTime.now()) && c.getStatus() == StatusConsulta.Agendada)
                .collect(Collectors.toList());
    }

    public List<Consulta> getConsultasPassadas() {
        return consultas.stream()
                .filter(c -> c.getDataHora().isBefore(LocalDateTime.now()) || c.getStatus() != StatusConsulta.Agendada)
                .collect(Collectors.toList());
    }

    public List<Internacao> getPacientesInternadosAtualmente() {
        return internacoes.stream()
                .filter(i -> i.getStatus() == StatusInternacao.Ativa)
                .collect(Collectors.toList());
    }

    // Métodos de Estatísticas Avançadas
    public Medico getMedicoQueMaisAtendeu() {
        return consultas.stream()
                .filter(c -> c.getStatus() == StatusConsulta.Concluida)
                .collect(Collectors.groupingBy(Consulta::getMedico, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Especialidade getEspecialidadeMaisProcurada() {
        return consultas.stream()
                .filter(c -> c.getStatus() == StatusConsulta.Concluida)
                .collect(Collectors.groupingBy(c -> c.getMedico().getEspecialidade(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public long getQuantidadePacientesPorPlano(PlanoDeSaude plano) {
        return pacientes.stream()
                .filter(p -> p.getPlanoDeSaude() != null && p.getPlanoDeSaude().equals(plano))
                .count();
    }

    public double getEconomiaTotalPorPlano(PlanoDeSaude plano) {
        double economiaConsultas = consultas.stream()
                .filter(c -> c.getPaciente().getPlanoDeSaude() != null && c.getPaciente().getPlanoDeSaude().equals(plano))
                .mapToDouble(c -> {
                    double custoOriginal = c.getMedico().getCustoConsulta();
                    double desconto = c.getPaciente().getPlanoDeSaude().getDescontoPorEspecialidade(c.getMedico().getEspecialidade());
                    if (c.getPaciente().getIdade() >= 60) {
                        desconto += 0.1; // Desconto adicional para idosos
                    }
                    return custoOriginal * desconto; // Retorna o valor do desconto, não o custo final
                }).sum();

        // fazer dps: Implementar economia de internações se houver lógica de desconto para internações
        return economiaConsultas;
    }

    public double getTempoMedioInternacao() {
        return internacoes.stream()
                .filter(i -> i.getStatus() == StatusInternacao.Encerrada && i.getDataSaida() != null)
                .mapToLong(i -> Duration.between(i.getDataEntrada(), i.getDataSaida()).toHours())
                .average()
                .orElse(0.0);
    }

    public Map<Especialidade, Double> getTaxaOcupacaoPorEspecialidade() {
        Map<Especialidade, Long> internacoesPorEspecialidade = internacoes.stream()
                .filter(i -> i.getStatus() == StatusInternacao.Ativa)
                .collect(Collectors.groupingBy(i -> i.getMedicoResponsavel().getEspecialidade(), Collectors.counting()));

        long totalQuartos = quartosOcupacao.size();
        if (totalQuartos == 0) {
            return new HashMap<>();
        }

        Map<Especialidade, Double> taxaOcupacao = new HashMap<>();
        for (Map.Entry<Especialidade, Long> entry : internacoesPorEspecialidade.entrySet()) {
            taxaOcupacao.put(entry.getKey(), (double) entry.getValue() / totalQuartos);
        }
        return taxaOcupacao;
    }
}