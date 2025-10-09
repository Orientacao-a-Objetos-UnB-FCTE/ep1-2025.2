package hospital.model;

public class Quarto {
    private int numero;
    private boolean ocupado;

    public Quarto(int numero) { this.numero = numero; this.ocupado = false; }
    public int getNumero() { return numero; }
    public boolean isOcupado() { return ocupado; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }
}
