package com.colini;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.colini.db.FabricaConexoes;
import com.colini.models.Pessoas;
import com.colini.repositories.PessoasRepository;

public class Main {
    public static void main(String[] args) throws SQLException {
        PessoasRepository controladorBD = new PessoasRepository();

        try (Connection connection = FabricaConexoes.getInstance().getConnection();) {
            System.out.println("\nConexão aberta!\n\n");
            controladorBD.createTableIfNotExists(connection);
            Scanner scan = new Scanner(System.in);
            String nome, dataNascimento, cpf, email;
            int resposta;

            while (true) {
                System.out.printf("\n");
                controladorBD.coletaDados(connection);
                System.out.printf("\n");
                menuInicial();
                resposta = scan.nextInt();
                scan.nextLine();

                switch (resposta) {
                    case 1:
                        nome = solicitarString("Nome: ", scan);
                        dataNascimento = solicitarData("Data de Nascimento (aaaa-MM-dd): ", scan);
                        cpf = solicitarCPF("CPF (11 caracteres): ", scan);
                        email = solicitarString("Email: ", scan);

                        Pessoas pessoa = new Pessoas(nome, dataNascimento, cpf, email);
                        controladorBD.insereDados(pessoa, connection);
                        break;
                    case 2:
                        cpf = solicitarCPF("CPF (11 caracteres): ", scan);
                        controladorBD.deletaDados(cpf, connection);
                        break;
                    case 3:
                        menuUpdate();
                        int respostaUpdate = scan.nextInt();
                        scan.nextLine();
                        switch (respostaUpdate) {
                            case 1:
                                cpf = solicitarCPF("CPF da pessoa que o nome sera alterado (11 caracteres): ", scan);
                                nome = solicitarString("Nome novo: ", scan);
                                controladorBD.alteraNome(nome, cpf, connection);
                                break;
                            case 2:
                                cpf = solicitarCPF(
                                        "CPF da pessoa que a data de nascimento sera alterada (11 caracteres): ", scan);
                                dataNascimento = solicitarData("Data de Nascimento nova (aaaa-MM-dd): ", scan);
                                controladorBD.alteraDataNascimento(dataNascimento, cpf, connection);
                                break;
                            case 3:
                                cpf = solicitarCPF("CPF da pessoa que o email sera alterado (11 caracteres): ", scan);
                                email = solicitarString("Email novo: ", scan);
                                controladorBD.alteraEmail(email, cpf, connection);
                                break;
                            case 0:
                                break;

                            default:
                                break;
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                if (resposta == 0) {
                    break;
                }
            }
            scan.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String solicitarString(String mensagem, Scanner scan) {
        System.out.print(mensagem);
        return scan.nextLine();
    }

    private static String solicitarData(String mensagem, Scanner scan) {
        String data;
        while (true) {
            System.out.print(mensagem);
            data = scan.nextLine();
            if (data.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;
            } else {
                System.out.println("Formato inválido. Use o formato aaaa-MM-dd.");
            }
        }
        return data;
    }

    private static String solicitarCPF(String mensagem, Scanner scan) {
        String cpf;
        while (true) {
            System.out.print(mensagem);
            cpf = scan.nextLine();
            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. Certifique-se de que tem exatamente 11 dígitos numéricos.");
            }
        }
        return cpf;
    }

    private static void menuInicial() {
        System.out.println("Menu:");
        System.out.println("1 - Adicionar pessoa");
        System.out.println("2 - Deletar pessoa");
        System.out.println("3 - Atualizar pessoa");
        System.out.println("0 - Sair");
    }

    private static void menuUpdate() {
        System.out.println("\nMenu atualizar:");
        System.out.println("1 - Atualizar nome");
        System.out.println("2 - Atualizar data de nascimento");
        System.out.println("3 - Atualizar email");
        System.out.println("0 - Voltar");
    }
}
