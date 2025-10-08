package br.com.lucasferreira.sh.enums;
public enum Especialidade {
    CLINICA_GERAL(350.00),
    ORTOPEDIA(400.00),
    GINECOLOGIA(420.00),
    PEDIATRIA(450.00),
    DERMATOLOGIA(480.00),
    CARDIOLOGIA(550.00),
    NEUROLOGIA(600.00);

    private final double precoBase;

    Especialidade(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }
}