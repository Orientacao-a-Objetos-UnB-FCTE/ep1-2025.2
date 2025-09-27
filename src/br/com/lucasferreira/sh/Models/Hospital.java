package br.com.lucasferreira.sh.Models;
import java.util.List;
import java.util.ArrayList;
public class Hospital {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private Agenda agenda;
    public Hospital(){
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.agenda = new Agenda();
    }
    public void cadastrarPaciente(Paciente p){
        this.pacientes.add(p);
        System.out.println("Paciente " + p.getNome() + " cadastrado com sucesso.");
    }
    public void cadastrarMedico(Medico m){
        this.medicos.add(m);
        System.out.println("Médico " + m.getNome() + " cadastrado com sucesso.");
    }

    }

