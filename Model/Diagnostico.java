public class Diagnostico {
    private String descricao;
    private Medico medico;
    private Paciente paciente;
    private Consulta consulta;

    public Diagnostico(String descricao, Medico medico, Paciente paciente, Consulta consulta) {
        this.descricao = descricao;
        this.medico = medico;
        this.paciente = paciente;
        this.consulta = consulta;
    }

    // Getters e Setters
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
}