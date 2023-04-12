
public class Contato {
    private final String nome;
    private final String sobrenome;
    private final String telefone;
    private boolean ehFavorito;

    Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.ehFavorito = false;
    }

    String getNome() {
        return nome;
    }

    String getSobrenome() {
        return sobrenome;
    }

    String getTelefone() {
        return telefone;
    }

    Contato getContato() {
        return this;
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
    String getContatoCompleto() {
        return nome + " " + sobrenome + "\n" + telefone;
    }

    void setFavorito(boolean valor) {
        ehFavorito = valor;
    }

    boolean getEhFavorito() {
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
        return nome.equalsIgnoreCase(contObj.nome) && this.sobrenome.equalsIgnoreCase(contObj.sobrenome);
    }
}