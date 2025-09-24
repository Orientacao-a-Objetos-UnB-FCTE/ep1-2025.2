import java.util.List;

public class GerenciarInternacao {
    public static boolean registrarInternacao(List<Internacao> internacoes, Pacientes paciente, Medicos medicoResponsavel, String dataEntrada, String quarto, double custo){
        for (Internacao i : internacoes){
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.isAtiva()){
                System.out.println("Quarto ocupado no momento!");
                return false;
            }
        }
        Internacao internacao = new Internacao(paciente, medicoResponsavel, dataEntrada, quarto, custo);
        internacao.add(internacao);

        System.out.println("Internação registrada com sucesso para: " + paciente.getNome() + "no quarto" + quarto + ".");
        return true;
    }
}
