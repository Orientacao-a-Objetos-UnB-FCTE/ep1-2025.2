import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Medicos implements MedicosInterface {
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private Set<String> agenda = new HashSet<>();
    private List<Consulta> consultas = new ArrayList<>();
    private List<String> horariosDisponiveis = new ArrayList<>();

    public Medicos(String nome, String crm, String especialidade, double custoConsulta){
        this.nome=nome;
        this.crm=crm;
        this.especialidade=especialidade;
        this.custoConsulta =custoConsulta;

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
        return especialidade;
    }
    @Override
    public double getCustoConsulta() {
        return custoConsulta;
    }

    @Override
    public List<String> getHistoricoHorario() {
        return new ArrayList<>(agenda);
    }

    @Override
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
        agenda.add(consulta.getDataHorario());
    }
    public void adicionarHorario(String horario) {
        horariosDisponiveis.add(horario);
    }
    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public Set<String> getAgendaConsulta(){
        return agenda;
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public boolean simDisponivel(String horario){
        return !agenda.contains(horario);

    }
    public String toString(){
        return "Doutor(a):" + nome + " |CRM: " + crm + " |Especialidade: " + especialidade + "|Valor da consulta: " + custoConsulta;
    }

}
