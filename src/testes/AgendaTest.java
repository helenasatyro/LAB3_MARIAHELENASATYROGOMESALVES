package testes;
import agenda.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AgendaTest {
	private Agenda agendaBase = new Agenda();

	@BeforeEach
	void setUp() {
		agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		agendaBase.cadastraContato(3, "helena", "satyro", "0928130");
		agendaBase.cadastraContato(4, "maria", "helena", "477123");

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
		assert agendaBase.getContatoString(1).equals("Matheus Gaudencio\n(83) 99999-0000");
	}

	@Test
	void testaTemContato() {
		Agenda agenda = new Agenda();
		assert agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000").equals("CADASTRO REALIZADO");
	}

	@Test
	void testaDefinirFavoritoPosVazia() {
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 1!", agendaBase.cadastraFavorito(3, 1), "Deve favoritar com sucesso na posição especificada");
		assertEquals(true, agendaBase.ehFavorito(3), "Deve definir o contato 3 como favorito");
		assertEquals(true, agendaBase.getContato(3-1).getEhFavorito(), "atributo ehFavorito deve ser verdadeiro");
	}

	@Test
	void testaDefinirFavoritoPosOcupada() {
		agendaBase.cadastraFavorito(3, 1);
		agendaBase.cadastraFavorito(4, 1);
		assertEquals(false, agendaBase.ehFavorito(3-1), "Contato 3 deve ser falso");
		assertEquals(false, agendaBase.getContato(3-1).getEhFavorito(),"Contato 3 deve ser falso") ;
		assert agendaBase.ehFavorito(4);
		assert agendaBase.getContato(4-1).getEhFavorito();
	}

}
