import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Medicos implements usuariosmed {
    private String nome;
    private String crm;
    private String Especialidade;
    private double custoDaconsulta;
    private Set<String> agenda = new HashSet<>();
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

    @Override
    public String[] gethistoricoDehorario() {
        return agenda.toArray(new String[0]);
    }

    public Set<String> getAgendaDeConsulta(){
        return agenda;
    }
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
        historicoConsultas.add("Consulta com" + consulta.getMedicos().getnome() + "em" + consulta.getDatahorario());
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public boolean simDisponivel(String horario){
        return !agenda.contains(horario);
    }
    public void adicionarHorario(String horario){
        agenda.add(horario);
    }
    public String toString(){
        return "Doutor(a):" + nome + "CRM:" + crm + "Especialidade:" + Especialidade + "Valor da consulta:" + custoDaconsulta;
    }

}
