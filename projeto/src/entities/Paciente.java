package entities;

import java.util.ArrayList;
import java.util.List;
public class Paciente extends Pessoa{
    private List<AgendamentoConsulta> historicoconsultas;
    private List<Internacao> historicointernacoes;
    private boolean temPlanoDesaude;
    private static int contadorIds = 1; 
    private int id;

    

    public Paciente(String nome, String cpf, int idade, boolean temPlanoDesaude){
        super(nome,cpf,idade);
        this.temPlanoDesaude = temPlanoDesaude;
        this.historicoconsultas = new ArrayList<>();
        this.historicointernacoes = new ArrayList<>();
        this.id = contadorIds++;


    }

    public int getId(){
        return id;
    }


    public void adicionarConsulta(AgendamentoConsulta consulta){
        this.historicoconsultas.add(consulta);
        System.out.println("Consulta de "  +  getNome()  + " " + "adicionada");
        
       
    }

    public void CancelarConsulta(AgendamentoConsulta consulta){
        this.historicoconsultas.remove(consulta);
        System.out.println("sua consulta "+ consulta + " foi cancelada");
    }

    public void adicionarinternacao(Internacao internacao){
        this.historicointernacoes.add(internacao);
        System.out.println("Internacao " + internacao + " adicionada");
        
    }

    public void Cancelarinternacao(Internacao internacao){
         this.historicointernacoes.remove(internacao);
        System.out.println("Internacao " + internacao + " cancelada");
    }

    public void getHistoricConsultas(){
        for(AgendamentoConsulta ac:  historicoconsultas) {
            System.out.println("Nome do paciente: "+ ac.getPaciente().getNome());
            System.out.println("Nome do médico: " + ac.getMedico().getNome());
            System.out.println("Consultório: 0" + ac.getLocal().getNumero()); 
            System.out.println("Status: " + ac.getStatus());
            System.out.println("Data e hora: " + ac.getDatahora());
            System.out.println("---------------------------------");
        }
        
    }
    
    public void getHistoricointernacoes(){
        for (Internacao ac: historicointernacoes){
            System.out.println("Nome do paciente: " + ac.getPaciente().getNome());
            System.out.println("Medico responsável: " + ac.getMedicoResponsavel().getNome());
            System.out.println("Data de entrada: " + ac.getdataEntrada());
            System.out.println("Data de saída: " + ac.getdataSaida());
            System.out.println("Quarto: " + ac.getQuarto().getNumero());
            System.out.println("Custo: R$" + ac.getCustoInternacao() + "0");
            System.out.println("----------------------------------");
        }
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Idade: " + getIdade() + " anos");
        System.out.println("Histórico de consultas:");
        getHistoricConsultas();
        System.out.println("Histórico de internações:" );
        getHistoricointernacoes();

    }

    public boolean temPlanoSaude() {
        return temPlanoDesaude;
    }


   
}
