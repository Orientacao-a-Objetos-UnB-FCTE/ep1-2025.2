package br.com.lucasferreira.sh.enums;
public enum Especialidade {
    CARDIOLOGIA(500.00),
    ORTOPEDIA(400.00),
    PEDIATRIA(450.00),
    DERMATOLOGIA(420.00);

    private final double precoBase;

    Especialidade(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }
}