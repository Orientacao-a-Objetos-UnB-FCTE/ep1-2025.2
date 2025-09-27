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
    public boolean isHorarioDisponivel(LocalDateTime horario){
        for(Consulta verificaDisponibilidade : this.consultas){
            LocalDateTime verificaHorarioDisponivel = verificaDisponibilidade.getDataHora();
            if(verificaHorarioDisponivel.isEqual(horario)){
                return false;
            }
            }

        return true;
        }
        public boolean marcarConsulta(Consulta marcar){
        LocalDateTime horarioDesejado = marcar.getDataHora();
        if(this.isHorarioDisponivel(horarioDesejado)){
            this.adicionarConsulta(marcar);
            System.out.println("INFO: Consulta agendada com sucesso!");
            return true;
        }
        else{
            System.out.println("ERRO: Horário indisponível. A consulta não foi marcada.");
            return false;
        }

        }
        public List<Consulta> getHistorico(){
            Collections.sort(consultas);
            return this.consultas;
        }
    }








