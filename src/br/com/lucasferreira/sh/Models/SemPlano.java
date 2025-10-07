package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.Especialidade;

public class SemPlano {
    public double custoPorConsultaSemPlano(Especialidade especialidade) {
        return especialidade.getPrecoBase();
    }
}
