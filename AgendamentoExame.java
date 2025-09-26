public class AgendamentoExame {
    public static void agendarExame(Pacientes paciente, String tipo, String dataHorario){
        Exame exame = new Exame(paciente , tipo, dataHorario);
        paciente.adicionarExame(exame);
        System.out.println("Exame agendado com sucesso,muito obrigado" + exame);
    }
}
