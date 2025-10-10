package br.com.seuhospital.domain;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    private String nome;
    private String crm;
    private Especialidade especialidade;
    private double custoConsulta;
    private List<LocalDateTime> agendaHorarios;

    public Medico(String nome, String crm, Especialidade especialidade, double custoConsulta) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custoConsulta;
        this.agendaHorarios = new ArrayList<>();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public double getCustoConsulta() {
        return custoConsulta;
    }

    public List<LocalDateTime> getAgendaHorarios() {
        return agendaHorarios;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCustoConsulta(double custoConsulta) {
        this.custoConsulta = custoConsulta;
    }

    // metodos
    public void adicionarHorarioAgenda(LocalDateTime horario) {
        this.agendaHorarios.add(horario);
    }

    public void removerHorarioAgenda(LocalDateTime horario) {
        this.agendaHorarios.remove(horario);
    }

    @Override
    public String toString() {
        return "Medico{" +
               "nome=\'" + nome + '\'' +
               ", crm=\'" + crm + '\'' +
               ", especialidade=" + especialidade +
               ", custoConsulta=" + custoConsulta +
               '}'
               ;
    }
}
