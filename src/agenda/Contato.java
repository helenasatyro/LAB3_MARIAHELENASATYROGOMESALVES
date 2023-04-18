package agenda;

import java.util.Objects;

public class Contato {
    private final String nome;
    private final String sobrenome;
    private final String telefone;
    public Contato(String nome, String sobrenome, String telefone) {
        if (nome == null || telefone == null) {
            throw new NullPointerException("Nome e Telefone não podem ser nulos.");
        }
        if (sobrenome == null) { sobrenome = "";}
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
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
        return nome + " " + sobrenome + "\n" + telefone;
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