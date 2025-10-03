package hospital.entidade;

import java.time.LocalDate;

public class Internacao {
    private Pacientes paciente;
    private Medicos medicoResponsavel;
    private LocalDate dataEntrada;
    private LocalDate dataBaixa;
    private String quarto;
    private double custo;
    private boolean ativa;

    public Internacao(Pacientes paciente, Medicos medicoResponsavel, LocalDate dataEntrada, String quarto, double custo){
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto =quarto;
        this.custo = custo;
        this.ativa =true;
    }
    public void liberarInternacao(LocalDate dataBaixa){
        this.dataBaixa = dataBaixa;
        this.ativa = false;
    }
    public boolean isAtiva(){
        return ativa;
    }

    public double getCusto() {
        return custo;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public String getQuarto() {
        return quarto;
    }


    public Medicos getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }
    public Pacientes getPaciente() {
        return paciente;
    }
    public String toString(){
        return "Situação da internação do paciente:" + paciente.getNome() + " no quarto:" + quarto + " no dia: " + dataEntrada + " Valor: R$ " + String.format("%.2f", custo);
    }

    }

