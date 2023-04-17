package testes;
import agenda.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContatoTest {
    private Contato contatoBase;
    private Contato contatoBaseFavorito;
    @BeforeEach
    void setUp() {
        this.contatoBase = new Contato("Matheus", "Gaudencio", "555-5551");
        this.contatoBaseFavorito = new Contato("Matheus", "Gaudencio", "555-5551");
        contatoBaseFavorito.setFavorito(true);
    }

    @Test
    void testaContatoCompletogNormal() {
        assert contatoBase.getContatoCompleto().equals("Matheus Gaudencio\n555-5551");
    }

    @Test
    void testaContatoCompletoFavorito() {
        assert contatoBaseFavorito.getContatoCompleto().equals("❤️ Matheus Gaudencio\n555-5551");
    }

    @Test
    void testaToStringNormal() {
        assert contatoBase.toString().equals("Matheus Gaudencio");
    }
    @Test
    void testaToStringFavorito() {
        assert contatoBaseFavorito.toString().equals("Matheus Gaudencio");
    }
    @Test
    void testaEqualsCompleto() {
        assert contatoBase.equals(contatoBase);
    }
    @Test
    void testaEqualsNomeSobrenome() {
        assert contatoBase.equals(contatoBaseFavorito);
    }

    @Test
    void testaNotEquals() {
        Contato diferente = new Contato("livia", "sampaio", "83888888");
        assert !contatoBase.equals(diferente);
    }

    @Test
    void testaEqualsNulo() {
        try {
            assert !contatoBase.equals(null);
        } catch (Exception e) {
            fail("Não deve lançar erro");
        }
    }

    @Test
    void testaEqualsObjDiferente() {
        String oi = "oi";
        try {
            assert !contatoBase.equals(oi);
        } catch (Exception e) {
            fail("Não deve lançar erro");
        }
    }

    @Test
    void nomeNulo() {
        try {
            Contato contatoNulo = new Contato(null, "helena", "0947291");
            fail("Deve lançar exceção");
        } catch (Exception e) {
            assertEquals("Nome e Telefone não podem ser nulos.", e.getMessage());
        }
    }

    @Test
    void sobrenomeNulo() {
        try {
            Contato contatoNulo = new Contato("maria", null, "0947291");
        } catch (Exception e) {
            fail("Não deve lançar exceção");
        }
    }

    @Test
    void telefoneNulo() {
        try {
            Contato contatoNulo = new Contato("maria", "helena", null);
            fail("Deve lançar exceção");
        } catch (Exception e) {
            assertEquals("Nome e Telefone não podem ser nulos.", e.getMessage());
        }
    }


    @Test
    void testaEhFavorito() {
        assert contatoBaseFavorito.getEhFavorito();
        assert !contatoBase.getEhFavorito();
    }

    @Test
    void testaSetFavorito() {
        contatoBaseFavorito.setFavorito(false);
        assert !contatoBaseFavorito.getEhFavorito();
    }

}