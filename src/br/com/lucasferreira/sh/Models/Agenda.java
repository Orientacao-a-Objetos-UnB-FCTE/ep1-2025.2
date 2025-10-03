package br.com.lucasferreira.sh.Models;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class Agenda {
    private List<Consulta> consultas;
    public Agenda(){
        this.consultas = new ArrayList<>();
    }
    public void adicionarConsulta(Consulta novaConsulta){
        this.consultas.add(novaConsulta);
    }
    public boolean isHorarioDisponivel(LocalDateTime horario, Medico medico,Paciente paciente){
        for(Consulta verificaDisponibilidade : this.consultas){
            LocalDateTime verificaHorarioDisponivel = verificaDisponibilidade.getDataHora();
            if(verificaHorarioDisponivel.isEqual(horario)){
                return false;
            }
            if(verificaDisponibilidade.getMedico().equals(medico)){
                System.out.println("LOG: Conflito de agenda encontrado para o médico: " + medico.getNome());
                return false;
            }
            if (verificaDisponibilidade.getPaciente().equals(paciente)) {
                System.out.println("LOG: Conflito de agenda encontrado para o paciente: " + paciente.getNome());
                return false;
            }
            }

        return true;
        }
        public boolean marcarConsulta(Consulta marcar){
        LocalDateTime horarioDesejado = marcar.getDataHora();
        Medico medicoDesejado = marcar.getMedico();
        Paciente pacienteDaConsulta = marcar.getPaciente();
        if(this.isHorarioDisponivel(horarioDesejado,medicoDesejado,pacienteDaConsulta)){
            this.adicionarConsulta(marcar);
            System.out.println("INFO: Consulta agendada com sucesso!");
            return true;
        }
        else{
            System.out.println("INFO: A consulta não foi marcada devido ao conflito de agenda informado acima.");
            return false;
        }

        }
        public List<Consulta> getHistorico(){
            Collections.sort(consultas);
            return this.consultas;
        }
    }








