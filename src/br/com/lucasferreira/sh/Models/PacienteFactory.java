package br.com.lucasferreira.sh.Models;

import java.time.LocalDate;
import java.time.Period;

public class PacienteFactory {

    public static Paciente criarPaciente(String nome, String cpf, PlanoDeSaude plano, boolean pcd, LocalDate dataNascimento) {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        boolean condicaoEspecial = (idade >= 60 || idade <= 12 || pcd);

        if (condicaoEspecial) {
            return new PacienteEspecial(nome, cpf, plano, pcd, dataNascimento);
        } else {
            return new PacienteComum(nome, cpf, plano, pcd, dataNascimento);
        }
    }
}