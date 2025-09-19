public class Agendamento {
    public static boolean agendarConsulta(Medicos medicos, Pacientes pacientes, String descricao, String horario){
        if(!medicos.simDisponivel(horario)){
            System.out.println("Médico indisponível neste horário!");
            return false;
        }
        Consulta consulta = new Consulta(medicos, pacientes, descricao, horario);
        pacientes.adicionarConsulta(consulta);
        medicos.adicionarConsulta(consulta);
        System.out.println("Tudo certo, consulta marcada!");
            return true;
    }
}
