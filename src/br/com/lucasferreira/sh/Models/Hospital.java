package br.com.lucasferreira.sh.Models;
import java.util.List;
import java.util.ArrayList;
public class Hospital {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private Agenda agenda;
    private List<Internacao> internacoes;
    public Hospital(){
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.agenda = new Agenda();
        this.internacoes = new ArrayList<>();
    }
    public void cadastrarPaciente(Paciente p){
        this.pacientes.add(p);
        System.out.println("Paciente " + p.getNome() + " cadastrado com sucesso.");
    }
    public void cadastrarMedico(Medico m){
        this.medicos.add(m);
        System.out.println("Médico " + m.getNome() + " cadastrado com sucesso.");
    }
    public void iniciarInternacao(Paciente p){
        Internacao novaInternacao = new Internacao(p);
        this.internacoes.add(novaInternacao);
        System.out.println("INFO: Internação iniciada para o paciente " + p.getNome() + " em " + novaInternacao.getDataEntrada());
    }
    public void finalizarInternacao(Internacao internacao, double custoDiaria){
        internacao.darAlta();
        long diasInternado = internacao.getDuracaoEmDias();
        Paciente paciente = internacao.getPaciente();
        PlanoDeSaude plano = paciente.getPlano();
        double custoFinal = 0;
        if(plano.isCobreInternacaoCurta()){
            if(diasInternado <= 7){
                custoFinal = 0;
            }
            else{
                custoFinal = (diasInternado - 7)*custoDiaria;
            }
        }
        else{
            custoFinal = (diasInternado)*custoDiaria;
        }
        System.out.println("Internação finalizada para " + paciente.getNome() +
                ". Dias internado: " + diasInternado +
                ". Custo final: R$ " + custoFinal);
    }
    }


