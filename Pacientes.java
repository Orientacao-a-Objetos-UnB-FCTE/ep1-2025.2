import java.util.ArrayList;
import java.util.List;

public class Pacientes implements PacienteInterface {
    private String nome;
    private String cpf;
    private int idade;
    private String id;
    private List<String> historicoInternacoes;
    private List<String> historicoConsultas;
    private List<Consulta> consultas;

    public Pacientes(String nome, String cpf, int idade, String id){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.id = id;
        this.historicoConsultas = new ArrayList<>();
        this.historicoInternacoes = new ArrayList<>();
        this.consultas = new ArrayList<>();

    }
    @Override
    public String getNome() {
        return nome;
    }
    @Override
    public int getIdade() {
        return idade;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getCpf() {
        return cpf;
    }
    @Override
    public List<String> getHistoricoInternacoes() {
        return historicoInternacoes;
    }
    @Override
    public List<String> getHistoricoConsultas() {
        return historicoConsultas;
    }
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
        historicoConsultas.add("Consulta com " + consulta.getMedicos().getNome() + " no dia " + consulta.getDataHorario());
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void adicionarInternacao(String internacao){
        historicoInternacoes.add(internacao);
    }
    public String toString(){
        return " Paciente: "+  nome  + " |CPF: " +  cpf  + " |Idade: " +  idade  + " |Identificação: " +  id;
    }

}
