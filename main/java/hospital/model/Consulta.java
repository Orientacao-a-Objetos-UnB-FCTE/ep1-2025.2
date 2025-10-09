package hospital.model;

import java.time.LocalDateTime;

public class Consulta {
    public enum Status { AGENDADA, CONCLUIDA, CANCELADA }

    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String local;
    private double valor;
    private Status status;
    private String diagnostico;
    private String prescricao;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String local, double valor) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        this.valor = valor;
        this.status = Status.AGENDADA;
        this.diagnostico = "";
        this.prescricao = "";
    }

    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getLocal() { return local; }
    public double getValor() { return valor; }
    public Status getStatus() { return status; }
    public String getDiagnostico() { return diagnostico; }
    public String getPrescricao() { return prescricao; }

    public void setStatus(Status status) { this.status = status; }

    public void registrarDiagnostico(String diagnostico, String prescricao) {
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.status = Status.CONCLUIDA;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | R$ %.2f | %s",
            dataHora.toString(),
            paciente.getNome(),
            medico.getNome(),
            local,
            valor,
            status.name());
    }
}
