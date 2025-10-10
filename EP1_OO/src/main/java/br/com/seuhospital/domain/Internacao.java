package br.com.seuhospital.domain;

import java.time.LocalDateTime;

public class Internacao {
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String quarto;
    private double custoInternacao;
    private StatusInternacao status;

    public Internacao(Paciente paciente, Medico medicoResponsavel, LocalDateTime dataEntrada, String quarto) {
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto = quarto;
        this.status = StatusInternacao.Ativa;
        this.custoInternacao = 0.0; // vai ser calculado no final 
    }

    // Getters
    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public String getQuarto() {
        return quarto;
    }

    public double getCustoInternacao() {
        return custoInternacao;
    }

    public StatusInternacao getStatus() {
        return status;
    }

    // Setters
    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setCustoInternacao(double custoInternacao) {
        this.custoInternacao = custoInternacao;
    }

    public void setStatus(StatusInternacao status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Internacao{" +
               "paciente=" + paciente.getNome() +
               ", medicoResponsavel=" + medicoResponsavel.getNome() +
               ", dataEntrada=" + dataEntrada +
               (dataSaida != null ? ", dataSaida=" + dataSaida : "") +
               ", quarto=\'" + quarto + '\'' +
               ", custoInternacao=" + custoInternacao +
               ", status=" + status +
               '}'
               ;
    }
}
