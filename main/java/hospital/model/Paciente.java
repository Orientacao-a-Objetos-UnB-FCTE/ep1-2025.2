package hospital.model;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa {
    protected List<Consulta> consultas = new ArrayList<>();
    protected List<Internacao> internacoes = new ArrayList<>();

    public Paciente(String nome, String cpf, int idade) {
        super(nome, cpf, idade);
    }

    public void adicionarConsulta(Consulta c) { consultas.add(c); }
    public void adicionarInternacao(Internacao i) { internacoes.add(i); }
    public List<Consulta> getConsultas() { return consultas; }
    public List<Internacao> getInternacoes() { return internacoes; }
}
