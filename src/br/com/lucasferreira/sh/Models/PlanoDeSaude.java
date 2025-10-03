package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.Especialidade;
import java.util.HashMap;
import java.util.Map;
public class PlanoDeSaude{
    private String nome;
    private boolean isEspecial;
    private Map<Especialidade, Double> descontosPorEspecialidade;
    public PlanoDeSaude(String nome, boolean isEspecial) {
        this.nome = nome;
        this.isEspecial = isEspecial;
        this.descontosPorEspecialidade = new HashMap<>();
    }
}
