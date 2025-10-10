package br.com.seuhospital.application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import br.com.seuhospital.domain.Consulta;
import br.com.seuhospital.domain.Especialidade;
import br.com.seuhospital.domain.Hospital;
import br.com.seuhospital.domain.Internacao;
import br.com.seuhospital.domain.Medico;
import br.com.seuhospital.domain.Paciente;
import br.com.seuhospital.domain.PlanoDeSaude;
import br.com.seuhospital.domain.Prioridade;
import br.com.seuhospital.infra.DataPersistencia;
import br.com.seuhospital.util.ReportExporter;

public class Main {
    private static Hospital hospital = new Hospital();
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        try {
            hospital = DataPersistencia.loadHospitalData();
            System.out.println("Dados carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Nenhum dado anterior encontrado ou erro ao carregar. Começando com um hospital novo.");
            hospital = new Hospital();
        }

        System.out.println("Bem-vindo ao Sistema de Gerenciamento Hospitalar!");
        exibirMenuPrincipal();
        
        try {
            DataPersistencia.saveHospitalData(hospital);
            System.out.println("Dados salvos com sucesso. Obrigado por usar o sistema!");
        } catch (Exception e) {
            System.err.println("ERRO CRÍTICO: Não foi possível salvar os dados. " + e.getMessage());
        }
    }

    private static Especialidade buscarEspecialidade(String nomeEspecialidade) {
        for (Especialidade esp : Especialidade.values()) {
            if (esp.name().equalsIgnoreCase(nomeEspecialidade.trim())) {
                return esp;
            }
        }
        return null;
    }

    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastro de Pacientes");
            System.out.println("2. Cadastro de Médicos");
            System.out.println("3. Cadastro de Planos de Saúde");
            System.out.println("4. Agendamento de Consultas");
            System.out.println("5. Internações");
            System.out.println("6. Triagem");
            System.out.println("7. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1: menuCadastroPacientes(); break;
                case 2: menuCadastroMedicos(); break;
                case 3: menuCadastroPlanosDeSaude(); break;
                case 4: menuAgendamentoConsultas(); break;
                case 5: menuInternacoes(); break;
                case 6: menuTriagem(); break;
                case 7: menuRelatorios(); break;
                case 0: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
        } while (opcao != 0);
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            return -1;
        }
    }

    private static double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Use números e ponto para decimais (ex: 150.50).");
            return -1.0;
        }
    }

    private static void menuCadastroPacientes() {
        System.out.println("\n--- Cadastro de Pacientes ---");
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do paciente: ");
        String cpf = scanner.nextLine();
        
        LocalDate dataNascimento;
        try {
            System.out.print("Data de Nascimento do paciente (AAAA-MM-DD): ");
            dataNascimento = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use AAAA-MM-DD. Paciente não cadastrado.");
            return;
        }

        System.out.print("Endereço do paciente: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone do paciente: ");
        String telefone = scanner.nextLine();

        System.out.print("Paciente possui plano de saúde? (s/n): ");
        String temPlano = scanner.nextLine();

        if (temPlano.equalsIgnoreCase("s")) {
            System.out.print("Nome do plano de saúde: ");
            String nomePlano = scanner.nextLine();
            PlanoDeSaude plano = hospital.buscarPlanoDeSaudePorNome(nomePlano);
            if (plano != null) {
                hospital.cadastrarPaciente(new Paciente(cpf, nome, dataNascimento, endereco, telefone, plano));
            } else {
                System.out.println("Plano de saúde não encontrado. Paciente cadastrado sem plano.");
                hospital.cadastrarPaciente(new Paciente(cpf, nome, dataNascimento, endereco, telefone));
            }
        } else {
            hospital.cadastrarPaciente(new Paciente(cpf, nome, dataNascimento, endereco, telefone));
        }
    }

    private static void menuCadastroMedicos() {
        System.out.println("\n--- Cadastro de Médicos ---");
        System.out.print("Nome do médico: ");
        String nome = scanner.nextLine();
        System.out.print("CRM do médico: ");
        String crm = scanner.nextLine();
        
        System.out.print("Especialidade (Ex: Cardiologia, Pediatria): ");
        String especialidadeStr = scanner.nextLine();
        Especialidade especialidade = buscarEspecialidade(especialidadeStr);
        if (especialidade == null) {
            System.out.println("Especialidade inválida. Médico não cadastrado.");
            return;
        }

        System.out.print("Custo da consulta: ");
        double custo = lerDouble();
        if (custo < 0) {
            System.out.println("Operação cancelada devido a custo inválido.");
            return;
        }

        hospital.cadastrarMedico(new Medico(nome, crm, especialidade, custo));
    }

    private static void menuCadastroPlanosDeSaude() {
        System.out.println("\n--- Cadastro de Planos de Saúde ---");
        System.out.print("Nome do plano de saúde: ");
        String nomePlano = scanner.nextLine();
        System.out.print("Internação gratuita para menos de uma semana? (s/n): ");
        boolean internacaoGratuita = scanner.nextLine().equalsIgnoreCase("s");

        PlanoDeSaude plano = new PlanoDeSaude(nomePlano, internacaoGratuita);

        System.out.print("Deseja adicionar descontos por especialidade? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            String continuar = "s"; // CORREÇÃO APLICADA AQUI
            while (continuar.equalsIgnoreCase("s")) {
                System.out.print("Especialidade (Ex: Cardiologia, Pediatria): ");
                String especialidadeStr = scanner.nextLine();
                Especialidade especialidade = buscarEspecialidade(especialidadeStr);
                if (especialidade == null) {
                    System.out.println("Especialidade inválida. Tente novamente.");
                    continuar = "s"; 
                    continue;
                }

                System.out.print("Desconto (ex: 0.1 para 10%): ");
                double desconto = lerDouble();
                if (desconto < 0) {
                    System.out.println("Desconto inválido. Tente novamente.");
                    continuar = "s"; // Para permitir tentar de novo
                    continue;
                }
                
                plano.adicionarDescontoEspecialidade(especialidade, desconto);
                System.out.print("Adicionar outro desconto? (s/n): ");
                continuar = scanner.nextLine();
            }
        }
        hospital.cadastrarPlanoDeSaude(plano);
    }
    
    // O resto dos métodos não foi alterado e está certp
    private static void menuAgendamentoConsultas() {
        System.out.println("\n--- Agendamento de Consultas ---");
        System.out.print("CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();
        Paciente paciente = hospital.buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("CRM do médico: ");
        String crmMedico = scanner.nextLine();
        Medico medico = hospital.buscarMedicoPorCrm(crmMedico);
        if (medico == null) {
            System.out.println("Médico não encontrado.");
            return;
        }

        LocalDateTime dataHora;
        try {
            System.out.print("Data e Hora da consulta (AAAA-MM-DDTHH:MM): ");
            dataHora = LocalDateTime.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data e hora inválido. Use AAAA-MM-DDTHH:MM. Agendamento cancelado.");
            return;
        }

        System.out.print("Local da consulta: ");
        String local = scanner.nextLine();

        Consulta consulta = hospital.agendarConsulta(paciente, medico, dataHora, local);
        if (consulta != null) {
            if (paciente.isSpecial() && paciente.getPlanoDeSaude() != null) {
                double desconto = paciente.getPlanoDeSaude().getDescontoPorEspecialidade(medico.getEspecialidade());
                if (paciente.getIdade() >= 60) {
                    desconto += 0.1;
                }
                double custoFinal = medico.getCustoConsulta() * (1 - desconto);
                System.out.printf("Custo da consulta com desconto (%.2f%%): R$ %.2f%n", desconto * 100, custoFinal);
            }
        
            System.out.print("Deseja concluir ou cancelar a consulta agora? (concluir/cancelar/nao): ");
            String acao = scanner.nextLine();
            if (acao.equalsIgnoreCase("concluir")) {
                System.out.print("Diagnóstico: ");
                String diagnostico = scanner.nextLine();
                System.out.print("Prescrição: ");
                String prescricao = scanner.nextLine();
                hospital.concluirConsulta(consulta, diagnostico, prescricao);
            } else if (acao.equalsIgnoreCase("cancelar")) {
                hospital.cancelarConsulta(consulta);
            }
        }
    }

    private static void menuInternacoes() {
        System.out.println("\n--- Gerenciamento de Internações ---");
        System.out.print("CPF do paciente: ");
        String cpfPaciente = scanner.nextLine();
        Paciente paciente = hospital.buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("CRM do médico responsável: ");
        String crmMedico = scanner.nextLine();
        Medico medico = hospital.buscarMedicoPorCrm(crmMedico);
        if (medico == null) {
            System.out.println("Médico não encontrado.");
            return;
        }

        System.out.print("Quarto para internação: ");
        String quarto = scanner.nextLine();

        LocalDateTime dataEntrada;
        try {
            System.out.print("Data e Hora de entrada (AAAA-MM-DDTHH:MM): ");
            dataEntrada = LocalDateTime.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data e hora inválido. Use AAAA-MM-DDTHH:MM. Internação cancelada.");
            return;
        }

        Internacao internacao = hospital.internarPaciente(paciente, medico, dataEntrada, quarto);
        if (internacao != null) {
            System.out.print("Deseja encerrar ou cancelar a internação agora? (encerrar/cancelar/nao): ");
            String acao = scanner.nextLine();
            if (acao.equalsIgnoreCase("encerrar")) {
                LocalDateTime dataSaida;
                try {
                    System.out.print("Data e Hora de saída (AAAA-MM-DDTHH:MM): ");
                    dataSaida = LocalDateTime.parse(scanner.nextLine());
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data e hora inválido. A internação permanece ativa.");
                    return;
                }
                hospital.encerrarInternacao(internacao, dataSaida);
            } else if (acao.equalsIgnoreCase("cancelar")) {
                hospital.cancelarInternacao(internacao);
            }
        }
    }
    
    private static void menuRelatorios() {
        int opcao;
        do {
            System.out.println("\n--- Relatórios ---");
            System.out.println("1. Pacientes cadastrados");
            System.out.println("2. Médicos cadastrados");
            System.out.println("3. Consultas futuras");
            System.out.println("4. Consultas passadas");
            System.out.println("5. Pacientes internados atualmente");
            System.out.println("6. Estatísticas gerais");
            System.out.println("7. Quantidade de pessoas em um plano de saúde e economia");
            System.out.println("8. Tempo médio de internação");
            System.out.println("9. Taxa de ocupação por especialidade");
            System.out.println("10. Exportar relatórios para CSV");
            System.out.println("11. Exportar relatórios para PDF");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();
    
            switch (opcao) {
                case 1:
                    System.out.println("\n--- Pacientes Cadastrados ---");
                    hospital.getPacientesCadastrados().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("\n--- Médicos Cadastrados ---");
                    hospital.getMedicosCadastrados().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("\n--- Consultas Futuras ---");
                    hospital.getConsultasFuturas().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("\n--- Consultas Passadas ---");
                    hospital.getConsultasPassadas().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("\n--- Pacientes Internados Atualmente ---");
                    hospital.getPacientesInternadosAtualmente().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("\n--- Estatísticas Gerais ---");
                    Medico medicoMaisAtendeu = hospital.getMedicoQueMaisAtendeu();
                    if (medicoMaisAtendeu != null) {
                        System.out.println("Médico que mais atendeu: " + medicoMaisAtendeu.getNome());
                    } else {
                        System.out.println("Nenhum médico atendeu consultas ainda.");
                    }
                    Especialidade especialidadeMaisProcurada = hospital.getEspecialidadeMaisProcurada();
                    if (especialidadeMaisProcurada != null) {
                        System.out.println("Especialidade mais procurada: " + especialidadeMaisProcurada);
                    } else {
                        System.out.println("Nenhuma especialidade foi procurada ainda.");
                    }
                    break;
                case 7:
                    System.out.println("\n--- Relatório de Planos de Saúde ---");
                    if (hospital.getPlanosDeSaude().isEmpty()) {
                        System.out.println("Nenhum plano de saúde cadastrado.");
                        break;
                    }
                    for (PlanoDeSaude plano : hospital.getPlanosDeSaude()) {
                        System.out.println("\nPlano: " + plano.getNome());
                        long qtdPessoas = hospital.getQuantidadePacientesPorPlano(plano);
                        System.out.println("  Quantidade de pacientes: " + qtdPessoas);
                        double economia = hospital.getEconomiaTotalPorPlano(plano);
                        System.out.printf("  Economia total para pacientes deste plano: R$ %.2f%n", economia);
                    }
                    break;
                case 8:
                    System.out.printf("\n--- Tempo Médio de Internação: %.2f horas ---%n", hospital.getTempoMedioInternacao());
                    break;
                case 9:
                    System.out.println("\n--- Taxa de Ocupação por Especialidade ---");
                    if (hospital.getTaxaOcupacaoPorEspecialidade().isEmpty()) {
                        System.out.println("Nenhuma internação ativa para calcular a taxa de ocupação.");
                    } else {
                        hospital.getTaxaOcupacaoPorEspecialidade().forEach((especialidade, taxa) ->
                                System.out.printf("  %s: %.2f%%%n", especialidade, taxa * 100));
                    }
                    break;
                case 10: menuExportarRelatoriosCsv(); break;
                case 11: menuExportarRelatoriosPdf(); break;
                case 0: break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
        } while (opcao != 0);
    }
    
    private static void menuExportarRelatoriosCsv() {
        System.out.println("\n--- Exportar Relatórios para CSV ---");
        System.out.println("1. Pacientes");
        System.out.println("2. Médicos");
        System.out.println("3. Consultas");
        System.out.println("4. Internações");
        System.out.println("0. Voltar");
        System.out.print("Escolha o tipo de relatório para exportar: ");
        int opcao = lerInteiro();

        String filePath;
        try {
            switch (opcao) {
                case 1:
                    filePath = "relatorio_pacientes.csv";
                    ReportExporter.exportPacientesToCsv(hospital.getPacientesCadastrados(), filePath);
                    System.out.println("Relatório de pacientes exportado para " + filePath);
                    break;
                case 2:
                    filePath = "relatorio_medicos.csv";
                    ReportExporter.exportMedicosToCsv(hospital.getMedicosCadastrados(), filePath);
                    System.out.println("Relatório de médicos exportado para " + filePath);
                    break;
                case 3:
                    filePath = "relatorio_consultas.csv";
                    ReportExporter.exportConsultasToCsv(hospital.getHistoricoConsultas(), filePath);
                    System.out.println("Relatório de consultas exportado para " + filePath);
                    break;
                case 4:
                    filePath = "relatorio_internacoes.csv";
                    ReportExporter.exportInternacoesToCsv(hospital.getHistoricoInternacoes(), filePath);
                    System.out.println("Relatório de internações exportado para " + filePath);
                    break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar relatório CSV: " + e.getMessage());
        }
    }

    private static void menuExportarRelatoriosPdf() {
        System.out.println("\n--- Exportar Relatórios para PDF ---");
        System.out.println("1. Pacientes");
        System.out.println("2. Médicos");
        System.out.println("3. Consultas");
        System.out.println("4. Internações");
        System.out.println("0. Voltar");
        System.out.print("Escolha o tipo de relatório para exportar: ");
        int opcao = lerInteiro();

        String filePath;
        try {
            switch (opcao) {
                case 1:
                    filePath = "relatorio_pacientes.pdf";
                    ReportExporter.exportPacientesToPdf(hospital.getPacientesCadastrados(), filePath);
                    System.out.println("Relatório de pacientes exportado para " + filePath);
                    break;
                case 2:
                    filePath = "relatorio_medicos.pdf";
                    ReportExporter.exportMedicosToPdf(hospital.getMedicosCadastrados(), filePath);
                    System.out.println("Relatório de médicos exportado para " + filePath);
                    break;
                case 3:
                    filePath = "relatorio_consultas.pdf";
                    ReportExporter.exportConsultasToPdf(hospital.getHistoricoConsultas(), filePath);
                    System.out.println("Relatório de consultas exportado para " + filePath);
                    break;
                case 4:
                    filePath = "relatorio_internacoes.pdf";
                    ReportExporter.exportInternacoesToPdf(hospital.getHistoricoInternacoes(), filePath);
                    System.out.println("Relatório de internações exportado para " + filePath);
                    break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } catch (IOException | DocumentException e) {
            System.err.println("Erro ao exportar relatório PDF: " + e.getMessage());
        }
    }

    private static void menuTriagem() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Triagem ---");
            System.out.println("1. Adicionar paciente à triagem");
            System.out.println("2. Chamar próximo paciente");
            System.out.println("3. Visualizar fila de triagem");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    System.out.print("CPF do paciente para triagem: ");
                    String cpfPacienteTriagem = scanner.nextLine();
                    Paciente paciente = hospital.buscarPacientePorCpf(cpfPacienteTriagem);
                    if (paciente != null) {
                        System.out.print("Prioridade (1-5, sendo 1=VERMELHO, 5=AZUL): ");
                        int prioridadeInt = lerInteiro();
                        if (prioridadeInt >= 1 && prioridadeInt <= 5) {
                            Prioridade prioridade = Prioridade.fromInt(prioridadeInt);
                            paciente.setPrioridade(prioridade);
                            hospital.getTriagem().adicionarPacienteATriagem(paciente);
                        } else {
                            System.out.println("Prioridade inválida. Deve ser um número entre 1 e 5.");
                        }
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;
                case 2:
                    hospital.getTriagem().chamarProximoPaciente();
                    break;
                case 3:
                    System.out.println("\n--- Fila de Triagem ---");
                    if (hospital.getTriagem().isFilaVazia()) {
                        System.out.println("A fila de triagem está vazia.");
                    } else {
                        hospital.getTriagem().getFilaDePrioridade().forEach(p -> System.out.println(p.getNome() + " - Prioridade: " + p.getPrioridade()));
                    }
                    break;
                case 0: break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
        } while (opcao != 0);
    }
}