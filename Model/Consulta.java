import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String local;
    private StatusConsulta status; 
    private String diagnostico;
    private String prescricao;

    public Consulta(int id, Paciente paciente, Medico medico, LocalDateTime dataHora, String local) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        this.status = StatusConsulta.AGENDADA; // ✅ Corrigido: usa o enum
        this.diagnostico = ""; // ✅ Inicializado como vazio
        this.prescricao = "";  // ✅ Inicializado como vazio
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public StatusConsulta getStatus() { return status; }
    public void setStatus(StatusConsulta status) { this.status = status; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getPrescricao() { return prescricao; }
    public void setPrescricao(String prescricao) { this.prescricao = prescricao; }

    // ✅ Método para concluir a consulta CORRIGIDO
    public void concluir(String diagnostico, String prescricao) {
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.status = StatusConsulta.CONCLUIDA; // ✅ Usa o enum
    }

    // ✅ Método para cancelar a consulta CORRIGIDO
    public void cancelar() {
        this.status = StatusConsulta.CANCELADA; // ✅ Usa o enum
    }

    // ✅ Método adicional: reagendar consulta
    public void reagendar(LocalDateTime novaDataHora, String novoLocal) {
        if (this.status == StatusConsulta.CONCLUIDA) {
            throw new IllegalStateException("Não é possível reagendar uma consulta concluída");
        }
        this.dataHora = novaDataHora;
        this.local = novoLocal;
        this.status = StatusConsulta.AGENDADA;
    }

    // ✅ Método para verificar se a consulta está ativa
    public boolean isAtiva() {
        return this.status == StatusConsulta.AGENDADA;
    }

    // ✅ Método toString para debug
    @Override
    public String toString() {
        return String.format("Consulta #%d: %s com %s em %s - Status: %s",
                id, paciente.getNome(), medico.getNome(), 
                dataHora.toString(), status.getDisplayName());
    }
}