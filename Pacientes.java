import java.util.ArrayList;
import java.util.List;

public class Pacientes implements usuariospac {
    private String nome;
    private String cpf;
    private byte idade;
    private String id;
    private List<String> historicoInternacoes;
    private List<String> historicoConsultas;
    private List<Consulta> consultas;

    public Pacientes(String Nome, String Cpf, byte Idade, String Id){
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
    public byte getIdade() {
        return idade;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getCPF() {
        return cpf;
    }
    @Override
    public String[] getHistoricoInternacoes() {
        return historicoInternacoes.toArray(new String[0]);
    }
    @Override
    public String[] getHistoricoConsultas() {
        return historicoConsultas.toArray(new String[0]);
    }
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
        historicoConsultas.add("Consulta com" + consulta.getMedicos().getnome() + "em" + consulta.getDatahorario());
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void adicionarInternacao(String internacao){
        historicoInternacoes.add(internacao);
    }
    public String toString(){
        return "/Paciente:"+ nome + "CPF:" + cpf + "Idade:" + idade + "Identificação:" + id +"/";
    }

}
