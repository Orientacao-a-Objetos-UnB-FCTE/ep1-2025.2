package br.com.seuhospital.domain;

import java.time.LocalDateTime;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String local;
    private StatusConsulta status;
    private String diagnostico;
    private String prescricao;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String local) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        this.status = StatusConsulta.Agendada;
    }

    // Getters
    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getLocal() {
        return local;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    // Setters
    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    @Override
    public String toString() {
        return "Consulta{" +
               "paciente=" + paciente.getNome() +
               ", medico=" + medico.getNome() +
               ", dataHora=" + dataHora +
               ", local=\'" + local + '\'' +
               ", status=" + status +
               (diagnostico != null ? ", diagnostico=\'" + diagnostico + '\'' : "") +
               (prescricao != null ? ", prescricao=\'" + prescricao + '\'' : "") +
               '}'
               ;
    }
}
