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
            default -> {
                System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
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
                    System.out.println("CONTATO JA CADASRADO");
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
     * Lê uma agenda de um arquivo csv.
     *
     * @param arquivoContatos O caminho para o arquivo.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private void carregaAgenda(String arquivoContatos) throws FileNotFoundException, IOException {
        LeitorDeAgenda leitor = new LeitorDeAgenda();

        int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
        System.out.println("Carregamos " + carregados + " registros.");
    }

    /**
     * Imprime os detalhes de um dos contatos da agenda.
     */
    private void exibeContato() {
        System.out.print("\nQual contato> ");
        try {
            int posicao = Integer.parseInt(scanner.nextLine()) - 1;
            if (agenda.temContato(posicao)) {
                Contato contato = agenda.getContato(posicao);
                System.out.println("Dados do contato:\n" + contato.getContatoCompleto());
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

