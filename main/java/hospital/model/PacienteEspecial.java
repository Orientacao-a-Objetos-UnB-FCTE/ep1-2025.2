package hospital.model;

public class PacienteEspecial extends Paciente {
    private PlanoSaude plano;

    public PacienteEspecial(String nome, String cpf, int idade, PlanoSaude plano) {
        super(nome, cpf, idade);
        this.plano = plano;
    }

    public PlanoSaude getPlano() { return plano; }
}
