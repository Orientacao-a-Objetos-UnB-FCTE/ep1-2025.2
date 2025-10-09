package hospital.model;

import java.time.LocalDateTime;

public class Internacao {
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private Quarto quarto;
    private double custo;

    public Internacao(Paciente paciente, Medico medicoResponsavel, LocalDateTime dataEntrada, Quarto quarto, double custo) {
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto = quarto;
        this.custo = custo;
    }

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

    public Quarto getQuarto() {
        return quarto;
    }

    public double getCusto() {
        return custo;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
        quarto.setOcupado(false);
    }

    @Override
    public String toString() {
        return paciente.getNome() + " | Quarto: " + quarto.getNumero() +
                " | Entrada: " + dataEntrada +
                " | Saída: " + (dataSaida == null ? "Ainda internado" : dataSaida);
    }
}
