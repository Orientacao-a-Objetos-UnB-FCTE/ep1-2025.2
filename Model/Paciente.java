import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa {
    private List<Consulta> historicoConsultas;
    private List<Internacao> historicoInternacoes;
    private PlanoSaude plano;

    public Paciente(String nome, String cpf, Integer idade, Genero genero, String telefone, PlanoSaude plano) {
        super(nome, cpf, idade, genero, telefone);
        this.historicoConsultas = new ArrayList<>();
        this.historicoInternacoes = new ArrayList<>();
        this.plano = plano;
    }

    // Getters e Setters
    public List<Consulta> getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void setHistoricoConsultas(List<Consulta> historicoConsultas) {
        this.historicoConsultas = historicoConsultas;
    }

    public List<Internacao> getHistoricoInternacoes() {
        return historicoInternacoes;
    }

    public void setHistoricoInternacoes(List<Internacao> historicoInternacoes) {
        this.historicoInternacoes = historicoInternacoes;
    }

    public PlanoSaude getPlano() {
        return plano;
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }
}