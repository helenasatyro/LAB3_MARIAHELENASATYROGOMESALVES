package testes;
import agenda.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContatoTest {
    private Contato contatoBase;
    private Contato contatoBaseDois;
    @BeforeEach
    void setUp() {
        this.contatoBase = new Contato("Matheus", "Gaudencio", "555-5551");
        this.contatoBaseDois = new Contato("Matheus", "Gaudencio", "555-5551");
    }

    @Test
    void testaContatoCompleto() {
        assertEquals("Matheus Gaudencio\n555-5551", contatoBase.getContatoCompleto());
    }

    @Test
    void testaToStringNormal() {
        assertEquals("Matheus Gaudencio", contatoBase.toString());
    }
    @Test
    void testaEqualsCompleto() {
        assert contatoBase.equals(contatoBase);
    }
    @Test
    void testaEqualsNomeSobrenome() {
        assert contatoBase.equals(contatoBaseDois);
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
        } catch (NullPointerException e) {
            assertEquals("CONTATO INVÁLIDO", e.getMessage());
        }
    }

    @Test
    void sobrenomeNulo() {
        try {
            Contato contatoNulo = new Contato("maria", null, "0947291");
            fail("Deve lançar exceção");
        } catch (NullPointerException e) {
            assertEquals("CONTATO INVÁLIDO", e.getMessage());
        }
    }

    @Test
    void telefoneNulo() {
        try {
            Contato contatoNulo = new Contato("maria", "helena", null);
            fail("Deve lançar exceção");
        } catch (NullPointerException e) {
            assertEquals("CONTATO INVÁLIDO", e.getMessage());
        }
    }
    @Test
    void nomeVazio() {
        try {
            Contato contatoVazio = new Contato("   ", "algo", "0947291");
            fail("Deve lançar exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("CONTATO INVÁLIDO", e.getMessage());
        }
        try {
            Contato contatoVazio = new Contato("", "algo", "0947291");
            fail("Deve lançar exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("CONTATO INVÁLIDO", e.getMessage());
        }
    }
    @Test
    void mudaFoneNulo() {
        try {
            contatoBase.setTelefone(null);
            fail("Deve lançar exceção");
        } catch (NullPointerException e) {
            assertEquals("TELEFONE INVÁLIDO", e.getMessage());
        }
    }
    @Test
    void mudaFoneVazio() {
        try {
            contatoBase.setTelefone("");
            fail("Deve lançar exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("TELEFONE INVÁLIDO", e.getMessage());
        }
    }
}