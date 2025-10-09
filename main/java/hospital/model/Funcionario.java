package hospital.model;

import java.time.LocalDate;

public abstract class Funcionario extends Pessoa {
    private double salario;
    
    public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salario) {
        super(nome, cpf, dataNascimento); //para chamar o construtor da ppessoa
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    @Override
    public String toString() {
        return super.toString() + "| Salário: R$" + salario;
    }
}