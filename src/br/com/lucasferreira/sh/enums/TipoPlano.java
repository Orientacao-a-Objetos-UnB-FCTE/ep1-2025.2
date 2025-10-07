package br.com.lucasferreira.sh.enums;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

public enum TipoPlano {
    BASICO(criarMapaDescontosBasico()),
    INTERMEDIARIO(criarMapaDescontosIntermediario()),
    PREMIUM(criarMapaDescontosPremium());
    private final Map<Especialidade, Double> descontos;
    TipoPlano(Map<Especialidade, Double> descontos) {
        this.descontos = Collections.unmodifiableMap(descontos);
    }
    public Map<Especialidade, Double> getDescontos() {
        return this.descontos;
    }
    private static Map<Especialidade, Double> criarMapaDescontosBasico() {
        Map<Especialidade, Double> mapa = new HashMap<>();
        mapa.put(Especialidade.CLINICA_GERAL, 0.10);
        mapa.put(Especialidade.PEDIATRIA, 0.05);
        return mapa;
    }

    private static Map<Especialidade, Double> criarMapaDescontosIntermediario() {
        Map<Especialidade, Double> mapa = new HashMap<>();
        mapa.put(Especialidade.CLINICA_GERAL, 0.20);
        mapa.put(Especialidade.PEDIATRIA, 0.15);
        mapa.put(Especialidade.ORTOPEDIA, 0.10);
        mapa.put(Especialidade.GINECOLOGIA, 0.10);
        return mapa;
    }

    private static Map<Especialidade, Double> criarMapaDescontosPremium() {
        Map<Especialidade, Double> mapa = new HashMap<>();
        mapa.put(Especialidade.CLINICA_GERAL, 0.40); // 40%
        mapa.put(Especialidade.PEDIATRIA, 0.30);     // 30%
        mapa.put(Especialidade.ORTOPEDIA, 0.25);    // 25%
        mapa.put(Especialidade.GINECOLOGIA, 0.25);   // 25%
        mapa.put(Especialidade.CARDIOLOGIA, 0.20);  // 20%
        mapa.put(Especialidade.NEUROLOGIA, 0.15);    // 15%
        mapa.put(Especialidade.DERMATOLOGIA, 0.50);  // 50%
        return mapa;
    }
}