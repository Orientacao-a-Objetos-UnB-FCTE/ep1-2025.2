package hospital.terminal;

import hospital.entidade.Internacao;
import hospital.entidade.Pacientes;
import hospital.servico.AgendamentoExame;
import hospital.servico.PersistenciaDados;
import hospital.util.Excecao;
import hospital.entidade.Medicos;
import hospital.servico.Agendamento;
import hospital.servico.GerenciarInternacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final DateTimeFormatter DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void main(String[] args) {


        List<Pacientes> pacientes = new ArrayList<>();
        List<Medicos> medicos = new ArrayList<>();
        List<Internacao> internacoes = new ArrayList<>();

        Pacientes p0= null;
        Pacientes p1=null;
        Medicos m0 = null;

        carregarDadosPersistidos(pacientes, medicos, internacoes);

        if (pacientes.isEmpty()) {
            System.out.println("\n Detectando primeiro uso. Criando dados");
            p0 = new Pacientes("Leon Scott Kennedy", "123.442.331-25", 27, "RE007");
            p1 = new Pacientes("Claire Redfield", "327.198.271-21", 24, "RE010");
            Pacientes p2 = new Pacientes("Ellie Williams", "849.381.739-56", 14, "TLOU1");
            Pacientes p3 = new Pacientes("Victor Sullivan", "061.831.628-62", 66, "UNC02");
            Pacientes p4 = new Pacientes("Johnny Cage", "648.371.629-64", 34, "MK042");
            Pacientes p5 = new Pacientes("John Marston", "764.817.361-21", 31, "RDR01");
            Pacientes p6 = new Pacientes("Alan Wake", "849.173.651-74", 42, "AW322");
            Pacientes p7 = new Pacientes("Arthur Morgan", "985.727.648-17", 36, "RDR02");
            Pacientes p8 = new Pacientes("Ezio Auditore", "263.817.498-26", 29, "ASC01");
            Pacientes p9 = new Pacientes("Elena Fisher", "029.857.162-34", 30, "UNC86");
            Pacientes p10 = new Pacientes("Triss Merigold", "447.816.345-75", 35, "TW333");
            Pacientes p11 = new Pacientes("Cranky Kong", "543.277.140-72", 81, "DK001");

            pacientes.add(p0);pacientes.add(p1);pacientes.add(p2);pacientes.add(p3);pacientes.add(p4);pacientes.add(p5);
            pacientes.add(p6);pacientes.add(p7);pacientes.add(p8);pacientes.add(p9);pacientes.add(p10);pacientes.add(p11);

            m0 = new Medicos("Jill Valentine", "DF001", "Clínico-Geral", 250.00);
            medicos.add(m0);

            Medicos m1 = new Medicos("Joel Miller", "DF002", "Pediatra", 150.00);
            medicos.add(m1);

            Medicos m2 = new Medicos("Nathan Drake", "DF003", "Cardiologista", 80.00);
            medicos.add(m2);

            Medicos m3 = new Medicos("Lara Croft", "DF004", "Pneumologista", 125.00);
            medicos.add(m3);

            Medicos m4 = new Medicos("Geralt de Rívia", "DF005", "Infectologista", 110.00);
            medicos.add(m4);

            Medicos m5 = new Medicos("Gordon Freeman", "DF006", "Geriatra", 115.00);
            medicos.add(m5);

            Medicos m6 = new Medicos("James Sunderland", "DF007", "Psiquiatra", 100.00);
            medicos.add(m6);
            System.out.println("...Dados prontos para serem salvos...");
        }else {
            p0 =buscarPaciente(pacientes, "Leon Scott kennedy");
            p1= buscarPaciente(pacientes, "Claire Redfield");
            m0 = buscarMedico(medicos, "Jill Valentine");
        }
        if (p0!= null && m0 != null && p1 != null) {
            try {
                LocalDateTime dataInicial = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
                Agendamento.agendarConsulta(m0, p0, "Check-up inicial", dataInicial);
                GerenciarInternacao.registrarInternacao(internacoes, p1, m0, LocalDate.now().minusDays(3), "Q201", 500.00);
            }catch (IllegalArgumentException e){
                System.out.println("Erro ao configurar dados iniciais: " + e.getMessage());
            }
        }

        PersistenciaDados.carregarPacientes(pacientes);
        PersistenciaDados.carregarMedicos(medicos);

        try {
            LocalDateTime dataInicial = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
            Agendamento.agendarConsulta(m0, p0, "Check-up inicial", dataInicial);

            GerenciarInternacao.registrarInternacao(internacoes, p1, m0, LocalDate.now().minusDays(3), "Q201", 500.00);
        }catch (IllegalArgumentException e){
            System.out.println("Erro ao configurar dados iniciais: " + e.getMessage());
        }

        boolean sair = false;

        while (!sair){
            System.out.println("\n|-Bem vindo-| " +
                           "\n---|HOSPITAL SAINT DENNIS|---");
            System.out.println("1 - Lista de hospital.entidade.Pacientes");
            System.out.println("2 - Lista de Médicos");
            System.out.println("3 - Cadastrar novo Paciente");
            System.out.println("4 - Agendar Consulta");
            System.out.println("5 - Mostrar histórico de paciente");
            System.out.println("6 - Registrar internação");
            System.out.println("7 - Liberar Internação");
            System.out.println("8 - Agendar Exame");
            System.out.println("9 - Salvar dados");
            System.out.println("0 - Sair");

            int opcao = Excecao.lerOpcaoMenu("\nPor favor, escolha uma opção: ");

            switch (opcao) {
                case 1: listarPacientes(pacientes); break;
                case 2: listarMedicos(medicos); break;
                case 3: cadastrarPacienteAcao(pacientes, medicos); break;
                case 4: agendarConsultaAcao(pacientes, medicos); break;
                case 5: mostrarHistoricoPacienteAcao(pacientes); break;
                case 6: registrarInternacaoAcao(pacientes, medicos, internacoes); break;
                case 7: liberarInternacaoAcao(internacoes); break;
                case 8: agendarExameAcao(pacientes); break;
                case 9: PersistenciaDados.salvarTodosDados(pacientes, medicos, internacoes); break;
                case 0:
                    sair = true;
                    System.out.println("Saindo");
                    Excecao.closeScanner();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    public static void listarPacientes(List<Pacientes> pacientes) {
        System.out.println("     \nPacientes cadastrados:");
        for (Pacientes p : pacientes) {
            System.out.println(p);
        }
    }

    public static void listarMedicos(List<Medicos> medicos) {
        System.out.println("Médicos cadastrados:");
        for (Medicos m : medicos) {
            System.out.println(m);
        }
    }
    public static void cadastrarPacienteAcao(List<Pacientes> pacientes, List<Medicos> medicos) {
        String nomeNovo = Excecao.lerStringSemNumero("Nome do Paciente: ");
        String cpfNovo = Excecao.lerCpf("CPF do Paciente: ");
        int idadeNova = Excecao.lerInteiroPositivo("Idade do Paciente: ");
        String idNovo = Excecao.lerString("ID do Paciente (utilize apenas 5 termos, entre letras e úmeros): ");

        Pacientes novoPaciente = new Pacientes(nomeNovo, cpfNovo, idadeNova, idNovo);
        pacientes.add(novoPaciente);
        System.out.println("Paciente cadastrado com sucesso! " + nomeNovo + "!");

        boolean voltarDoSubMenu = false;
        while (!voltarDoSubMenu) {
            System.out.println("\n--- Ações para " + novoPaciente.getNome() + " ---");
            System.out.println("1 - Agendar Consulta");
            System.out.println("2 - Agendar Exame");
            System.out.println("0 - Voltar ao Menu Principal");
            int subOpcao = Excecao.lerOpcaoMenu("Escolha uma opção: ");

            switch (subOpcao) {
                case 1:
                    agendarConsultaSimples(medicos, novoPaciente);
                    break;
                case 2:
                    agendarExameSimples(novoPaciente);
                    break;
                case 0:
                    voltarDoSubMenu = true;
                    System.out.println("Voltando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    public static void agendarConsultaAcao(List<Pacientes> pacientes, List<Medicos> medicos) {
        System.out.println("\n|| Agendar Consulta ||");
        String nomePaciente = Excecao.lerString("Nome do Paciente: ");
        Pacientes pacienteEscolhido = buscarPaciente(pacientes, nomePaciente);

        if (pacienteEscolhido == null) {
            System.out.println("Paciente não encontrado no sistema!");
            return;
        }
        agendarConsultaSimples(medicos, pacienteEscolhido);
    }

    public static void agendarConsultaSimples(List<Medicos> medicos, Pacientes paciente) {
        System.out.println("\n ||Agendar Consulta||");
        listarMedicos(medicos);

        String nomeMedico = Excecao.lerString("\nNome do Médico: ");
        Medicos medicoEscolhido = buscarMedico(medicos, nomeMedico);

        if (medicoEscolhido == null) {
            System.out.println("Médico não encontrado no sistema!");
            return;
        }

        System.out.println("--- Horários AGENDADOS para " + medicoEscolhido.getNome() + " ---");
        List<LocalDateTime> agendados = medicoEscolhido.getHorariosAgendados();
        if (agendados.isEmpty()) {
            System.out.println("Médico não tem consultas agendadas (Totalmente disponível no momento).");
        } else {
            System.out.println("Os horários abaixo estão ocupados:");
            agendados.forEach(h -> System.out.println(("- " + h.format(DATA_HORA))));
        }

        LocalDateTime horario = Excecao.lerDataHora("\nEscolha a Data e Hora para a Consulta");
        String desc = Excecao.lerString("Descrição da Consulta: ");

        try {
            Agendamento.agendarConsulta(medicoEscolhido, paciente, desc, horario);
            System.out.println("Consulta agendada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao agendar: " + e.getMessage());
        }
    }
    public static void mostrarHistoricoPacienteAcao(List<Pacientes> pacientes) {
        String nomeHistorico = Excecao.lerString("Nome do paciente: ");
        Pacientes pHistorico = buscarPaciente(pacientes, nomeHistorico);

        if (pHistorico == null) {
            System.out.println("Paciente não encontrado no sistema!");
            return;
        }

        boolean voltarHistorico = false;
        while (!voltarHistorico) {
            System.out.println("\n--- Histórico de " + pHistorico.getNome() + " ---");
            System.out.println("1 - Ver Consultas");
            System.out.println("2 - Ver Exames");
            System.out.println("3 - Ver Internações");
            System.out.println("0 - Voltar");

            int subOpcao = Excecao.lerOpcaoMenu("Escolha o tipo de histórico para visualizar: ");

            switch (subOpcao) {
                case 1:
                    System.out.println("\n[CONSULTAS]");
                    if (pHistorico.getHistoricoConsultas().isEmpty()) {
                        System.out.println("Nenhuma consulta registrada.");
                    } else {
                        pHistorico.getHistoricoConsultas().forEach(c -> System.out.println(
                                c.toString() + " | Data: " + c.getDataHorario().format(DATA_HORA)));
                    }
                    break;
                case 2:
                    System.out.println("\n[EXAMES]");
                    if (pHistorico.getExames().isEmpty()) {
                        System.out.println("Nenhum exame registrado.");
                    } else {
                        pHistorico.getExames().forEach(e -> System.out.println(
                                e.toString() + " | Data: " + e.getDataHorario().format(DATA_HORA)));
                    }
                    break;
                case 3:
                    System.out.println("\n[INTERNAÇÕES]");
                    if (pHistorico.getHistoricoInternacoes().isEmpty()) {
                        System.out.println("Nenhuma internação registrada.");
                    } else {
                        pHistorico.getHistoricoInternacoes().forEach(i -> System.out.println(
                                i.toString() + " | Entrada: " + i.getDataEntrada().format(DATA)));
                    }
                    break;
                case 0:
                    voltarHistorico = true;
                    System.out.println("Voltando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    public static void registrarInternacaoAcao(List<Pacientes> pacientes, List<Medicos> medicos, List<Internacao> internacoes) {
        System.out.println("\n|| Registrar Internação ||");
        String nomeInt = Excecao.lerString("Nome do paciente para internação: ");
        Pacientes pInt = buscarPaciente(pacientes, nomeInt);

        if (pInt == null) {
            System.out.println("Paciente não encontrado no sistema!");
            return;
        }
        String nomeMed = Excecao.lerString("Nome do médico responsável:");
        Medicos mResp = buscarMedico(medicos, nomeMed);
        if (mResp == null) {
            System.out.println("Médico não encontrado no sistema!");
            return;
        }

        LocalDate dataEntrada = Excecao.lerData(" Digite a data da entrada: ");
        String quarto = Excecao.lerString("Quarto: ");
        double custo = Excecao.lerDoublePositivo("Custo: ");

        try {
            Internacao novaInternacao = GerenciarInternacao.registrarInternacao(internacoes, pInt, mResp, dataEntrada, quarto, custo);
            System.out.println("Internação registrada com sucesso: " + novaInternacao);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao registrar internação: " + e.getMessage());
        }
    }

    public static void liberarInternacaoAcao(List<Internacao> internacoes) {
        System.out.println("\n|| Liberar Internação ||");
        System.out.println("Internações ativas no sistema:");

        if (internacoes.stream().noneMatch(Internacao::isAtiva)) {
            System.out.println("Não há internações ativas para liberar.");
            return;
        }
        internacoes.stream()
                .filter(Internacao::isAtiva)
                .forEach(i ->
                        System.out.println(
                                "Paciente: " + i.getPaciente().getNome() + " | Quarto: " + i.getQuarto() + " | Entrada: " + i.getDataEntrada().format(DATA)));

        String quartoLiberar = Excecao.lerString("\nDigite o número do quarto da internação a liberar: ");

        try {
            LocalDate dataSaida = Excecao.lerData("Digite a data de baixa: ");

            Internacao intLiberar = GerenciarInternacao.liberarInternacao(internacoes, quartoLiberar, dataSaida);

            System.out.println("Baixa registrada com sucesso: " + intLiberar);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao liberar internação: " + e.getMessage());
        }
    }

    public static void agendarExameAcao(List<Pacientes> pacientes) {
        System.out.println("\n|| Agendar Exame ||");
        String nomeExame = Excecao.lerString("Nome do Paciente: ");
        Pacientes pExame = buscarPaciente(pacientes, nomeExame);

        if (pExame == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }
        agendarExameSimples(pExame);
    }

    public static void agendarExameSimples(Pacientes pExame) {
        String tipoExame = Excecao.lerString("Tipo de Exame: ");
        LocalDateTime dataHorarioExame = Excecao.lerDataHora("Data e horário do exame");

        try {
            AgendamentoExame.agendarExame(pExame, tipoExame, dataHorarioExame);
            System.out.println("Exame de " + tipoExame + " agendado para " + pExame.getNome() + " em " + dataHorarioExame.format(DATA_HORA) + ".");
        } catch (Exception e) {
            System.out.println("Erro ao agendar exame: " + e.getMessage());
        }
    }
    public static Pacientes buscarPaciente(List<Pacientes> pacientes, String nome){
        for(Pacientes p : pacientes){
            if (p.getNome().equalsIgnoreCase(nome)){
                return p;
            }
        }return null;
    }
    public static Medicos buscarMedico(List<Medicos> medicos, String nome){
        for(Medicos m : medicos){
            if(m.getNome().equalsIgnoreCase(nome)){
                return m;
            }
        }return null;
    }
    public static void carregarDadosPersistidos(List<Pacientes> pacientes, List<Medicos> medicos, List<Internacao> internacoes){
        System.out.println("\n___Tentando carregar dados persistidos___");
        PersistenciaDados.carregarPacientes(pacientes);
        PersistenciaDados.carregarMedicos(medicos);
        PersistenciaDados.carregarInternacoes(internacoes, pacientes, medicos);
        PersistenciaDados.carregarConsultas(pacientes, medicos);
        PersistenciaDados.carregarExames(pacientes);
        System.out.println("............");
    }

}
