import java.util.ArrayList;
import java.util.List;

public class Medicos implements usuariosmed {
    private String nome;
    private String crm;
    private String Especialidade;
    private String custoDaconsulta;
    private String agendaDeconsulta;
    private List<String>historicoDehorario;
    private List<Consulta> consultas;

    public Medicos(String nome, String crm, String Especialidade, String custaDaconsulta, String agendaDeconsulta){
        this.nome=nome;
        this.crm=crm;
        this.Especialidade=Especialidade;
        this.custoDaconsulta=custaDaconsulta;
        this.agendaDeconsulta=agendaDeconsulta;
        this.historicoDehorario= new ArrayList<>();
        this.consultas = new ArrayList<>();

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
    public String getcustoDaconsulta() {
        return custoDaconsulta;
    }
    @Override
    public String getagendaDeconsulta() {
        return agendaDeconsulta;
    }
    @Override
    public String[] gethistoricoDehorario() {
        return historicoDehorario.toArray(new String[0]);
    }
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void adicionarHorario(String horario){
        historicoDehorario.add(horario);
    }
    public String toString(){
        return "Doutor(a):" + nome + "CRM:" + crm + "Especialidade:" + Especialidade + "Valor da consulta:" + custoDaconsulta;
    }

}
