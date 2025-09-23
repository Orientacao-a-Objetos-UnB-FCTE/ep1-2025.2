

public class Consulta {
    public enum StatusConsulta{
        AGENDADA, CONCLUIDA, CANCELADA}
    private Medicos medicos;
    private Pacientes paciente;
    private String descricao;
    private String datahorario;
    private StatusConsulta status;
    public Consulta( Medicos medicos, Pacientes paciente, String descricao, String datahorario){
        this.paciente = paciente;
        this.medicos = medicos;
        this.descricao = descricao;
        this.datahorario = datahorario;
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
    public String getDatahorario() {
        return datahorario;
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
        return "/Consulta com: " + medicos.getNome() + "Paciente-->" + paciente.getNome() + "às" + datahorario + "para" + descricao + "/" + "que está" + status;
    }
}
