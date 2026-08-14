package entities;
import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

//commit teste



public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) throws Exception {
        
        
         Paciente p1 = new Paciente("Cauã Mendes", "05418394", 19, false);
         Paciente p2 = new Paciente("Maria Eduarda", "040430", 30, false);
         Paciente pEspecial = new Paciente("Eduardo", "3049340", 59, true);
         Medico m1 = new Medico("Dr.Rodrigo", "048874387", 40, "3245", "Ortopedista", 400);
         Medico m2 = new Medico("Dr.Amanda", "394883", 35, "3456", "Pediatria", 700);
         Medico m3 = new Medico("Dr.Ricardo", "394883", 35, "3456", "Pediatria", 700);



         LocalDateTime manha = LocalDateTime.of(2025, 4, 17, 8, 0, 0);
        LocalDateTime tarde = LocalDateTime.of(2025, 4, 17, 17, 0, 0);
        Quarto ala01 = new Quarto(01);
        Quarto ala02 = new Quarto(02);
        Quarto ala03 = new Quarto(03);
        Quarto ala04 = new Quarto(04);


         AgendamentoConsulta agendamento1 = new AgendamentoConsulta(p1, m1, tarde, ala02, "Agendada");
         AgendamentoConsulta agendamento2 = new AgendamentoConsulta(p2, m2, manha, ala01, "Agendada");
         AgendamentoConsulta agendamento3 = new AgendamentoConsulta(pEspecial, m2, tarde, ala03, "Agendada");
         AgendamentoConsulta agendamento4 = new AgendamentoConsulta(p1, m3, manha, ala02, "Agendada");

            
       

        Hospital hospital = new Hospital();
        
       

        

        while (true){
                
            System.out.println("------ SISTEMA HOSPITALAR------");
            System.out.println("1. Pacientes");
            System.out.println("2. Médicos");
            System.out.println("3. Consultas");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");


           String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    PacienteMenu(hospital);
                    break;
                case "2":
                    MedicoMenu(hospital);
                    break;

                case "3":
                    ConsultaMenu(hospital);
                    break;
                case "4":
                    System.out.println("Saindo do sistema...");
                    return; 
            
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        }
            
    } 
        
    
    private static void PacienteMenu(Hospital hospital){
        while (true) {
            System.out.println("\n-- PACIENTES --");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar pacientes");
            System.out.println("3 - Detalhar pacientes");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");    
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    RegistrarPaciente(hospital);
                    break;

                case "2":
                    hospital.PacientesCadastrados(hospital);
                    break;

                case "3":
                
                    break;
                case "0":
                    return; 
            
                default:
                    
                    break;
            }
        }
    } 
    
    private static void RegistrarPaciente(Hospital hospital){
         System.out.println("\n-- Cadastrar paciente --");
         System.out.print("Nome: ");
         String nome = sc.nextLine().trim();
         System.out.print("Documento (CPF/RG): ");
         String cpf = sc.nextLine().trim();
         System.out.println("Idade: ");
         int idade =  readIntOrDefault(-1);
         System.out.println("Tem plano de saúde? ");
         boolean temPlanoDesaude = sc.nextLine().trim().equalsIgnoreCase("s");

         Paciente p = new Paciente(nome, cpf, idade, temPlanoDesaude);
         hospital.CadastrarPaciente(p);
         System.out.println("Paciente " + p.getNome() + " cadastrado(a) (ID " + p.getId() + ")");
    }
    
    private static void MedicoMenu(Hospital hospital){
        while (true) {
            System.out.println("\n-- MÉDICOS --");
            System.out.println("1 - Cadastrar médico");
            System.out.println("2 - Listar médicos");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            String opt = sc.nextLine().trim();
            
            switch (opt) {
                case "1":
                    RegistrarMedico(hospital);
                    break;

                case "2":
                    hospital.MedicosCadastrados(hospital);
                    break;
                case "0":
                    return; // Volta para o menu principal
            
                default:
                    // ...
                    break;
            }
        }
    } 
    
    private static void RegistrarMedico(Hospital hospital){
        System.out.println("\n-- Cadastrar médico --");
        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        System.out.println("Documento (CPF/RG): ");
        String cpf = sc.nextLine().trim();
        System.out.println("Idade: ");
        int idade =  readIntOrDefault(-1);
        System.out.println("CRM: ");
        String CRM = sc.nextLine().trim();
        System.out.println("Especialidade: ");
        String Especialidade = sc.nextLine().trim();
        System.out.println("Valor da consulta:");
        double CustoConsulta = readDoubleOrDefault(0.0);

        Medico m = new Medico(nome, cpf, idade, CRM, Especialidade, CustoConsulta);

        hospital.CadastrarMedico(m);
        System.out.println("Médico(a): " + m.getNome() + " cadastrado(a)(ID " + m.getId() + ")");
    }   




      
        


    



    private static void ConsultaMenu(Hospital hospital){
        while (true) {
            System.out.println("\n-- CONSULTAS / AGENDAMENTOS --");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Listar consultas");
           
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    AgendarConsulta(hospital);
                    break;
                case "2":
                    
                    break;
                
                case "3":


                    break;


                case "0": return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void  AgendarConsulta(Hospital hospital) {
        System.out.println("\n-- Agendar consulta --");
        System.out.println("Lista de pacientes:");
        try { hospital.getPacientesCadastrados(); } catch (Throwable t) {}

        System.out.print("Digite o nome (ou ID) do paciente: ");
        String pacienteIdOrName = sc.nextLine().trim();

        System.out.println("Lista de médicos:");
        try { hospital.getMedicosCadastrados(); } catch (Throwable t) {}

        System.out.print("Digite o nome (ou ID) do médico: ");
        String medicoIdOrName = sc.nextLine().trim();

        System.out.print("Data e hora (YYYY-MM-DD HH:MM): ");
        String dt = sc.nextLine().trim();
        LocalDateTime dataHora;
        try {
            dataHora = LocalDateTime.parse(dt, DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido. Use: yyyy-MM-dd HH:mm");
            return;
        }

          Paciente paciente = null;
        Medico medico = null;
        try {
            
            paciente = hospital.findPacienteByIdentifier(pacienteIdOrName);
        } catch (Throwable t) {}
        try {
            medico = hospital.findMedicoByIdentifier(medicoIdOrName);
        } catch (Throwable t) {}

        if (paciente == null || medico == null) {
            System.out.println("Paciente ou médico não encontrados automaticamente.");
            System.out.println("Você precisa adaptar a busca em scheduleAppointment() para usar os métodos do seu Hospital.");
            System.out.println("Se preferir, digite manualmente o nome do paciente e do médico e eu mostro como procurar nas listas.");
            return;
        }

        AgendamentoConsulta ag = new AgendamentoConsulta(paciente, medico, dataHora, null, "Agendada");
        try {
            hospital.CadastrarConsulta(ag); 
            System.out.println("Consulta agendada com sucesso!");
        } catch (Throwable t) {
            System.out.println("Não foi possível cadastrar a consulta automaticamente. Verifique se Hospital tem método para cadastrar consultas.");
        }
    }
    

    private static int readIntOrDefault(int def) {
        String s = sc.nextLine().trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }


     private static double readDoubleOrDefault(double def) {
        String s = sc.nextLine().trim();
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }



    
} 