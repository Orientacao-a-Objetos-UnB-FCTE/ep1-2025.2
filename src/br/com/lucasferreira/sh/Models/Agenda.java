package br.com.lucasferreira.sh.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {
    private List<Consulta> consultas;

    public Agenda() {
        this.consultas = new ArrayList<>();
    }

    public void adicionarConsulta(Consulta novaConsulta) {
        this.consultas.add(novaConsulta);
    }

    public boolean isHorarioDisponivel(LocalDateTime horario, Medico medico, Paciente paciente) {
        for (Consulta consultaExistente : this.consultas) {
            if (consultaExistente.getDataHora().isEqual(horario)) {
                if (consultaExistente.getMedico().equals(medico)) {
                    System.out.println("LOG: Conflito de agenda. O médico " + medico.getNome() + " já tem uma consulta neste horário.");
                    return false;
                }
                if (consultaExistente.getPaciente().equals(paciente)) {
                    System.out.println("LOG: Conflito de agenda. O paciente " + paciente.getNome() + " já tem uma consulta neste horário.");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean marcarConsulta(Consulta marcar) {
        if (isHorarioDisponivel(marcar.getDataHora(), marcar.getMedico(), marcar.getPaciente())) {
            this.adicionarConsulta(marcar);
            System.out.println("INFO: Consulta agendada com sucesso para " + marcar.getDataHora().toLocalDate() + " às " + marcar.getDataHora().toLocalTime() + "!");
            return true;
        } else {
            System.out.println("INFO: A consulta não foi marcada devido ao conflito de agenda informado acima.");
            return false;
        }
    }

    public List<Consulta> getConsultas() {
        Collections.sort(consultas);
        return this.consultas;
    }
}