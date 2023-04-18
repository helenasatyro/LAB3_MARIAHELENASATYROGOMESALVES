package testes;
import agenda.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AgendaTest {
	private Agenda agendaBase = new Agenda();

	@BeforeEach
	void setUp() {
		agendaBase = new Agenda();
		agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		agendaBase.cadastraContato(3, "helena", "satyro", "0928130");
		agendaBase.cadastraContato(4, "maria", "helena", "477123");
		agendaBase.cadastraContato(100, "contato", "cem", "09374937390");

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
		assertEquals("CADASTRO REALIZADO",  agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000"));
		assertEquals(true, agendaBase.getContatos()[0] != null);
	}

	@Test
	void testaEhFavorito() {
		assertEquals(false, agendaBase.ehFavorito(2), "Não deve ser fav antes do cadastro, usa pos real");
		agendaBase.cadastraFavorito(3, 2);
		assertEquals(true, agendaBase.ehFavorito(2), "Deve ser fav após o cadastro, usa pos real.");
	}

	@Test
	void testaDefinirFavoritoPosVazia() {
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 1!", agendaBase.cadastraFavorito(3, 1), "Deve favoritar com sucesso na posição especificada");
		assert agendaBase.ehFavorito(3-1); // função do sistema usa posição real
	}

	@Test
	void testaDefinirFavoritoAbaixoLim() {
		assertEquals("POSIÇÃO INVÁLIDA", agendaBase.cadastraFavorito(3, 0), "Não deve cadastrar com sucesso ou levantar exceção");
	}
	@Test
	void testaDefinirFavoritoLim() {
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 10!", agendaBase.cadastraFavorito(3, 10), "Deve cadastrar com sucesso");
	}
	@Test
	void testaDefinirFavoritoAcimaLim() {
		assertEquals("POSIÇÃO INVÁLIDA", agendaBase.cadastraFavorito(3, 11), "Não deve cadastrar com sucesso ou levantar exceção");
	}
	@Test
	void testaDefinirFavoritoContatoLim() {
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 3!", agendaBase.cadastraFavorito(100, 3), "Deve cadastrar com sucesso");
	}

	@Test
	void testaDefinirFavoritoContatoAcimaLim() {
		assertEquals("POSIÇÃO INVÁLIDA", agendaBase.cadastraFavorito(101, 1), "Não deve cadastrar com sucesso ou levantar exceção");
	}
	@Test
	void testaDefinirFavoritoContatoAbaixoLim() {
		assertEquals("POSIÇÃO INVÁLIDA", agendaBase.cadastraFavorito(0, 1), "Não deve cadastrar com sucesso ou levantar exceção");
	}

	@Test
	void testaDefinirFavoritoPosOcupada() {
		agendaBase.cadastraFavorito(3, 1);
		agendaBase.cadastraFavorito(4, 1);
		assert !agendaBase.ehFavorito(2); // função do sistema usa posição real
		assert agendaBase.ehFavorito(3); // função do sistema usa posição real
	}

	@Test
	void testaContatoCompletoFavorito() {
		agendaBase.cadastraFavorito(1, 1);
		assertEquals("❤️ Matheus Gaudencio\n(83) 99999-0000", agendaBase.getContatoString(1), "Deve exibir um coração no favorito.");
	}

	@Test
	void testaContatoCompleto() {
		agendaBase.cadastraFavorito(1, 1);
		assertEquals("❤️ Matheus Gaudencio\n(83) 99999-0000", agendaBase.getContatoString(1), "Deve exibir um coração no favorito.");
		agendaBase.removeFavorito(1);
		assertEquals("Matheus Gaudencio\n(83) 99999-0000", agendaBase.getContatoString(1), "Coração deve ter sido removido");
	}
}
