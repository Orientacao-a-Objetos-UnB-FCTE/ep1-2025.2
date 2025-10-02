package hospital.servico;

import hospital.entidade.Internacao;
import hospital.entidade.Pacientes;
import hospital.entidade.Medicos;
import java.time.LocalDate;

import java.util.List;

public class GerenciarInternacao {
    public static Internacao registrarInternacao(List<Internacao> internacoes, Pacientes paciente, Medicos medicoResponsavel, LocalDate dataEntrada, String quarto, double custo) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.isAtiva()) {
                throw new IllegalArgumentException("Quarto " + quarto + "está oculpado no momento.");
            }
        }
        Internacao internacao = new Internacao(paciente, medicoResponsavel, dataEntrada, quarto, custo);
        internacoes.add(internacao);
        paciente.adicionarInternacao(internacao);

        return internacao;
    }

    public static Internacao liberarInternacao(List<Internacao> internacoes, String quarto, LocalDate dataBaixa) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.isAtiva()) {
                if (dataBaixa.isBefore(i.getDataEntrada())){
                    throw new IllegalArgumentException("A data de baixa (" + dataBaixa + ") não pode ser anterior à data ed entrada (" + i.getDataEntrada() + ").");
                }
            }
            i.liberarInternacao(dataBaixa);
            return i;
        }
        throw new IllegalArgumentException ("Nenhuma internação encontrada no quarto" + quarto + ".");
    }
}