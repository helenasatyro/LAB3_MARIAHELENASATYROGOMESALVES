package agenda;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Classe de lógica da interface com menus texto para manipular uma agenda de contatos.
 *
 * @author Maria Helena Sátyro Gomes Alves
 *
 */
public class Menu {
    private final Scanner scanner;
    private final Agenda agenda;

    /**
     * Cria um menu com uma agenda e carrega contatos de um arquivo csv para ela.
     *
     * @param scanner para entrada de dados
     * @param agenda objeto Agenda para guardar os contatos
     * @param nomeArquivo nome do arquivo csv a ser lido
     */
    public Menu(Scanner scanner, Agenda agenda, String nomeArquivo) {
        this.scanner = scanner;
        this.agenda = agenda;
        LeitorDeAgenda leitor = new LeitorDeAgenda();
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
    /**
     * Cria um menu com uma agenda e carrega contatos de um arquivo csv pré-definido para ela.
     *
     * @param scanner para entrada de dados
     * @param agenda objeto Agenda para guardar os contatos
     * */
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
                            "(M)udar Telefone\n" +
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
            case "M" -> mudaFone();
            default -> System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private void mudaFone() {
        System.out.print("Contato> ");
        int cont;
        try {
            cont = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        System.out.println("Novo telefone> ");
        String fone = scanner.nextLine();
        try {
            agenda.mudaFone(cont, fone);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("TELEFONE ALTERADO PARA " + fone);
    }

    /**
     * Exibe os prompts para coletar dados para remover um contato da lista de favoritos.
     */
    private void removerFavorito() {
        System.out.print("Posicao> ");
        int pos;
        try {
            pos = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        try {
            agenda.removeFavorito(pos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("REMOVIDO!");
    }
    /**
     * Exibe os prompts para coletar dados para adicionar um contato à lista de favoritos.
     */
    private void addFavorito() {
        System.out.print("Contato> ");
        int cont;
        int pos;
        try {
            cont = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        System.out.print("Posicao> ");
        try {
            pos = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        System.out.println(agenda.cadastraFavorito(cont, pos));
    }
    /**
     * Imprime a lista de favoritos.
     */
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
     * Recebe dados para cadastrar um contato na agenda.
     */
    private void cadastraContato() {
        System.out.print("\nPosição na agenda> ");
        int posicao;
        try {
            posicao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e ) {
            System.out.println("POSIÇÃO INVÁLIDA");
            return;
        }
        System.out.print("Nome> ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome> ");
        String sobrenome = scanner.nextLine();
        System.out.print("Telefone> ");
        String telefone = scanner.nextLine();
        System.out.println(agenda.cadastraContato(posicao, nome, sobrenome, telefone));

    }

    /**
     * Imprime os detalhes de um dos contatos da agenda.
     */
    private void exibeContato() {
        System.out.print("\nQual contato> ");
        int posicaoUser;
        try {
            posicaoUser = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e ) {
            System.out.println("POSIÇÃO INVÁLIDA!");
            return;
        }
        System.out.println("Dados do contato:\n" + agenda.getContatoString(posicaoUser));
    }

    /**
     * Sai da aplicação.
     */
    private void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }
}
