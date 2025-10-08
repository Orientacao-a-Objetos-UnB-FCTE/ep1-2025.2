package br.com.lucasferreira.sh.Models;

import br.com.lucasferreira.sh.enums.Especialidade;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public abstract class Paciente {
    private final String nome;
    private final String cpf;
    private PlanoDeSaude plano;
    private final boolean pcd;
    private final LocalDate dataNascimento;
    public abstract double calcularCustoConsulta(Consulta consulta);

    public Paciente(String nome, String cpf, PlanoDeSaude plano, boolean pcd, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.plano = plano;
        this.pcd = pcd;
        this.dataNascimento = dataNascimento;
    }

    public PlanoDeSaude getPlano() {
        return this.plano;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isEspecial() {
        return (getIdade() >= 60 || this.pcd || getIdade() <= 12);
    }

    public boolean isPcd() {
        return pcd;
    }
    public List<Consulta> getHistoricoDoPaciente(Agenda agenda) {
        List<Consulta> consultasDoPaciente = new ArrayList<>();
        List<Consulta> todasAsConsultas = agenda.getConsultas();

        for (Consulta c : todasAsConsultas) {
            if (c.getPaciente().equals(this)) {
                consultasDoPaciente.add(c);
            }
        }
        return consultasDoPaciente;
    }
    protected double calcularCustoAposPlano(Consulta consulta) {
        Medico medico = consulta.getMedico();
        double custoBaseMedico = medico.getCustoPorConsulta();

        if (this.getPlano() != null) {
            Especialidade especialidade = medico.getEspecialidade();
            double descontoPlano = this.getPlano().getDescontoPorEspecialidade(especialidade);
            return custoBaseMedico * (1 - descontoPlano);
        } else {
            return custoBaseMedico;
        }
    }
    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }
}