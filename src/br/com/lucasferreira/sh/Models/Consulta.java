package br.com.lucasferreira.sh.Models;
import java.time.LocalDateTime;
public class Consulta implements Comparable<Consulta>{
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataHora;
    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora){
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
    }
    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    @Override
    public int compareTo(Consulta outra) {
        // Ordena do mais antigo para o mais recente
        return this.dataHora.compareTo(outra.dataHora);
    }
}