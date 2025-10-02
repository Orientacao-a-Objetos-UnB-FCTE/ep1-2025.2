package hospital.servico;

import hospital.entidade.Consulta;
import hospital.entidade.Pacientes;
import hospital.entidade.Medicos;

import java.time.LocalDateTime;

public class Agendamento {
    public static Consulta agendarConsulta(Medicos medicos, Pacientes pacientes, String descricao, LocalDateTime horario){
        if (!medicos.simDisponivel(horario)){
            throw new IllegalArgumentException("Médico " + medicos.getNome() + "indisponível neste hprário: " + horario);
        }
        Consulta consulta = new Consulta(medicos, pacientes, descricao, horario);
        pacientes.adicionarConsulta(consulta);
        medicos.adicionarConsulta(consulta);
        return consulta;
    }
}
