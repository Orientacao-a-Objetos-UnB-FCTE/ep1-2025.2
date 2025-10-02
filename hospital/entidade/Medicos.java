package hospital.entidade;

import hospital.interfac.MedicosInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Medicos implements MedicosInterface {
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private List<Consulta> consultas = new ArrayList<>();

    public Medicos(String nome, String crm, String especialidade, double custoConsulta){
        this.nome = nome;
        this.crm=crm;
        this.especialidade =especialidade;
        this.custoConsulta =custoConsulta;
    }
    public String getNome() {
        return nome; }
    public String getCrm() {
        return crm; }
    public String getEspecialidade() {
        return especialidade; }
    public double getCustoConsulta() {
        return custoConsulta; }

    @Override
    public List<Consulta> getHistoricoHorario() {
        return List.of();
    }
    @Override
    public List<Consulta> getHistoricoConsultas() {
        return Collections.unmodifiableList(consultas);
    }
    public boolean simDisponivel(LocalDateTime dataHora) {
        for (Consulta consulta : consultas) {
            if (consulta.getDataHorario().equals(dataHora)) {
                return false;
            }
        }
        return true;
    }
    public List<LocalDateTime> getHorariosAgendados() {
        List<LocalDateTime> horaries = new ArrayList<>();
        for (Consulta consulta : consultas) {
            horaries.add(consulta.getDataHorario());
        }
        return Collections.unmodifiableList(horaries);
    }
    @Override
    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
    }
    @Override
    public String toString(){
        return "Doutor(a): " + nome + " | CRM: " + crm + " | Especialidade: " + especialidade + " | Valor da consulta: R$ " + String.format("%.2f", custoConsulta);
    }
}