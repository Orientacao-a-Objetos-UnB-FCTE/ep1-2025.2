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
    public String getNome() {
        return nome;
    }
    @Override
    public String getCrm() {
        return crm;
    }
    @Override
    public String getEspecialidade() {
        return Especialidade;
    }
    @Override
    public double getCustoDaConsulta() {
        return custoDaconsulta;
    }

    @Override
    public String[] getHistoricoDeHorario() {
        return agenda.toArray(new String[0]);
    }
    public Set<String> getAgendaDeConsulta(){
        return agenda;
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
