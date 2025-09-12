import java.util.ArrayList;
import java.util.List;

public class Pacientes implements usuariospac {
    private String Nome;
    private String Cpf;
    private byte Idade;
    private String Id;
    private List<String> HistoricoInternacoes;
    private List<String> HistoricoConsultas;

    public Pacientes(String Nome, String Cpf, byte Idade, String Id){
        this.Nome = Nome;
        this.Cpf = Cpf;
        this.Idade = Idade;
        this.Id = Id;
        this.HistoricoInternacoes = new ArrayList<>();
        this.HistoricoConsultas = new ArrayList<>();

    }
    @Override
    public String getNome() {
        return Nome;
    }
    @Override
    public byte getIdade() {
        return Idade;
    }
    @Override
    public String getId() {
        return Id;
    }
    @Override
    public String getCPF() {
        return Cpf;
    }
    @Override
    public String[] getHistoricoInternacoes() {
        return HistoricoInternacoes.toArray(new String[0]);
    }
    @Override
    public String[] getHistoricoConsultas() {
        return HistoricoConsultas.toArray(new String[0]);
    }
}
