package br.com.lucasferreira.sh;
import br.com.lucasferreira.sh.enums.TipoPlano;
import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private final String nome;
    private final String cpf;
    private TipoPlano plano;
    private boolean pcd;
    private final LocalDate dataNascimento; // usar LocalDate para idade nao desatualizar


    public Paciente(String nome, String cpf, TipoPlano plano, boolean pcd, LocalDate dataNascimento){
        this.nome = nome;
        this.cpf = cpf;
        this.plano = plano;
        this.pcd = pcd;
        this.dataNascimento = dataNascimento;
    }
    public TipoPlano getPlano(){
        return plano;
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
        return (getIdade()>=60 ||this.pcd || this.plano == TipoPlano.ESPECIAL);
    }
    public boolean isPcd(){
        return pcd;
    }
}
