package hospital;

import hospital.model.*;
import hospital.service.SistemaHospitalar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaHospitalar sistema = new SistemaHospitalar(10);

        // Dados de exemplo (você pode remover se preferir menu limpo)
        PlanoSaude plano1 = new PlanoSaude("Plano Básico", 0.10);
        Paciente p1 = new Paciente("João Silva", "111.111.111-11", 30);
        Paciente p2 = new PacienteEspecial("Maria Souza", "222.222.222-22", 65, plano1);
        Medico m1 = new Medico("Pedro", "CRM1234", "Cardiologia", 200.0);
        Medico m2 = new Medico("Ana", "CRM5678", "Pediatria", 150.0);

        sistema.cadastrarPaciente(p1);
        sistema.cadastrarPaciente(p2);
        sistema.cadastrarMedico(m1);
        sistema.cadastrarMedico(m2);

        // loop do menu
        boolean roda = true;
        while (roda) {
            System.out.println("\n=== SISTEMA HOSPITALAR ===");
            System.out.println("1 - Cadastros (Paciente/Medico)");
            System.out.println("2 - Agendar Consulta");
            System.out.println("3 - Internações");
            System.out.println("4 - Relatórios / Exportar CSV");
            System.out.println("5 - Consultas e Diagnósticos");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1":
                    menuCadastros(sc, sistema);
                    break;
                case "2":
                    agendarConsultaMenu(sc, sistema);
                    break;
                case "3":
                    internacaoMenu(sc, sistema);
                    break;
                case "4":
                    relatoriosMenu(sc, sistema);
                    break;
                case "5":
                    consultasDiagnosticosMenu(sc, sistema);
                    break;
                case "0":
                    System.out.println("Saindo... gerando relatórios de exemplo.");
                    // gera alguns CSVs ao sair
                    sistema.gerarRelatorioPacientesCSV();
                    sistema.gerarRelatorioMedicosCSV();
                    sistema.gerarRelatorioInternacoesCSV();
                    sistema.gerarEstatisticasCSV();
                    roda = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }

    // ----- Menus auxiliares -----
    private static void menuCadastros(Scanner sc, SistemaHospitalar s) {
        System.out.println("\n--- CADASTROS ---");
        System.out.println("1 - Cadastrar Paciente");
        System.out.println("2 - Cadastrar Paciente com Plano");
        System.out.println("3 - Cadastrar Médico");
        System.out.print("Opção: ");
        String op = sc.nextLine().trim();
        switch (op) {
            case "1":
                System.out.print("Nome: "); String nome = sc.nextLine();
                System.out.print("CPF: "); String cpf = sc.nextLine();
                System.out.print("Idade: "); int idade = Integer.parseInt(sc.nextLine());
                s.cadastrarPaciente(new Paciente(nome, cpf, idade));
                System.out.println("Paciente cadastrado.");
                break;
            case "2":
                System.out.print("Nome: "); nome = sc.nextLine();
                System.out.print("CPF: "); cpf = sc.nextLine();
                System.out.print("Idade: "); idade = Integer.parseInt(sc.nextLine());
                System.out.print("Nome do plano: "); String planoNome = sc.nextLine();
                System.out.print("Desconto (ex 0.1 = 10%): "); double desconto = Double.parseDouble(sc.nextLine());
                PlanoSaude plano = new PlanoSaude(planoNome, desconto);
                s.cadastrarPaciente(new PacienteEspecial(nome, cpf, idade, plano));
                System.out.println("Paciente especial cadastrado.");
                break;
            case "3":
                System.out.print("Nome: "); String n = sc.nextLine();
                System.out.print("CRM: "); String crm = sc.nextLine();
                System.out.print("Especialidade: "); String esp = sc.nextLine();
                System.out.print("Custo consulta: "); double custo = Double.parseDouble(sc.nextLine());
                s.cadastrarMedico(new Medico(n, crm, esp, custo));
                System.out.println("Médico cadastrado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void agendarConsultaMenu(Scanner sc, SistemaHospitalar s) {
        System.out.println("\n--- AGENDAR CONSULTA ---");
        // listar pacientes
        List<Paciente> pacs = s.getPacientes();
        for (int i = 0; i < pacs.size(); i++) {
            System.out.printf("%d - %s (CPF: %s)\n", i, pacs.get(i).getNome(), pacs.get(i).getDocumento());
        }
        System.out.print("Escolha índice do paciente: "); int ip = Integer.parseInt(sc.nextLine());
        // listar medicos
        List<Medico> meds = s.getMedicos();
        for (int i = 0; i < meds.size(); i++) {
            System.out.printf("%d - %s (CRM: %s, %s)\n", i, meds.get(i).getNome(), meds.get(i).getDocumento(), meds.get(i).getEspecialidade());
        }
        System.out.print("Escolha índice do médico: "); int im = Integer.parseInt(sc.nextLine());
        System.out.print("Data/hora (yyyy-MM-dd HH:mm): "); String dt = sc.nextLine();
        System.out.print("Local (sala): "); String local = sc.nextLine();

        try {
            LocalDateTime dataHora = LocalDateTime.parse(dt, F);
            boolean ok = s.agendarConsulta(pacs.get(ip), meds.get(im), dataHora, local);
            System.out.println(ok ? "Consulta agendada." : "Falha ao agendar.");
        } catch (Exception e) {
            System.out.println("Erro na entrada da data/hora.");
        }
    }

    private static void internacaoMenu(Scanner sc, SistemaHospitalar s) {
        System.out.println("\n--- INTERNAÇÃO ---");
        List<Paciente> pacs = s.getPacientes();
        for (int i = 0; i < pacs.size(); i++) {
            System.out.printf("%d - %s\n", i, pacs.get(i).getNome());
        }
        System.out.print("Índice paciente: "); int ip = Integer.parseInt(sc.nextLine());
        List<Medico> meds = s.getMedicos();
        for (int i = 0; i < meds.size(); i++) {
            System.out.printf("%d - %s\n", i, meds.get(i).getNome());
        }
        System.out.print("Índice médico: "); int im = Integer.parseInt(sc.nextLine());
        System.out.println("Quartos disponíveis:");
        List<Quarto> qts = s.getQuartos();
        for (Quarto q : qts) {
            System.out.printf("%d - %s\n", q.getNumero(), q.isOcupado() ? "Ocupado" : "Livre");
        }
        System.out.print("Número do quarto: "); int nq = Integer.parseInt(sc.nextLine());
        Quarto escolhido = qts.stream().filter(q -> q.getNumero() == nq).findFirst().orElse(null);
        System.out.print("Custo previsto: "); double custo = Double.parseDouble(sc.nextLine());
        boolean ok = s.registrarInternacao(pacs.get(ip), meds.get(im), escolhido, custo);
        System.out.println(ok ? "Internação registrada." : "Falha ao registrar internação.");
    }

    private static void relatoriosMenu(Scanner sc, SistemaHospitalar s) {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1 - Mostrar no console (pacientes, médicos, internações, estatísticas)");
        System.out.println("2 - Gerar arquivos CSV (pacientes, médicos, internações, estatísticas)");
        System.out.print("Opção: ");
        String op = sc.nextLine().trim();
        if ("1".equals(op)) {
            s.relatorioPacientesConsole();
            s.relatorioMedicosConsole();
            s.relatorioInternacoesConsole();
            s.relatorioEstatisticasConsole();
        } else if ("2".equals(op)) {
            s.gerarRelatorioPacientesCSV();
            s.gerarRelatorioMedicosCSV();
            s.gerarRelatorioInternacoesCSV();
            s.gerarEstatisticasCSV();
            System.out.println("Arquivos CSV gerados na pasta 'relatorios'.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void consultasDiagnosticosMenu(Scanner sc, SistemaHospitalar s) {
        System.out.println("\n--- CONSULTAS E DIAGNÓSTICOS ---");
        System.out.println("1 - Listar consultas agendadas");
        System.out.println("2 - Registrar diagnóstico/prescrição");
        System.out.println("3 - Alterar status da consulta (cancelar/concluir)");
        System.out.print("Opção: ");
        String op = sc.nextLine().trim();

        switch (op) {
            case "1":
                List<Consulta> ag = s.listarConsultasAgendadas();
                for (int i = 0; i < ag.size(); i++) {
                    System.out.printf("%d - %s\n", i, ag.get(i).toString());
                }
                break;
            case "2":
                // listar todas as consultas para escolher
                List<Consulta> todas = s.getConsultas();
                for (int i = 0; i < todas.size(); i++) {
                    System.out.printf("%d - %s\n", i, todas.get(i).toString());
                }
                System.out.print("Escolha índice da consulta: ");
                int idx = Integer.parseInt(sc.nextLine());
                if (idx < 0 || idx >= todas.size()) { System.out.println("Índice inválido."); return; }
                Consulta cons = todas.get(idx);
                System.out.print("Diagnóstico: "); String dx = sc.nextLine();
                System.out.print("Prescrição: "); String rx = sc.nextLine();
                s.registrarDiagnostico(cons, dx, rx);
                System.out.println("Diagnóstico registrado. Consulta marcada como CONCLUÍDA.");
                break;
            case "3":
                todas = s.getConsultas();
                for (int i = 0; i < todas.size(); i++) {
                    System.out.printf("%d - %s\n", i, todas.get(i).toString());
                }
                System.out.print("Escolha índice da consulta: ");
                idx = Integer.parseInt(sc.nextLine());
                if (idx < 0 || idx >= todas.size()) { System.out.println("Índice inválido."); return; }
                cons = todas.get(idx);
                System.out.println("Estado atual: " + cons.getStatus());
                System.out.println("1 - Concluir");
                System.out.println("2 - Cancelar");
                String escolha = sc.nextLine();
                if ("1".equals(escolha)) {
                    s.atualizarStatusConsulta(cons, Consulta.Status.CONCLUIDA);
                    System.out.println("Consulta concluída.");
                } else if ("2".equals(escolha)) {
                    s.atualizarStatusConsulta(cons, Consulta.Status.CANCELADA);
                    System.out.println("Consulta cancelada.");
                } else {
                    System.out.println("Opção inválida.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
