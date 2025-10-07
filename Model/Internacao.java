import java.time.LocalDate;

public class Internacao {
    private final Paciente paciente;
    private final Medico medicoResponsavel;
    private Quarto quarto;
    private final LocalDate dataInternacao;
    private String periodoEstimado;
    private boolean ativa;

    public Internacao(Paciente paciente, Medico medicoResponsavel, Quarto quarto, String periodoEstimado) {
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.quarto = quarto;
        this.dataInternacao = LocalDate.now();
        this.periodoEstimado = periodoEstimado;
        this.ativa = true;
        this.quarto.ocuparQuarto();
    }

    // Getters e Setters
    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public LocalDate getDataInternacao() {
        return dataInternacao;
    }

    public String getPeriodoEstimado() {
        return periodoEstimado;
    }

    public void setPeriodoEstimado(String periodoEstimado) {
        this.periodoEstimado = periodoEstimado;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void darAlta() {
        this.ativa = false;
        this.quarto.liberarQuarto();
    }

    @Override
    public String toString() {
        return "Internacao{" +
                "paciente=" + paciente.getNome() +
                ", medicoResponsavel=" + medicoResponsavel.getNome() +
                ", quarto=" + quarto.getNumero() +
                ", dataInternacao=" + dataInternacao +
                ", periodoEstimado='" + periodoEstimado + '\'' +
                ", ativa=" + ativa +
                '}';
    }
}