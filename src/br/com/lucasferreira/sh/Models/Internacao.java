package br.com.lucasferreira.sh.Models;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
public class Internacao {
    private Paciente paciente;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    public boolean ativa;

    public Internacao(Paciente paciente){
        this.paciente = paciente;
        this.dataEntrada = LocalDateTime.now();
        this.ativa = true;
    }
    public long getDuracaoEmDias(){
        LocalDateTime dataFinal = (this.dataSaida == null) ? LocalDateTime.now() : this.dataSaida;
        //(condição) ? valor_se_verdadeiro : valor_se_falso;
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataFinal);
        // se a diferenca da data de entrada e data final  ser igual a 0 ent retorna 1 dia se n, retorna os dias
        return dias == 0 ? 1 : dias;
    }
    public void darAlta() {
        this.dataSaida = LocalDateTime.now();
        this.ativa = false;
    }
    public Paciente getPaciente() {
        return paciente;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }
}
