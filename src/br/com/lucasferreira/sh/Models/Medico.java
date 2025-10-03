package br.com.lucasferreira.sh.Models;
import br.com.lucasferreira.sh.enums.Especialidade;

public class Medico {
    private String nome;
    private final String crm;
    private Especialidade especialidade;
    private int rating;
    private boolean plantao;
    public Medico(String nome, String crm, Especialidade especialidade,int rating, boolean plantao){
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.rating = rating;
        this.plantao = plantao;
    }
    public String getNome(){
        return nome;
    }
    public String getCrm(){
        return crm;
    }
    public Especialidade getEspecialidade(){
        return especialidade;
    }
    public int getRating(){
        return rating;
    }
    public boolean isPlantao(){return plantao;}
    public double getCustoPorConsulta(){
        double precoBase = this.especialidade.getPrecoBase();
        double fatorMultiplicador = 1.0;
        switch (this.rating ){
            case 5:
                fatorMultiplicador = 1.2;
                break;
            case 4:
                fatorMultiplicador = 1.1;
                break;
            case 2:
                fatorMultiplicador = 0.9;
                break;
            case 1:
                fatorMultiplicador = 0.8;
                break;
        }
        return precoBase * fatorMultiplicador;
    }
}
