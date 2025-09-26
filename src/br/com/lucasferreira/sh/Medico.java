package br.com.lucasferreira.sh;
import br.com.lucasferreira.sh.enums.Especialidade;

public class Medico {
    private String nome;
    private final String crm;
    private Especialidade especialidade;
    private double custoPorConsulta;
    public Medico(String nome, String crm, Especialidade especialidade,double custoPorConsulta){
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoPorConsulta = custoPorConsulta;
    }
    public String getNome(){
        return nome;
    }
}
