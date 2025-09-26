public class Internacao {
    private Pacientes paciente;
    private Medicos medicoResponsavel;
    private String dataEntrada;
    private String dataBaixa;
    private String quarto;
    private double custo;
    private boolean ativa;

    public Internacao(Pacientes paciente, Medicos medicoResponsavel, String dataEntrada, String quarto, double custo){
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto =quarto;
        this.custo = custo;
        this.ativa =true;
        paciente.adicionarInternacao("Internação iniciada no dia " + dataEntrada + " no quarto" + quarto);
    }
    public void liberarInternacao(String dataBaixa){
        this.dataBaixa = dataBaixa;
        this.ativa = false;
        paciente.adicionarInternacao("Alta em " + dataBaixa + " (quarto " + quarto + ")");
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
        return "Situação da internação do paciente:" + paciente.getNome() + " no quarto:" + quarto + " no dia: " + dataEntrada + " Valor: R$ " + custo;
    }

    }

