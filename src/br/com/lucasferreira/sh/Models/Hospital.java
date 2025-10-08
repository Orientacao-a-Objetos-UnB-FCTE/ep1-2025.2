package br.com.lucasferreira.sh.Models;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private Agenda agenda;
    private List<Internacao> internacoes;
    private List<PlanoDeSaude> planos;

    public Hospital() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.agenda = new Agenda();
        this.internacoes = new ArrayList<>();
        this.planos = new ArrayList<>();
    }

    //cadastro
    public void cadastrarPaciente(Paciente p) {
        this.pacientes.add(p);
        System.out.println("Paciente " + p.getNome() + " cadastrado com sucesso.");
    }
    public void cadastrarMedico(Medico m) {
        this.medicos.add(m);
        System.out.println("Médico " + m.getNome() + " cadastrado com sucesso.");
    }
    public void cadastrarPlano(PlanoDeSaude plano) {
        this.planos.add(plano);
        System.out.println("Plano de Saúde '" + plano.getNome() + "' cadastrado com sucesso.");
    }


    // carregamento
    public void carregarMedicos(List<Medico> medicos) {
        this.medicos.addAll(medicos);
    }
    public void carregarPacientes(List<Paciente> pacientes) {
        this.pacientes.addAll(pacientes);
    }
    public void carregarPlanos(List<PlanoDeSaude> planos) {
        this.planos.addAll(planos);
    }

    //buscas
    public boolean pacienteJaExiste(String cpfParaVerificar) {
        String cpfLimpoParaVerificar = cpfParaVerificar.replaceAll("[^0-9]", "");
        for (Paciente pacienteExistente : this.pacientes) {
            String cpfLimpoExistente = pacienteExistente.getCpf().replaceAll("[^0-9]", "");
            if (cpfLimpoExistente.equals(cpfLimpoParaVerificar)) {
                return true;
            }
        }
        return false;
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        String cpfLimpoBusca = cpf.replaceAll("[^0-9]", "");
        for (Paciente paciente : this.pacientes) {
            String cpfLimpoPaciente = paciente.getCpf().replaceAll("[^0-9]", "");
            if (cpfLimpoPaciente.equals(cpfLimpoBusca)) {
                return paciente;
            }
        }
        return null;
    }

    // getters
    public List<Paciente> getPacientes() {
        return this.pacientes;
    }
    public List<Medico> getMedicos() {
        return this.medicos;
    }
    public List<PlanoDeSaude> getPlanos() {
        return this.planos;
    }
    public Agenda getAgenda() {
        return this.agenda;
    }
    public List<Internacao> getInternacoes() {
        return this.internacoes;
    }
}