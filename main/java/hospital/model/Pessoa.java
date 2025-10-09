package hospital.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe base para representar pessoas no sistema hospitalar.
 * Pode ser usada por Paciente, Médico, Funcionário etc.
 */
public class Pessoa {
    private String nome;
    private String documento; // CPF, CRM, etc.
    private int idade;

    /**
     * Construtor padrão - usa idade diretamente.
     */
    public Pessoa(String nome, String documento, int idade) {
        this.nome = nome;
        this.documento = documento;
        this.idade = idade;
    }

    /**
     * Construtor alternativo - calcula idade com base na data de nascimento.
     */
    public Pessoa(String nome, String documento, LocalDate dataNascimento) {
        this.nome = nome;
        this.documento = documento;
        this.idade = calcularIdade(dataNascimento);
    }

    /**
     * Calcula idade a partir da data de nascimento.
     */
    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    // ========================
    // Getters e Setters
    // ========================

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + " (Documento: " + documento + ", Idade: " + idade + ")";
    }
}
