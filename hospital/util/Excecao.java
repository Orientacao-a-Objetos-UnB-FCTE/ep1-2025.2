package hospital.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class Excecao {
    private static final Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void closeScanner(){
        if (sc != null){
            sc.close();
            System.out.println("Muito obrigado, volte sempre (mas melhor não) ao Hospital Saint Dennis");
        }
    }
        public static int lerOpcaoMenu(String mensagem) {
            while (true) {
                try {
                    System.out.print(mensagem);
                    int opcao = Integer.parseInt(sc.nextLine());
                    if (opcao < 0) {
                        System.out.println("Opção inválida. Digite um número válido.");
                    } else {
                        return opcao;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite apenas números.");
                }
            }
        }
        public static String lerStringSemNumero(String mensagem) {
            while (true) {
                System.out.print(mensagem);
                String entrada = sc.nextLine().trim();
                if (entrada.matches("^[A-Za-zÀ-ÿ ]+$")) {
                    return entrada;
                } else {
                    System.out.println("Digite apenas letras. Exemplo: Pedro Pedreira");
                }
            }
        }
        public static int lerInteiroPositivo(String mensagem) {
            while (true) {
                try {
                    System.out.print(mensagem);
                    int valor = Integer.parseInt(sc.nextLine());
                    if (valor > 0) {
                        return valor;
                    } else {
                        System.out.println("O número deve ser maior que zero!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite apenas números");
                }
            }
        }
        public static String lerString(String mensagem){
            System.out.println(mensagem);
            return sc.nextLine();
        }
        public static double lerDoublePositivo(String mensagem) {
            while (true) {
                try {
                    System.out.print(mensagem);
                    String input = sc.nextLine().trim().replace(",", ".");
                    double valor = Double.parseDouble(input);
                    if (valor > 0) {
                        return valor;
                    } else {
                        System.out.println("O valor deve ser maior que zero!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite apenas números decimais. Exemplo: 15.50");
                }
            }
        }
        public static String lerCpf(String mensagem) {
            while (true) {
                System.out.print(mensagem);
                String cpf = sc.nextLine().trim();
                if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                    return cpf;
                } else {
                    System.out.println("CPF inválido! Use o formato: 123.456.789-10");
                }
            }
        }
        public static LocalDateTime lerDataHora(String mensagem){
            while (true) {
                System.out.print(mensagem + " (Formato: dd/MM/yyyy HH:mm): ");
                String entrada = sc.nextLine().trim();
                try {
                    return LocalDateTime.parse(entrada, DATA_HORA);
                }catch (DateTimeParseException e){
                    System.out.println("Formato de data e hora inválido! Use ex: 12/03/2015 12:10 ");
                }


            }
        }
        public static LocalDate lerData(String mensagem){
            while (true){
                System.out.print(mensagem + " (Formato: dd/MM/yyyy): ");
                String entrada = sc.nextLine().trim();
                try{
                    return LocalDate.parse(entrada, DATA_HORA);
                } catch (DateTimeParseException e){
                    System.out.println("Formato de data e hora inválido! Use ex: 21/07/1912 07:35 ");
                }
            }
        }

    }
