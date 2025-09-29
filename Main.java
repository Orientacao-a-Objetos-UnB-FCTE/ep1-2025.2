import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        List<Pacientes> pacientes = new ArrayList<>();
        List<Medicos> medicos = new ArrayList<>();
        List<Internacao> internacoes = new ArrayList<>();

        Pacientes p0 = new Pacientes("Leon Scott Kennedy" , "123.442.331-25" , 27 , "RE007");
        Pacientes p1 = new Pacientes("Claire Redfield", "327.198.271-21", 24, "RE010");
        Pacientes p2 = new Pacientes("Ellie Williams","849.381.739-56",  14, "TLOU1");
        Pacientes p3 = new Pacientes("Victor Sullivan","061.831.628-62", 66, "UNC02");
        Pacientes p4 = new Pacientes("Johnny Cage","648.371.629-64", 34, "MK042");
        Pacientes p5 = new Pacientes("John Marston","764.817.361-21",  31, "RDR01");
        Pacientes p6 = new Pacientes("Alan Wake","849.173.651-74", 42, "AW322");
        Pacientes p7 = new Pacientes("Arthur Morgan","985.727.648-17",  36, "RDR02");
        Pacientes p8 = new Pacientes("Ezio Auditore","263.817.498-26", 29, "ASC01");
        Pacientes p9 = new Pacientes("Elena Fisher","029.857.162-34",  30, "UNC86");
        Pacientes p10 = new Pacientes("Triss Merigold","447.816.345-75", 35, "TW333");
        Pacientes p11 = new Pacientes("Cranky Kong","543.277.140-72",  81, "DK001");

         pacientes.add(p0); pacientes.add(p1); pacientes.add(p2); pacientes.add(p3); pacientes.add(p4); pacientes.add(p5);
         pacientes.add(p6); pacientes.add(p7); pacientes.add(p8); pacientes.add(p9); pacientes.add(p10); pacientes.add(p11);

        Medicos m0 = new Medicos("Jill Valentine", "DF001", "Clínico-Geral", 250.00);
        medicos.add(m0);
        m0.adicionarHorario("Segunda ás 10:00"); m0.adicionarHorario("Segunda ás 15:00"); m0.adicionarHorario("Quarta ás 9:00");
        m0.adicionarHorario("Quinta ás 16:00");

        Medicos m1 = new Medicos("Joel Miller", "DF002", "Pediatra", 150.00);
        medicos.add(m1);
        m1.adicionarHorario("Terça ás 8:00"); m1.adicionarHorario("Terça ás 13:00"); m1.adicionarHorario("Quinta ás 10:00");
        m1.adicionarHorario("Quinta ás 16:15"); m1.adicionarHorario("Sexta ás 12:00");

        Medicos m2 = new Medicos("Nathan Drake", "DF003", "Cardiologista", 80.00);
        medicos.add(m2);
        m2.adicionarHorario("Quarta ás 8:00");m2.adicionarHorario("Quarta ás 10:30");m2.adicionarHorario("Quarta ás 11:40");
        m2.adicionarHorario("Sexta ás 9:30:00");m2.adicionarHorario("Sexta ás 14:20");m2.adicionarHorario("Sexta ás 16:00");

        Medicos m3 = new Medicos("Lara Croft", "DF004", "Pneumologista", 125.00);
        medicos.add(m3);
        m3.adicionarHorario("Segunda ás 13:10");m3.adicionarHorario("Quarta ás 12:50");m3.adicionarHorario("Sábado ás 10:30");

        Medicos m4 = new Medicos("Geralt de Rívia", "DF005", "Infectologista", 110.00);
        medicos.add(m4);
        m4.adicionarHorario("Sábado ás 8:20");m4.adicionarHorario("Terça ás 10:00");m4.adicionarHorario("Quinta ás 11:45");
        m4.adicionarHorario("Quinta ás 9:00");

        Medicos m5 = new Medicos("Gordon Freeman", "DF006", "Geriatra", 115.00);
        medicos.add(m5);
        m5.adicionarHorario("Segunda ás 14:30");m5.adicionarHorario("Segunda ás 16:15");


        Medicos m6 = new Medicos("James Sunderland", "DF007", "Psiquiatra", 100.00);
        medicos.add(m6);
        m6.adicionarHorario("Sábado ás 10:00");m6.adicionarHorario("Sábado ás 11:30");m6.adicionarHorario("Sábado ás 8:25");

        boolean sair = false;


        while (!sair){
            System.out.println("       \n|-Bem vindo-| \n---|HOSPITAL THE FIRST OF US|---");
            System.out.println("1 - Lista de Pacientes");
            System.out.println("2 - Lista de Médicos");
            System.out.println("3 - Cadastrar novo Paciente");
            System.out.println("4 - Agendar Consulta");
            System.out.println("5 - Mostrar histórico de paciente");
            System.out.println("6 - Registrar internação");
            System.out.println("7 - Liberar Internação");
            System.out.println("8 - Agendar Exame");
            System.out.println("0 - Sair");

            int opcao = Excecao.lerOpcaoMenu("\nPor favor, escolha uma opção: ");

            switch (opcao){
                case 1:
                    System.out.println("     \nPacientes cadastrados:");
                    for(Pacientes p : pacientes){
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.println("Médicos cadastrados:");
                    for (Medicos m : medicos){
                        System.out.println(m);
                    }
                    break;
                case 3:
                    String nomeNovo = Excecao.lerStringSemNumero("Nome do Paciente: ");
                    String cpfNovo = Excecao.lerCpf("CPF do Paciente: ");
                    int idadeNova= Excecao.lerInteiroPositivo("Idade do Paciente: ");
                    String idNovo = Excecao.lerString("ID do Paciente");
                    Pacientes novoPaciente = new Pacientes(nomeNovo, cpfNovo, idadeNova, idNovo);
                    pacientes.add(novoPaciente);
                    System.out.println("Paciente cadastrado com sucesso!");
                    break;
                case 4:
                    String nomePaciente = Excecao.lerString("Nome do Paciente: ");
                    Pacientes pacienteEscolhido = buscarPaciente(pacientes, nomePaciente);
                    if(pacienteEscolhido == null){
                        System.out.println("paciente não encontrado no sistema!");
                        break;
                    }
                    String nomeMedico = Excecao.lerString("Nome do Médico: ");
                    Medicos medicoEscolhido = buscarMedico(medicos, nomeMedico);
                    if (medicoEscolhido == null){
                        System.out.println("Médico não encontrado no sistema!");
                        break;
                    }
                    System.out.println("Horários disponíveis para " + medicoEscolhido.getNome() +":");
                    for (String h : medicoEscolhido.getHorariosDisponiveis()){
                        System.out.println(("- " + h));
                    }
                    String horario = Excecao.lerString("Escolha um dos horários acima: ");
                    if (!medicoEscolhido.simDisponivel(horario)){
                        System.out.println("Esse horário não está disponível! POr favor Escolha um horário válido");
                        break;
                    }
                    String desc = Excecao.lerString("Descrição da Consulta: ");
                    agendarConsulta(medicoEscolhido, pacienteEscolhido, desc, horario);
                    break;
                case 5:
                    String nomeHistorico = Excecao.lerString("Nome do paciente: ");
                    Pacientes pHistorico = buscarPaciente(pacientes, nomeHistorico);
                    if (pHistorico == null){
                        System.out.println("Paciente não encontrado no sistema!");
                        break;
                    }
                    System.out.println("Histórico de " + pHistorico.getNome() + ":");
                    for (Consulta c : pHistorico.getConsultas()){
                        System.out.println(c);
                    }
                    for (Exame e : pHistorico.getExames()){
                        System.out.println(e);
                    }
                    break;
                case 6:
                    String nomeInt = Excecao.lerString("Nome do paciente para internação: ");
                    Pacientes pInt = buscarPaciente(pacientes, nomeInt);
                    if(pInt == null){
                        System.out.println("Paciente não encontrado no sistema!");
                        break;
                    }
                    String nomeMed = Excecao.lerString("Nome do médico responsável:");
                    Medicos mResp = buscarMedico(medicos, nomeMed);
                    if(mResp == null){
                        System.out.println("Médico não encontrado no sistema!");
                        break;
                    }
                    String dataEntrada = Excecao.lerString(" Digite a data da entrada, por favor! ");
                    String quarto = Excecao.lerString("Quarto: ");
                    double custo = Excecao.lerDoublePositivo("Custo: ");
                    Internacao internacao = new Internacao(pInt, mResp, dataEntrada, quarto, custo);
                    internacoes.add(internacao);
                    System.out.println("Internação registrada com sucesso: " + internacao);
                    break;
                case 7:
                    System.out.println("\nInternações ativas no sistema:");
                    boolean temAtiva = false;
                    for (Internacao i : internacoes) {
                        if (i.isAtiva()) {
                            System.out.println("Paciente: " + i.getMedicoResponsavel().getNome() +
                                    " | Quarto: " + i.getQuarto() +
                                    " | Data entrada: " + i.getDataEntrada());
                            temAtiva = true;
                        }
                    }
                    if (!temAtiva) {
                        System.out.println("Não há internações ativas para liberar.");
                        break;
                    }
                    String quartoLiberar = Excecao.lerString("\nDigite o número do quarto da internação a liberar: ");
                    Internacao intLiberar = buscarInternacao(internacoes, quartoLiberar);

                    if (intLiberar != null && intLiberar.isAtiva()) {
                        String dataSaida = Excecao.lerString("Digite a data de baixa: ");
                        intLiberar.liberarInternacao(dataSaida);
                        System.out.println("Baixa registrada: " + intLiberar);
                    } else {
                        System.out.println("Internação não encontrada ou já liberada!");
                    }
                    break;
                case 8:
                    System.out.print("Nome do paciente: ");
                    String nomeExame = Excecao.lerString("Nome do Paciente: ");
                    Pacientes pExame = buscarPaciente(pacientes, nomeExame);
                    if (pExame == null){
                        System.out.println("Paciente não encontrado!");
                        break;
                    }
                    String tipoExame = Excecao.lerString("Tipo de Exame: ");
                    String dataHorarioExame = Excecao.lerString("Data e horário do exame: ");
                    AgendamentoExame.agendarExame(pExame, tipoExame, dataHorarioExame);
                    System.out.println("Exame agendado para: " + pExame.getNome() + " - " + tipoExame + " em " + dataHorarioExame);
                    break;
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
    public static Internacao buscarInternacao(List<Internacao> internacoes, String quarto){
        for(Internacao i : internacoes){
            if(i.getQuarto().equalsIgnoreCase(quarto) && i.isAtiva()){
                return i;
            }
        }return null;
    }
    public static void agendarConsulta(Medicos medicos, Pacientes pacientes, String descricao, String horario){
        if (!medicos.simDisponivel(horario)){
            System.out.println("Erro: Médico " + medicos.getNome() + "Não está disponível neste horário: " + horario);
            return;
        }
        Consulta consulta = new Consulta(medicos, pacientes, descricao, horario);
        pacientes.adicionarConsulta(consulta);
        medicos.adicionarConsulta(consulta);
        medicos.getHorariosDisponiveis().remove(horario);

        System.out.println("Consulta agendada: " + consulta + "Tenha um bom dia!");
    }

}
