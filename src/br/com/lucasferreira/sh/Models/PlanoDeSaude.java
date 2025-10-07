package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.Especialidade;
import br.com.lucasferreira.sh.enums.TipoPlano;
import java.util.HashMap;
import java.util.Map;
public class PlanoDeSaude{
    private String nome;
    private boolean cobreInternacaoCurta;
    private TipoPlano tipo;
    public PlanoDeSaude(String nome, boolean cobreInternacaoCurta,TipoPlano tipo ) {
        this.nome = nome;
        this.cobreInternacaoCurta = cobreInternacaoCurta;
        this.tipo = tipo;
    }
    public boolean isCobreInternacaoCurta(){
        return this.cobreInternacaoCurta;
    }
    public String getNome(){
        return nome;
    }
    public double getDescontoPorEspecialidade(Especialidade especialidade){
        return this.tipo.getDescontos().getOrDefault(especialidade, 0.0);
    }
    public double getCustoFinalPorEspecialidade(Especialidade especialidade){
        double precoBase =especialidade.getPrecoBase();
        double desconto = getDescontoPorEspecialidade(especialidade);
        return precoBase *(1-desconto);
    }
}
