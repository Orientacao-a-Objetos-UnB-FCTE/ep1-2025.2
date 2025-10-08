package br.com.lucasferreira.sh.Models;

import br.com.lucasferreira.sh.enums.TipoPlano;

import java.time.LocalDate;

public class PacienteComum extends Paciente{
    public PacienteComum(String nome, String cpf, PlanoDeSaude plano, boolean pcd, LocalDate dataNascimento){
        super(nome,cpf,plano,pcd,dataNascimento);
    }
    @Override
    public double calcularCustoConsulta(Consulta consulta) {
        return calcularCustoAposPlano(consulta);
    }

}
