package hospital.servico;

import hospital.entidade.*;
import hospital.entidade.Exame.StatusExame;
import hospital.entidade.Consulta.StatusConsulta;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.LocalDate;


public class PersistenciaDados {
    private static String arquivo_pacientes = "pacientes.csv";
    private static String arquivo_medicos = "medicos.csv";
    private static String arquivo_internacoes = "internacoes.csv";
    private static String arquivo_consultas = "consultas.csv";
    private static String arquivo_exames = "exames.csv";

    public static void salvarTodosDados(List<Pacientes> pacientes, List<Medicos> medicos, List<Internacao> internacoes) {
        salvarPacientes(pacientes);
        salvarMedicos(medicos);
        salvarInternacoes(internacoes);
        salvarConsultas(pacientes);
        salvarExames(pacientes);
        System.out.println("Dados salvos com sucesso!");

    }

    public static void salvarPacientes(List<Pacientes> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo_pacientes))) {
            writer.println("Nome, CPF, Idade,ID");
            for (Pacientes p : pacientes) {
                writer.println(p.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar pacientes no arquivo: " + e.getMessage());
        }
    }

    public static void salvarMedicos(List<Medicos> medicos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo_medicos))) {
            writer.println("Nome, CRM, Especialidade, Custo da conculta");
            for (Medicos m : medicos) {
                writer.println(m.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar medicos no arquivo: " + e.getMessage());
        }
    }

    public static void salvarInternacoes(List<Internacao> internacoes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo_internacoes))) {
            writer.println("Paciente, Médico responsável, Data de entrada, Custo, Quarto");
            for (Internacao i : internacoes) {
                writer.println(i.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar internação no arquivo: " + e.getMessage());
        }
    }

    public static void salvarConsultas(List<Pacientes> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo_consultas))) {
            writer.println("Paciente CPF, Médico CRM, Descrição, Data e Horário, Status");
            for (Pacientes p : pacientes) {
                for (Consulta c : p.getHistoricoConsultas()) {
                    writer.println(c.toCSV());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar consultas no arquivo: " + e.getMessage());
        }
    }

    public static void salvarExames(List<Pacientes> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo_exames))) {
            writer.println("Paciente CPF, Tipo de exame, Data e Horário, Status");
            for (Pacientes p : pacientes) {
                for (Exame e : p.getExames()) {
                    writer.println(e.toCSV());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar exames no arquivo: " + e.getMessage());
        }
    }
    public static void carregarPacientes(List<Pacientes> pacientes){
        try (BufferedReader reader =new BufferedReader(new FileReader(arquivo_pacientes))){
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    try {
                        String nome = dados[0];
                        String cpf = dados[1];
                        int idade = Integer.parseInt(dados[2].trim());
                        String id = dados[3];

                        Pacientes p = new Pacientes(nome, cpf, idade, id);
                        pacientes.add(p);
                    }catch (NumberFormatException e){
                        System.err.println("Erro de formato de número ao carregar paciente: " + linha);
                    }
                }
            }
            System.out.println("Pacientes carregados: " + pacientes.size() + "registros");
        }catch (IOException e){
            System.out.println("Arquivo de paciente não encontrado. Iniciando com lista vazia");
        }
    }
    public static void carregarInternacoes(List<Internacao> internacoes, List<Pacientes> pacientes, List<Medicos> medicos){
        internacoes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo_internacoes))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    Pacientes paciente = buscarPacientePorCpf(pacientes, dados[0].trim());
                    Medicos medico = buscarMedicoPorCrm(medicos, dados[1].trim());
                    if (paciente != null && medico != null) {
                        LocalDate dataEntrada = LocalDate.parse(dados[2].trim());
                        double custo = Double.parseDouble(dados[3].trim());
                        String quarto = dados[4].trim();
                        Internacao i = new Internacao(paciente, medico, dataEntrada, quarto, custo);
                        internacoes.add(i);
                        paciente.adicionarInternacao(i);
                    }
                }
            }
            System.out.println("...Internações carregadas...");
        }catch (IOException e) {
                System.out.println("...Arquivo de internações não encontrado...");
            }

    }
    public static void carregarConsultas(List<Pacientes> pacientes, List<Medicos> medicos){
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo_consultas))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    Pacientes paciente = buscarPacientePorCpf(pacientes, dados[0].trim());
                    Medicos medico = buscarMedicoPorCrm(medicos, dados[1].trim());
                    if (paciente != null && medico != null) {
                        String descricao = dados[2].trim();
                        LocalDateTime dataHora = LocalDateTime.parse(dados[3].trim());
                        StatusConsulta status = StatusConsulta.valueOf(dados[4].trim());
                        Consulta c = new Consulta(medico, paciente, descricao, dataHora);
                        if (status != StatusConsulta.AGENDADA) {

                        }
                        paciente.adicionarConsulta(c);
                        medico.adicionarConsulta(c);
                    }
                }
            }
            System.out.println("...Consultas carregadas...");
        }catch (IOException e) {
            System.out.println("...Arquivo de consulta não encontrado...");
        }

    }
    public static void carregarMedicos(List<Medicos> medicos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo_medicos))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    try {
                        String nome = dados[0];
                        String crm = dados[1];
                        String especialidade = dados[2];
                        double custoConsulta = Double.parseDouble(dados[3].trim());

                        Medicos m = new Medicos(nome, crm, especialidade, custoConsulta);
                        medicos.add(m);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro de formato de número ao carregar medico: " + linha);
                    }
                }
            }
            System.out.println("Médicos carregados: " + medicos.size() + "registros");
        } catch (IOException e) {
            System.out.println("Arquivo de medico não encontrado. Iniciando com lista vazia");
        }
    }
    public static void carregarExames(List<Pacientes> pacientes){
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo_exames))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    Pacientes paciente = buscarPacientePorCpf(pacientes, dados[0].trim());
                    if (paciente != null) {
                        String tipo = dados[1].trim();
                        LocalDateTime dataHora = LocalDateTime.parse(dados[2].trim());
                        StatusExame status = StatusExame.valueOf(dados[3].trim());
                        Exame e = new Exame(paciente, tipo, dataHora);
                        if (status != StatusExame.AGENDADO) {
                        }
                        paciente.adicionarExame(e);
                    }
                }
            }
            System.out.println("...Exames carregadas...");
        }catch (IOException e) {
            System.out.println("...Arquivo de exame não encontrado...");
        }

    }
    private static Pacientes buscarPacientePorCpf(List<Pacientes> pacientes, String cpf) {
        for (Pacientes p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }
    private static Medicos buscarMedicoPorCrm(List<Medicos> medicos, String crm) {
        for (Medicos m : medicos) {
            if (m.getCrm().equals(crm)) {
                return m;
            }
        }
        return null;
    }
}