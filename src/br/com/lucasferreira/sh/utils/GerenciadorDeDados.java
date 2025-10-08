package br.com.lucasferreira.sh.utils;

import br.com.lucasferreira.sh.Models.*;
import br.com.lucasferreira.sh.enums.Especialidade;
import br.com.lucasferreira.sh.enums.TipoPlano;
import br.com.lucasferreira.sh.Models.PacienteFactory;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class GerenciadorDeDados {

    private static final String SEPARADOR = ";";

    // Le e traduz os arquivos

    public static List<PlanoDeSaude> carregarPlanos(String caminhoArquivo) {
        List<PlanoDeSaude> planos = new ArrayList<>();
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return planos;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // Pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(SEPARADOR);
                planos.add(new PlanoDeSaude(campos[0], Boolean.parseBoolean(campos[1]), TipoPlano.valueOf(campos[2])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de planos: " + e.getMessage());
        }
        return planos;
    }

    public static List<Medico> carregarMedicos(String caminhoArquivo) {
        List<Medico> medicos = new ArrayList<>();
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return medicos;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // Pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(SEPARADOR);
                medicos.add(new Medico(campos[0], campos[1], Especialidade.valueOf(campos[2]), Integer.parseInt(campos[3]), Boolean.parseBoolean(campos[4])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de médicos: " + e.getMessage());
        }
        return medicos;
    }

    public static List<Paciente> carregarPacientes(String caminhoArquivo, List<PlanoDeSaude> planosDisponiveis) {
        List<Paciente> pacientes = new ArrayList<>();
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return pacientes;

        Map<String, PlanoDeSaude> mapaPlanos = planosDisponiveis.stream().collect(Collectors.toMap(PlanoDeSaude::getNome, Function.identity()));

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // Pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(SEPARADOR);
                String nomePlano = campos[2];
                PlanoDeSaude plano = nomePlano.equals("null") ? null : mapaPlanos.get(nomePlano);
                pacientes.add(PacienteFactory.criarPaciente(campos[0], campos[1], plano, Boolean.parseBoolean(campos[3]), LocalDate.parse(campos[4])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    public static List<Consulta> carregarConsultas(String caminhoArquivo, List<Medico> medicos, List<Paciente> pacientes) {
        List<Consulta> consultas = new ArrayList<>();
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) return consultas;

        Map<String, Medico> mapaMedicos = medicos.stream().collect(Collectors.toMap(Medico::getCrm, Function.identity()));
        Map<String, Paciente> mapaPacientes = pacientes.stream().collect(Collectors.toMap(p -> p.getCpf().replaceAll("[^0-9]", ""), Function.identity()));

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(SEPARADOR);
                Medico medico = mapaMedicos.get(campos[0]);
                Paciente paciente = mapaPacientes.get(campos[1].replaceAll("[^0-9]", ""));
                LocalDateTime dataHora = LocalDateTime.parse(campos[2]);
                if (medico != null && paciente != null) {
                    consultas.add(new Consulta(medico, paciente, dataHora));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar consultas: " + e.getMessage());
        }
        return consultas;
    }

    // salva alteracoes nos arquivos

    public static void salvarPlanos(String caminhoArquivo, List<PlanoDeSaude> planos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            bw.write("nome;cobreInternacaoCurta;tipo");
            bw.newLine();
            for (PlanoDeSaude plano : planos) {
                String linha = String.join(SEPARADOR, plano.getNome(), String.valueOf(plano.isCobreInternacaoCurta()), plano.getTipo().name());
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar planos: " + e.getMessage());
        }
    }

    public static void salvarMedicos(String caminhoArquivo, List<Medico> medicos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            bw.write("nome;crm;especialidade;rating;plantao");
            bw.newLine();
            for (Medico medico : medicos) {
                String linha = String.join(SEPARADOR, medico.getNome(), medico.getCrm(), medico.getEspecialidade().name(), String.valueOf(medico.getRating()), String.valueOf(medico.isPlantao()));
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar médicos: " + e.getMessage());
        }
    }

    public static void salvarPacientes(String caminhoArquivo, List<Paciente> pacientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            bw.write("nome;cpf;plano_nome;pcd;data_nascimento");
            bw.newLine();
            for (Paciente paciente : pacientes) {
                String nomePlano = paciente.getPlano() == null ? "null" : paciente.getPlano().getNome();
                String linha = String.join(SEPARADOR, paciente.getNome(), paciente.getCpf(), nomePlano, String.valueOf(paciente.isPcd()), paciente.getDataNascimento().toString());
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    public static void salvarConsultas(String caminhoArquivo, List<Consulta> consultas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            bw.write("medico_crm;paciente_cpf;data_hora");
            bw.newLine();
            for (Consulta consulta : consultas) {
                String linha = String.join(SEPARADOR, consulta.getMedico().getCrm(), consulta.getPaciente().getCpf(), consulta.getDataHora().toString());
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }
}