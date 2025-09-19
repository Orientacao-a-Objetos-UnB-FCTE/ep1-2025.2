import java.util.ArrayList;
import java.util.List;

public class Medicos implements usuariosmed {
    private String nome;
    private String crm;
    private String Especialidade;
    private double custoDaconsulta;
    private List<String> agenda = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    public Medicos(String nome, String crm, String Especialidade, double custaDaconsulta){
        this.nome=nome;
        this.crm=crm;
        this.Especialidade=Especialidade;
        this.custoDaconsulta=custaDaconsulta;

    }
    @Override
    public String getnome() {
        return nome;
    }
    @Override
    public String getcrm() {
        return crm;
    }
    @Override
    public String getEspecialidade() {
        return Especialidade;
    }
    @Override
    public double getcustoDaconsulta() {
        return custoDaconsulta;
    }
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void adicionarHorario(String horario){
        agenda.add(horario);
    }
    public String toString(){
        return "Doutor(a):" + nome + "CRM:" + crm + "Especialidade:" + Especialidade + "Valor da consulta:" + custoDaconsulta;
    }

}
