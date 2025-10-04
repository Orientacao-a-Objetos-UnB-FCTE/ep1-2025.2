package hospital.entidade;

import java.time.LocalDateTime;

public class Exame {
    public enum StatusExame{
        AGENDADO, REALIZADO, CANCELADO
    }
    private Pacientes paciente;
    private String tipo;
    private LocalDateTime dataHorario;
    private StatusExame status;

    public Exame(Pacientes paciente, String tipo, LocalDateTime dataHorario){
        this.paciente = paciente;
        this.tipo = tipo;
        this.dataHorario = dataHorario;
        this.status = StatusExame.AGENDADO;
    }
    public String toCSV(){
        return paciente.getCpf() + "," + tipo + "," + dataHorario + "," + status.name();
    }

    public Pacientes getPaciente() {
        return paciente;
    }
    public String getTipo(){
        return tipo;
    }
    public LocalDateTime getDataHorario() {
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
