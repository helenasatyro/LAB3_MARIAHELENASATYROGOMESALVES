package testes;
import agenda.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AgendaTest {
	private Agenda agendaBase = new Agenda();

	@BeforeEach
	void setUp() {
		agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
	}

	@Test
	void testaConstrutorPosVazia() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000").equals("CADASTRO REALIZADO");
	}

	@Test
	void testaConstrutorPosExistente() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assert agenda.cadastraContato(1, "Pedro", "Silva", "(84) 98888-1111").equals("CADASTRO REALIZADO");
	}

	@Test
	void testaContatoDuplicado() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assert agenda.cadastraContato(3, "Matheus", "Gaudencio", "(83) 99999-0000").equals("CONTATO JA CADASTRADO");
	}

	@Test
	void testaConstrutorLimite() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(100, "Matheus", "Gaudencio", "(83) 99999-0000").equals("CADASTRO REALIZADO");
	}
	@Test
	void testaConstrutorAcimaLimite() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(101, "Matheus", "Gaudencio", "(83) 99999-0000").equals("POSIÇÃO INVÁLIDA");
	}

	@Test
	void testaConstrutorAbaixoLimite() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(0, "Matheus", "Gaudencio", "(83) 99999-0000").equals("POSIÇÃO INVÁLIDA");
	}

	@Test
	void testaConstrutorSemTelefone() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(1, "Matheus", "Gaudencio", "").equals("CONTATO INVÁLIDO");
	}

	@Test
	void testaConstrutorSemNome() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(1, "", "Gaudencio", "(83) 99999-0000").equals("CONTATO INVÁLIDO");
	}

	@Test
	void testaRepresentacaoContato() {
		assert agendaBase.getContato(1).equals("Matheus Gaudencio\n(83) 99999-0000");
	}

}
