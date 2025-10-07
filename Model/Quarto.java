public class Quarto {
    private final int numero;
    private boolean ocupado;

    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocuparQuarto() {
        this.ocupado = true;
    }

    public void liberarQuarto() {
        this.ocupado = false;
    }

    public int getNumero() {
        return numero;
    }
}