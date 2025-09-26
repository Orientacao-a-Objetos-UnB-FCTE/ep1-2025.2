import java.util.List;

public class GerenciarInternacao {
    public static boolean registrarInternacao(List<Internacao> internacoes, Pacientes paciente, Medicos medicoResponsavel, String dataEntrada, String quarto, double custo) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.isAtiva()) {
                System.out.println("Quarto ocupado no momento!");
                return false;
            }
        }
        Internacao internacao = new Internacao(paciente, medicoResponsavel, dataEntrada, quarto, custo);
        internacoes.add(internacao);

        System.out.println("Internação registrada com sucesso para: " + paciente.getNome() + "no quarto" + quarto + ".");
        return true;
    }

    public static boolean liberarInternacao(List<Internacao> internacoes, String quarto, String dataBaixa) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.isAtiva()) {
                i.liberarInternacao(dataBaixa);
                System.out.println("Paciente com internação" + i.getMedicoResponsavel().getNome() + " no quarto " + quarto + " recebeu alta");
            }
            return true;
        }
        System.out.println("Nenhuma internação encontrada no qaurto" + quarto + ".");
        return false;
    }
}