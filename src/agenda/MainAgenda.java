package agenda;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * Interface com menus texto para manipular uma agenda de contatos.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
public class MainAgenda {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Agenda agenda = new Agenda();

		LeitorDeAgenda leitor = new LeitorDeAgenda();
		System.out.println("Carregando agenda inicial");

		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
			 */
			leitor.carregaContatos("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

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
			comando(scanner.nextLine().toUpperCase(), agenda, scanner);
		}
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 *
	 * @param opcao   Opção digitada.
	 * @param agenda a ser usada no programa
	 * @param scanner a ser usado no programa
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
			case "C" -> cadastraContato(agenda, scanner);
			case "L" -> listaContatos(agenda);
			case "E" -> exibeContato(agenda, scanner);
			case "S" -> sai();
			case "F" -> verFavoritos(agenda);
			case "A" -> addFavorito(agenda, scanner);
			case "R" -> removerFavorito(agenda, scanner);
			case "M" -> mudaFone(agenda, scanner);
			default -> System.out.println("OPÇÃO INVÁLIDA!");
		}
	}

	/**
	 * Altera o telefone de um contato
	 *
	 * @param agenda a ser usada no programa
	 * @param scanner a ser usado no programa
	 */
	private static void mudaFone(Agenda agenda, Scanner scanner) {
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
	 * @param agenda a ser usada no programa
	 * @param scanner a ser usado no programa
	 */
	private static void removerFavorito(Agenda agenda, Scanner scanner) {
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
	 * Exibe uma string informando o status de sucesso ou falha da operação.
	 * @param agenda a ser usada no programa
	 * @param scanner a ser usado no programa
	 */
	private static void addFavorito(Agenda agenda, Scanner scanner) {
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
		try {
			agenda.cadastraFavorito(cont, pos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + (pos) + "!");

	}
	/**
	 * Imprime a lista de favoritos.
	 * @param agenda a ser usada no programa
	 */
	private static void verFavoritos(Agenda agenda) {
		System.out.println(agenda.getFavoritos());
	}

	/**
	 * Imprime lista de contatos da agenda.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		System.out.println(agenda);
	}

	/**
	 * Recebe dados para cadastrar um contato na agenda.
	 * @param agenda a ser usada no programa
	 * @param scanner a ser usado no programa
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
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
		try {
			agenda.cadastraContato(posicao, nome, sobrenome, telefone);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("CADASTRO REALIZADO");


	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda.
	 * @param agenda a ser usada no programa
	 * @param scanner a ser usado no programa
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
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
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}
}