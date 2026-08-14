package entities;
import java.util.ArrayList;
import java.util.List;


public class Medico extends Pessoa {
    
    private String CRM;
    private String Especialidade;
    private double CustoConsulta;
    private List<AgendamentoConsulta> Agenda;
    private int consultasRealizadas;
    private static int contadorIds = 1; 
    private int id;
    
    public Medico (String nome, String cpf, int idade, String CRM, String Especialidade, double CustoConsulta){
        super(nome, cpf, idade);
        this.CRM = CRM;
        this.Especialidade = Especialidade;
        this.CustoConsulta = CustoConsulta;
        this.Agenda = new ArrayList<>();
        this.consultasRealizadas = 0;
        this.id = contadorIds++;
    }
    
    public void adicionarHorarioAgenda(AgendamentoConsulta NovaConsulta){
        this.Agenda.add(NovaConsulta);
        System.out.println("Nova consulta adicionada na sua agenda");
       
        

    }
    public int getId(){
        return id;
    }


    public String getCRM(){
        return CRM;
    }

    public void exibirInformacoes(){
        System.out.println("Nome: " + getNome());
        System.out.println("CRM: " + getCRM());
        System.out.println("Especialidade: " + getEspecialidade());
        System.out.println("Custo da consulta: R$" + getCustoConsulta()+ "0");
        System.out.println("Agenda de horários:");
        getExibirAgendaMedico();;
        
    }


    public double getCustoConsulta(){
        return CustoConsulta;
    }

    public String getEspecialidade(){
        return Especialidade;
    }

    public List<AgendamentoConsulta> getAgenda(){
        return Agenda;
    }

    public int getConsultasRealizadas(){
        return consultasRealizadas;
    }

    public void getExibirAgendaMedico(){
        for (AgendamentoConsulta ac: Agenda){
           
            System.out.println("Nome do paciente: "+ ac.getPaciente().getNome());
            System.out.println("Data e hora: " + ac.getDatahora());
            System.out.println("Status: " + ac.getStatus());
            System.out.println("-----------------------------------");
            
        }
    }

    public void  incrementarConsultasRealizadas(){
        this.consultasRealizadas++;
    }
    
}