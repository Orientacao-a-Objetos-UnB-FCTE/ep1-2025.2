package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.TipoPlano;

import java.time.LocalDate;

public class PacienteEspecial extends Paciente{
    private static final double descontoEspecial = 0.15;
    public PacienteEspecial(String nome, String cpf, PlanoDeSaude plano, boolean pcd, LocalDate dataNascimento){
        super(nome,cpf,plano,pcd,dataNascimento);
    }
    @Override
    public double calcularCustoConsulta(Consulta consulta) {
        double custoAposPlano = super.calcularCustoAposPlano(consulta);
        double custoFinal = custoAposPlano*(1-descontoEspecial);
        System.out.println("INFO: Desconto adicional de " + (descontoEspecial * 100) + "% aplicado para paciente especial: " + this.getNome());
        return custoFinal;
    }

}
