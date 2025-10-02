package hospital.entidade;

import hospital.interfac.PacienteInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pacientes implements PacienteInterface {
    private String nome;
    private String cpf;
    private int idade;
    private String id;
    private List<Internacao> internacoes;
    private List<Consulta> consultas;
    private List<Exame> exames;

    public Pacientes(String nome, String cpf, int idade, String id){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.id = id;
        this.internacoes = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.exames = new ArrayList<>();

    }
    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getIdade() {
        return idade;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public List<Internacao> getHistoricoInternacoes() {
        return Collections.unmodifiableList(internacoes);
    }

    @Override
    public List<Consulta> getHistoricoConsultas() {
        return Collections.unmodifiableList(consultas);
    }

    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
    }

    public List<Consulta> getConsultas() {
        return Collections.unmodifiableList(consultas);
    }

    public void adicionarInternacao(Internacao internacao){
        internacoes.add(internacao);
    }

    public void adicionarExame(Exame exame) {
        exames.add(exame);
    }

    public List<Exame> getExames() {

        return Collections.unmodifiableList(exames);
    }

    public String toString(){
        return " Paciente: "+  nome  + " |CPF: " +  cpf  + " |Idade: " +  idade  + " anos " + " |Identificação: " +  id;
    }

}
