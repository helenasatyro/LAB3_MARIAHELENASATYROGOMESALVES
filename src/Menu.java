import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu {
    private final Scanner scanner;
    private final Agenda agenda;
    private final LeitorDeAgenda leitor;
    public Menu(Scanner scanner, Agenda agenda, String nomeArquivo) {
        this.scanner = scanner;
        this.agenda = agenda;
        this.leitor = new LeitorDeAgenda();
        System.out.println("Carregando agenda inicial");

        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            leitor.carregaContatos(nomeArquivo, agenda);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }
    }
    public Menu(Scanner scanner, Agenda agenda) {
        this(scanner, agenda, "agenda_inicial.csv");
    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.*
     */
    public void abreMenu() {
        while (true) {
            System.out.print(
                    "\n---\nMENU\n" +
                            "(C)adastrar Contato\n" +
                            "(L)istar Contatos\n" +
                            "(E)xibir Contato\n" +
                            "(F)avoritos\n" +
                            "(A)dicionar Favorito\n" +
                            "(R)emover Favorito\n" +
                            "(S)air\n" +
                            "\n" +
                            "Opção> ");
            comando(scanner.nextLine().toUpperCase());
        }
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     */
    private void comando(String opcao) {
        switch (opcao) {
            case "C" -> cadastraContato();
            case "L" -> listaContatos();
            case "E" -> exibeContato();
            case "S" -> sai();
            case "F" -> verFavoritos();
            case "A" -> addFavorito();
            case "R" -> removerFavorito();
            default -> {
                System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
    }

    private void removerFavorito() {
        System.out.print("Posicao> ");
        int pos;
        try {
            pos = Integer.parseInt(scanner.nextLine()) -1;
        } catch (NumberFormatException e) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        if (pos <= 9 && pos >= 0) {
            if (!agenda.removeFavorito(pos)) {
                System.out.println("CONTATO NÃO É FAVORITO");
            } else {
                return;
            }
        } else {
        System.out.println("POSIÇÃO INVÁLIDA");
        }
    }

    private void addFavorito() {
        System.out.print("Contato> ");
        int cont;
        int pos;
        try {
            cont = Integer.parseInt(scanner.nextLine()) -1;
        } catch (NumberFormatException e) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        if (agenda.temContato(cont)) {
            if (!agenda.ehFavorito(cont)) {
                System.out.print("Posicao> ");
                try {
                    pos = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("POSIÇÃO INVÁLIDA");
                    return;
                }
                if (pos <= 9 && pos >= 0) {
                    agenda.cadastraFavorito(cont, pos);
                    System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + (pos + 1) + "!");
                } else {
                    System.out.println("POSIÇÃO INVÁLIDA");
                }
            } else {
                System.out.println("CONTATO JÁ É FAVORITO");
            }
        } else {
            System.out.println("POSIÇÃO INVÁLIDA");
        }
    }

    private void verFavoritos() {
        System.out.println(agenda.getFavoritos());
    }

    /**
     * Imprime lista de contatos da agenda.
     */
    private void listaContatos() {
        System.out.println("\nLista de contatos: ");
        System.out.println(agenda);
    }

    /**
     * Cadastra um contato na agenda.
     */
    private void cadastraContato() {
        System.out.print("\nPosição na agenda> ");
        String strPosicao = scanner.nextLine();

        try {
            int posicao = Integer.parseInt(strPosicao) -1;
            if (posicao >= 0 && posicao <= 99) {
                System.out.print("Nome> ");
                String nome = scanner.nextLine();
                if (nome.equals("")) {
                    System.out.println("CONTATO INVÁLIDO");
                    return;
                }
                System.out.print("Sobrenome> ");
                String sobrenome = scanner.nextLine();
                System.out.print("Telefone> ");
                String telefone = scanner.nextLine();
                if (telefone.equals("")) {
                    System.out.println("CONTATO INVÁLIDO");
                    return;
                }
                if (!agenda.cadastraContato(posicao, nome, sobrenome, telefone)) {
                    System.out.println("CONTATO JA CADASTRADO");
                }
            } else {
                System.out.println("POSIÇÃO INVÁLIDA");
            }

        } catch (NumberFormatException e ) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
    }

    /**
     * Imprime os detalhes de um dos contatos da agenda.
     */
    private void exibeContato() {
        System.out.print("\nQual contato> ");
        try {
            int posicao = Integer.parseInt(scanner.nextLine()) - 1;
            if (agenda.temContato(posicao)) {
                if (agenda.ehFavorito(posicao)){
                    System.out.println("Dados do contato:\n" + "❤️ " + agenda.getContato(posicao));
                } else {
                    System.out.println("Dados do contato:\n" + agenda.getContato(posicao));
                }
            } else {
                System.out.println("POSIÇÃO INVÁLIDA!");
            }
        } catch (NumberFormatException e ) {
            System.out.println("POSIÇÃO INVÁLIDA!");
        }
    }

    /**
     * Sai da aplicação.
     */
    private void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }
}

