

public class Consulta {
    public enum StatusConsulta{
        AGENDADA, CONCLUIDA, CANCELADA}
    private Medicos medicos;
    private Pacientes paciente;
    private String descricao;
    private String dataHorario;
    private StatusConsulta status;
    public Consulta( Medicos medicos, Pacientes paciente, String descricao, String dataHorario){
        this.paciente = paciente;
        this.medicos = medicos;
        this.descricao = descricao;
        this.dataHorario = dataHorario;
        this.status = StatusConsulta.AGENDADA;
    }
    public Pacientes getPacientes() {
        return paciente;
    }
    public Medicos getMedicos() {
        return medicos;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getDataHorario() {
        return dataHorario;
    }
    public void concluirConsulta(){
        status = StatusConsulta.CONCLUIDA;
    }
    public void cancelarConsulta(){
        status = StatusConsulta.CANCELADA;
    }
    public StatusConsulta getStatus() {
        return status;
    }
    public String toString(){
        return "/Consulta com: " + medicos.getNome() + "Paciente-->" + paciente.getNome() + "às" + dataHorario + "para" + descricao + "/" + "que está" + status;
    }
}
