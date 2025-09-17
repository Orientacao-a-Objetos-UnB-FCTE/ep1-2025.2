public class Consulta {
    private Medicos medicos;
    private Pacientes paciente;
    private String descricao;
    public Consulta( Medicos medicos, Pacientes paciente, String descricao){
        this.paciente = paciente;
        this.medicos = medicos;
        this.descricao = descricao;
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
    public String toString(){
        return "Consulta com: " + medicos.getnome() + "às" +
    }
}
