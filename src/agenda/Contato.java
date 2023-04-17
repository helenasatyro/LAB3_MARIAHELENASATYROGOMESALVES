package agenda;

import java.util.Objects;

public class Contato {
    private final String nome;
    private final String sobrenome;
    private final String telefone;
    private boolean ehFavorito;

    public Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.ehFavorito = false;
    }
    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
    /**
     * Formata um contato para impressão na interface.
     *
     * @return A String formatada.
     */
    public String getContatoCompleto() {
        if (this.ehFavorito) {
            return "❤️ " + nome + " " + sobrenome + "\n" + telefone;
        } else {
            return nome + " " + sobrenome + "\n" + telefone;
        }
    }

    public void setFavorito(boolean valor) {
        ehFavorito = valor;
    }

    public boolean getEhFavorito() {
        return this.ehFavorito;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() == Contato.class)) {
            return false;
        }

        Contato contObj = (Contato) obj;
        return nome.equalsIgnoreCase(contObj.nome) && this.sobrenome.equalsIgnoreCase(contObj.sobrenome)
                && this.hashCode() == contObj.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }
}