package br.com.lucasferreira.sh.Models;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
public class Agenda {
    private List<Consulta> consultas;
    public Agenda(){
        this.consultas = new ArrayList<>();
    }
    public List<LocalDateTime>getHorarios(LocalDate dia){
        List<LocalDateTime>horariosDisponiveis = new ArrayList<>();
        final LocalTime inicioConsultas = LocalTime.of(8,0);
        final LocalTime fimConsultas = LocalTime.of(18,0);
        final int duracaoConsultaMinutos = 60;
        for(LocalTime hora = inicioConsultas; hora.isBefore(fimConsultas.plusMinutes(1));hora = hora.plusMinutes(duracaoConsultaMinutos)){
            LocalDateTime horario = LocalDateTime.of(dia,hora);
            if(isHorarioDisponivel(horario)){
                horariosDisponiveis.add(horario);
            }
        }
        return horariosDisponiveis;
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
    }

