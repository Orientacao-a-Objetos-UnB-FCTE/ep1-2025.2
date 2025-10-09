package hospital.model;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {
    private String especialidade;
    private double custoConsulta;
    private List<Consulta> consultas = new ArrayList<>();

    public Medico(String nome, String crm, String especialidade, double custoConsulta) {
        super(nome, crm, 0);
        this.especialidade = especialidade;
        this.custoConsulta = custoConsulta;
    }

    public String getEspecialidade() { return especialidade; }
    public double getCustoConsulta() { return custoConsulta; }
    public List<Consulta> getConsultas() { return consultas; }
    public void adicionarConsulta(Consulta c) { consultas.add(c); }
}
