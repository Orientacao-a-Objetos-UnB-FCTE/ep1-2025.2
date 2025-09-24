package br.com.lucasferreira.sh;
import br.com.lucasferreira.sh.enums.TipoPlano;
import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private final String cpf;
    private TipoPlano plano;
    private boolean pcd;
    private LocalDate dataNascimento; // usar LocalDate para idade nao desatualizar


    public Paciente(String cpf, TipoPlano plano, boolean pcd, LocalDate dataNascimento){
        this.cpf = cpf;
        this.plano = plano;
        this.pcd = pcd;
        this.dataNascimento = dataNascimento;
    }
    public TipoPlano getPlano(){
        return plano;
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
