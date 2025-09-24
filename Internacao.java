public class Internacao {
    private Pacientes pacientes;
    private Medicos medicoResponsavel;
    private String dataEntrada;
    private String dataBaixa;
    private String quarto;
    private double custo;
    private boolean ativa;

    public Internacao(Pacientes pacientes, Medicos medicoResponsavel, String dataEntrada, String quarto, double custo){
        this.pacientes = pacientes;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto =quarto;
        this.custo = custo;
        this.ativa =true;
    }
    public void liberarInternacao(String dataBaixa){
        this.dataBaixa = dataBaixa;
        this.ativa = false;
    }
    public boolean isAtiva(){
        return ativa;
    }

    public double getCusto() {
        return custo;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getQuarto() {
        return quarto;
    }

    public Medicos getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public String toString(){
        return "Situação da internação do paciente:" + pacientes.getNome() + "no quarto:" + quarto + "Ativa:" + ativa;}

    public void add(Internacao internacao) {
    }
}
