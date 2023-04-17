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

}