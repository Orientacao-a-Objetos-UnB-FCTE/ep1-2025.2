public class Consulta {
    private Medicos medicos;
    private Pacientes paciente;
    private String descricao;
    private String datahorario;
    public Consulta( Medicos medicos, Pacientes paciente, String descricao, String datahorario){
        this.paciente = paciente;
        this.medicos = medicos;
        this.descricao = descricao;
        this.datahorario = datahorario;
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
    public String toString(){
        return "/Consulta com: " + medicos.getnome() + "Paciente-->" + paciente.getNome() + "às" + datahorario + "para" + descricao + "/";
    }
}
