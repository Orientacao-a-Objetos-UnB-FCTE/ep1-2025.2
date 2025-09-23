import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner painel = new Scanner(System.in);

        List<Pacientes> pacientes = new ArrayList<>();
        List<Medicos> medicos = new ArrayList<>();
        List<Internacao> internacoes = new ArrayList<>();

        Pacientes p0 = new Pacientes("Leon Scott Kennedy","12344233125", (byte) 27, "RE007");
        Pacientes p1 = new Pacientes("Claire Redfield","32719827121", (byte) 24, "RE010");
        Pacientes p2 = new Pacientes("Ellie Williams","84938173956", (byte) 14, "TLOU1");
        Pacientes p3 = new Pacientes("Victor Sullivan","06183162862", (byte) 66, "UNC02");
        Pacientes p4 = new Pacientes("Johnny Cage","64837162964", (byte) 34, "MK042");
        Pacientes p5 = new Pacientes("John Marston","76481736121", (byte) 31, "RDR01");
        Pacientes p6 = new Pacientes("Alan Wake","84917365174", (byte) 42, "AW322");
        Pacientes p7 = new Pacientes("Arthur Morgan","98572764817", (byte) 36, "RDR02");
        Pacientes p8 = new Pacientes("Ezio Auditore","26381749826", (byte) 29, "ASC01");
        Pacientes p9 = new Pacientes("Elena Fisher","02985716234", (byte) 30, "UNC86");
        Pacientes p10 = new Pacientes("Triss Merigold","44728163455", (byte) 35, "TW333");
        Pacientes p11 = new Pacientes("Cranky Kong","54327714072", (byte) 81, "DK001");

         pacientes.add(p0); pacientes.add(p1); pacientes.add(p2); pacientes.add(p3); pacientes.add(p4); pacientes.add(p5);
         pacientes.add(p6); pacientes.add(p7); pacientes.add(p8); pacientes.add(p9); pacientes.add(p10); pacientes.add(p11);

        Medicos m0 = new Medicos("Dra. Jill Valentine", "DF001", "Clínico-Geral", 250.00);
        medicos.add(m0);
        m0.adicionarHorario("Segunda ás 10:00"); m0.adicionarHorario("Segunda ás 15:00"); m0.adicionarHorario("Quarta ás 9:00");
        m0.adicionarHorario("Quinta ás 16:00");

        Medicos m1 = new Medicos("Dr. Joel Miller", "DF002", "Pediatra", 150.00);
        medicos.add(m1);
        m1.adicionarHorario("Terça ás 8:00"); m1.adicionarHorario("Terça ás 13:00"); m1.adicionarHorario("Quinta ás 10:00");
        m1.adicionarHorario("Quinta ás 16:15"); m1.adicionarHorario("Sexta ás 12:00");

        Medicos m2 = new Medicos("Dr. Nathan Drake", "DF003", "Cardiologista", 80.00);
        medicos.add(m2);
        m2.adicionarHorario("Quarta ás 8:00");m2.adicionarHorario("Quarta ás 10:30");m2.adicionarHorario("Quarta ás 11:40");
        m2.adicionarHorario("Sexta ás 9:30:00");m2.adicionarHorario("Sexta ás 14:20");m2.adicionarHorario("Sexta ás 16:00");

        Medicos m3 = new Medicos("Dra. Lara Croft", "DF004", "Pneumologista", 125.00);
        medicos.add(m3);
        m3.adicionarHorario("Segunda ás 13:10");m3.adicionarHorario("Quarta ás 12:50");m3.adicionarHorario("Sábado ás 10:30");

        Medicos m4 = new Medicos("Dr. Geralt de Rívia", "DF005", "Infectologista", 110.00);
        medicos.add(m4);
        m4.adicionarHorario("Sábado ás 8:20");m4.adicionarHorario("Terça ás 10:00");m4.adicionarHorario("Quinta ás 11:45");
        m4.adicionarHorario("Quinta ás 9:00");

        Medicos m5 = new Medicos("Dr. Gordon Freeman", "DF006", "Geriatra", 115.00);
        medicos.add(m5);
        m5.adicionarHorario("Segunda ás 14:30");m5.adicionarHorario("Segunda ás 16:15");


        Medicos m6 = new Medicos("Dr. James Sunderland", "DF007", "Psiquiatra", 100.00);
        medicos.add(m6);
        m6.adicionarHorario("Sábado ás 10:00");m6.adicionarHorario("Sábado ás 11:30");m6.adicionarHorario("Sábado ás 8:25");

        boolean sair = false;

        while (!sair){
            System.out.println("\n---Hospital Clair Obscur---");
            System.out.println("1 - Lista de Pacientes");
            System.out.println("2 - Lista de Médicos");
            System.out.println("3 - Agendar Consulta");
            System.out.println("4 - Mostrar histórico de paciente");
            System.out.println("5 - Registrar internação");
            System.out.println("6 - Liberar Internação");
            System.out.println("0 - Sair");
            System.out.println("Por favor, escolha uma opção:");
            int opcao = painel.nextInt();
            painel.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("\nPacientes cadastrados:");
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
                    System.out.println("Nome do Paciente:");
                    String nomePaciente = painel.nextLine();
                    Pacientes pacienteEscolhido = buscarPaciente(pacientes, nomePaciente);
                    if (pacienteEscolhido == null){
                        System.out.println("Paciente não encontrado no sistema!");
                        break;
                    }
                    System.out.print("Nome do Médico: ");
                    String nomeMedico = painel.nextLine();
                    Medicos medicoEscolhido = buscarMedico(medicos, nomeMedico);
                    if(medicoEscolhido == null){
                        System.out.println("Médico não encontrado no sistema!");
                        break;
                    }
                    System.out.print("Descrição da consulta:");
                    String desc = painel.nextLine();
                    System.out.print("Horário (ex: Segunda 10h):");
                    String horario = painel.nextLine();
                    //agendarConsulta(medicoEscolhido, pacienteEscolhido, desc, horario);//
                    break;
                case 4:
                    System.out.print("Nome do Paciente:");
                    String nomeHistorico = painel.nextLine();
                    Pacientes pHistorico = buscarPaciente(pacientes, nomeHistorico);
                    if(pHistorico == null){
                        System.out.println("paciente não encontrado no sistema!");
                        break;
                    }
                    System.out.println("Histórico de " + pHistorico.getNome() + ":");
                    for (Consulta c : pHistorico.getConsultas()){
                        System.out.println(c);
                    }
                    break;
                case 5:
                    System.out.println("Nome do paciente para internação:");
                    String nomeInt = painel.nextLine();
                    Pacientes pInt = buscarPaciente(pacientes, nomeInt);
                    if(pInt == null){
                        System.out.println("Paciente não encontrado no sistema!");
                        break;
                    }
                    System.out.println("Nome do médico responsável:");
                    String nomeMed = painel.nextLine();
                    Medicos mResp = buscarMedico(medicos, nomeMed);
                    if(mResp == null){
                        System.out.println("Médico não encontrado no sistema!");
                        break;
                    }
                    System.out.println("Digite a data da entrada, por favor!");
                    String dataEntrada = painel.nextLine();
                    System.out.println("Quarto: ");
                    String quarto = painel.nextLine();
                    System.out.println("Custo: ");
                    double custo = painel.nextDouble();
                    painel.nextLine();

                    Internacao internacao = new Internacao(pInt, mResp, dataEntrada, quarto, custo);
                    internacao.add(internacao);
                    System.out.println("Internação registrada com sucesso: " + internacao);
                    break;
            }

        }


    }

}
