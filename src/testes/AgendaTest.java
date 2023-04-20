package testes;
import agenda.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.IdentityHashMap;

import static org.junit.jupiter.api.Assertions.*;


class AgendaTest {
	private Agenda agendaBase;

	@BeforeEach
	void setUp() {
		agendaBase = new Agenda();
		agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		agendaBase.cadastraContato(3, "helena", "satyro", "0928130");
		agendaBase.cadastraContato(4, "maria", "helena", "477123");
		agendaBase.cadastraContato(100, "contato", "cem", "09374937390");

	}

	@Test
	void testaCadastraPosVazia() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("Matheus Gaudencio", agenda.getContatos()[0].toString());
	}

	@Test
	void testaCadastraSobrescrito() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("Matheus Gaudencio", agenda.getContatos()[0].toString());
		agenda.cadastraContato(1, "Pedro", "Silva", "(84) 98888-1111");
		assertEquals("Pedro Silva", agenda.getContatos()[0].toString());
	}

	@Test
	void testaCadastraContatoDuplicado() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		try {
			agenda.cadastraContato(3, "Matheus", "Gaudencio", "(83) 99999-0000");
			fail("Deve lançar exceção");
		} catch (IllegalCallerException e ) {
			assertEquals("CONTATO JÁ CADASTRADO", e.getMessage());
		}
	}

	@Test
	void testaCadastraLimite() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(100, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertEquals("Matheus Gaudencio", agenda.getContatos()[99].toString());
	}
	@Test
	void testaCadastraAcimaLimite() {
		Agenda agenda = new Agenda();
		try {
			agenda.cadastraContato(101, "Matheus", "Gaudencio", "(83) 99999-0000");
			fail("Deve lançar exceção");
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
		}
	}

	@Test
	void testaCadastraAbaixoLimite() {
		Agenda agenda = new Agenda();
		try {
			agenda.cadastraContato(0, "Matheus", "Gaudencio", "(83) 99999-0000");
			fail("Deve lançar exceção");
		} catch (ArrayIndexOutOfBoundsException e) {
			assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
		}
	}

	@Test
	void testaCadastraSemTelefone() {
		Agenda agenda = new Agenda();
		try {
			agenda.cadastraContato(3, "Matheus", "Gaudencio", "");
			fail("Deve lançar exceção");
		} catch (IllegalArgumentException e ) {
			assertEquals("CONTATO INVÁLIDO - Telefone Vazio", e.getMessage());
		}
	}

	@Test
	void testaCadastraSemNome() {
		Agenda agenda = new Agenda();

		try {
			agenda.cadastraContato(1, "", "Gaudencio", "(83) 99999-0000");
			fail("Deve lançar exceção");
		} catch (IllegalArgumentException e) {
			assertEquals("CONTATO INVÁLIDO - Nome Vazio", e.getMessage());
		}
	}

	@Test
	void testaRepresentacaoContato() {
		assert agendaBase.getContatoString(1).equals("Matheus Gaudencio\n(83) 99999-0000");
	}

	@Test
	void testaTemContato() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Matheus", "Gaudencio", "(83) 99999-0000");
		assertNotNull(agendaBase.getContatos()[0], "Deve ter contato cadastrado na posição.");
	}

	@Test
	void testaEhFavorito() {
		assertFalse(agendaBase.ehFavorito(2), "Não deve ser fav antes do cadastro, usa pos real"); // função do sistema usa posição real
		agendaBase.cadastraFavorito(3, 2);
		assertTrue(agendaBase.ehFavorito(2), "Deve ser fav após o cadastro, usa pos real."); // função do sistema usa posição real
	}

	@Test
	void testaDefinirFavoritoPosVazia() {
		agendaBase.cadastraFavorito(3, 1); // "Deve favoritar com sucesso na posição especificada");
		assertTrue(agendaBase.ehFavorito(3-1), "Deve favoritar o contato com sucesso"); // função do sistema usa posição real
	}

	@Test
	void testaDefinirFavoritoAbaixoLim() {
		try {
			agendaBase.cadastraFavorito(3, 0);
			fail("Deve lançar exceção");
		} catch (Exception e) {
			assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
		}

	}
	@Test
	void testaDefinirFavoritoLim() {
		agendaBase.cadastraFavorito(3, 10);
		assertTrue(agendaBase.ehFavorito(2), "Deve cadastrar com sucesso"); // função do sistema usa posição real
	}
	@Test
	void testaDefinirFavoritoAcimaLim() {
		try {
			agendaBase.cadastraFavorito(3, 11);
			fail("Deve lançar exceção");
		} catch (Exception e) {
			assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
		}
	}
	@Test
	void testaDefinirFavoritoContatoLim() {
		agendaBase.cadastraFavorito(100, 3);
		assertTrue(agendaBase.ehFavorito(99),"Deve cadastrar com sucesso"); // função do sistema usa posição real

	}

	@Test
	void testaDefinirFavoritoContatoAcimaLim() {
		try {
			agendaBase.cadastraFavorito(101, 1);
			fail("Deve lançar exceção");
		} catch (Exception e) {
			assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
		}
	}
	@Test
	void testaDefinirFavoritoContatoAbaixoLim() {
		try {
			agendaBase.cadastraFavorito(0, 1);
			fail("Deve lançar exceção");
		} catch (Exception e) {
			assertEquals("POSIÇÃO INVÁLIDA", e.getMessage());
		}
	}

	@Test
	void testaDefinirFavoritoSobrescrito() {
		agendaBase.cadastraFavorito(3, 1);
		agendaBase.cadastraFavorito(4, 1);
		assertFalse(agendaBase.ehFavorito(2), "Deve remover a condição de favorito do contato sobrescrito"); // função do sistema usa posição real
		assertTrue(agendaBase.ehFavorito(3), "Deve favoritar um novo contato"); // função do sistema usa posição real
	}

	@Test
	void testaContatoCompletoFavorito() {
		agendaBase.cadastraFavorito(1, 1);
		assertEquals("❤️ Matheus Gaudencio\n(83) 99999-0000", agendaBase.getContatoString(1), "Deve exibir um coração no favorito.");
	}

	@Test
	void testaContatoCompleto() {
		agendaBase.cadastraFavorito(1, 1);
		assert agendaBase.ehFavorito(0);
		assertEquals("❤️ Matheus Gaudencio\n(83) 99999-0000", agendaBase.getContatoString(1), "Deve exibir um coração no favorito.");
		agendaBase.removeFavorito(1);
		assertEquals("Matheus Gaudencio\n(83) 99999-0000", agendaBase.getContatoString(1), "Coração deve ter sido removido");
	}

	@Test
	void testaGetFavoritos() {
		agendaBase.cadastraFavorito(1, 1);
		agendaBase.cadastraFavorito(3, 2);
		agendaBase.cadastraFavorito(4, 10);
		assertEquals("1 - Matheus Gaudencio\n2 - helena satyro\n10 - maria helena\n", agendaBase.getFavoritos());
	}

	@Test
	void testaGetAgendaString() {
		agendaBase.cadastraFavorito(1, 1);
		agendaBase.cadastraFavorito(3, 2);
		agendaBase.cadastraFavorito(4, 10);
		assertEquals("1 - Matheus Gaudencio\n3 - helena satyro\n4 - maria helena\n100 - contato cem\n", agendaBase.toString());
	}

}
