package hospital.servico;

import hospital.entidade.Exame;
import hospital.entidade.Pacientes;

import java.time.LocalDateTime;

public class AgendamentoExame {
    public static Exame agendarExame(Pacientes paciente, String tipo, LocalDateTime dataHorario){
        Exame exame = new Exame(paciente, tipo, dataHorario);
        paciente.adicionarExame(exame);
        return exame;
    }
}
