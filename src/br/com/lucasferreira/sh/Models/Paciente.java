package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.Especialidade;
import br.com.lucasferreira.sh.enums.TipoPlano;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

public  abstract class Paciente {
    private final String nome;
    private final String cpf;
    private PlanoDeSaude plano;
    private boolean pcd;
    private final LocalDate dataNascimento; // usar LocalDate para idade nao desatualizar
    private  List<Consulta> historicoPaciente;
    public abstract double calcularCustoConsulta(Consulta consulta);
    public Paciente(String nome, String cpf, PlanoDeSaude plano, boolean pcd, LocalDate dataNascimento){
        this.nome = nome;
        this.cpf = cpf;
        this.plano = plano;
        this.pcd = pcd;
        this.dataNascimento = dataNascimento;
        this.historicoPaciente =  new ArrayList<>();
    }

    public PlanoDeSaude getPlano(){
        return this.plano;
    }
    public String getNome(){
        return nome;
    }
    public int getIdade(){
        return Period.between(this.dataNascimento,LocalDate.now()).getYears();
    }
    public String getCpf(){
        return cpf;
    }
    public boolean isEspecial(){
        return (getIdade()>=60 ||this.pcd || getIdade()<=12);
    }
    public boolean isPcd(){
        return pcd;
    }
    public List<Consulta> getHistoricoDoPaciente(Agenda agenda) {
        List<Consulta> consultasDoPaciente = new ArrayList<>();
        List<Consulta> historico = agenda.getHistorico();

        for (Consulta c : historico) {
            if (c.getPaciente().equals(this)) {
                consultasDoPaciente.add(c);
            }
        }

        return consultasDoPaciente;
    }
    protected  double calcularCustoAposPlano(Consulta consulta){
        Medico medico = consulta.getMedico();
        double custoBaseMedico = medico.getCustoPorConsulta();
        if(this.getPlano() != null){
            Especialidade especialidade = medico.getEspecialidade();
            double descontoPlano = this.getPlano().getDescontoPorEspecialidade(especialidade);
            return custoBaseMedico * (1 - descontoPlano);
        }
        else{
           return custoBaseMedico;
        }
    }
}
