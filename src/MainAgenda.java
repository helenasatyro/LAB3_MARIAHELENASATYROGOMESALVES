import java.util.Scanner;
/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author Maria Helena Sátyro Gomes Alves
 *
 */
public class MainAgenda {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Agenda agenda = new Agenda();
		Menu menu = new Menu(scanner, agenda);
		menu.abreMenu();
	}
}
