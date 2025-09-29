import java.util.Scanner;
public class Excecao {
    private static final Scanner sc = new Scanner(System.in);
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
                    System.out.println("Digite apenas letras. Exemplo: João da Silva");
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
                    System.out.println("Entrada inválida! Digite apenas números inteiros positivos.");
                }
            }
        }

        public static double lerDoublePositivo(String mensagem) {
            while (true) {
                try {
                    System.out.print(mensagem);
                    double valor = Double.parseDouble(sc.nextLine().replace(",", "."));
                    if (valor > 0) {
                        return valor;
                    } else {
                        System.out.println("O valor deve ser maior que zero!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite apenas números decimais. Exemplo: 1500.50");
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
                    System.out.println("⚠️ CPF inválido! Use o formato: 123.456.789-10");
                }
            }
        }

    }
