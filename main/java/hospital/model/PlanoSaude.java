package hospital.model;

public class PlanoSaude {
    private String nome;
    private double descontoPadrao; // ex: 0.1 = 10%

    public PlanoSaude(String nome, double descontoPadrao) {
        this.nome = nome;
        this.descontoPadrao = descontoPadrao;
    }

    public String getNome() { return nome; }
    public double getDescontoPadrao() { return descontoPadrao; }
}
