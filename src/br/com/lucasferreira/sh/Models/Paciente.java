package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.TipoPlano;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

public class Paciente {
    private final String nome;
    private final String cpf;
    private TipoPlano plano;
    private boolean pcd;
    private final LocalDate dataNascimento; // usar LocalDate para idade nao desatualizar
    private  List<Consulta> historicoPaciente;
    public Paciente(String nome, String cpf, TipoPlano plano, boolean pcd, LocalDate dataNascimento){
        this.nome = nome;
        this.cpf = cpf;
        this.plano = plano;
        this.pcd = pcd;
        this.dataNascimento = dataNascimento;
        this.historicoPaciente =  new ArrayList<>();
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
    public List<Consulta> getHistoricoDoPaciente(Agenda agenda) {
        List<Consulta> consultasDoPaciente = new ArrayList<>();
        List<Consulta> historico = agenda.getHistorico(); // pega todas as consultas da agenda

        for (Consulta c : historico) {                  // percorre cada consulta
            if (c.getPaciente().equals(this)) {         // verifica se é o mesmo paciente
                consultasDoPaciente.add(c);            // adiciona à lista do paciente
            }
        }

        return consultasDoPaciente;                     // retorna só as consultas deste paciente
    }
}
