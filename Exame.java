public class Exame {
    public enum StatusExame{
        AGENDADO, REALIZADO, CANCELADO
    }
    private Pacientes paciente;
    private String tipo;
    private String dataHorario;
    private StatusExame status;

    public Exame(Pacientes paciente, String tipo, String dataHorario){
        this.paciente = paciente;
        this.tipo = tipo;
        this.dataHorario = dataHorario;
        this.status = StatusExame.AGENDADO;
    }

    public Pacientes getPaciente() {
        return paciente;
    }
    public String getTipo(){
        return tipo;
    }
    public String getDataHorario() {
        return dataHorario;
    }
    public StatusExame getStatus(){
        return status;
    }
    public void realizarExame(){
        this.status = StatusExame.REALIZADO;
    }
    public void cancelarExame(){
        this.status = StatusExame.CANCELADO;
    }
    public String toString(){
        return "Exame: " + tipo + " do senhor(a): " + paciente.getNome() + " Data/Horário: " + dataHorario + " Status: " + status;
    }
}
