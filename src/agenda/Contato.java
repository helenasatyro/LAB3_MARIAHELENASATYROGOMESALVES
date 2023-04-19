package agenda;
import java.util.Objects;
/**
 * Classe que mantém dados de um contato. Um contato deve ter nome e telefone não vazios, obrigatoriamente.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Contato {
    private final String nome;
    private final String sobrenome;
    private final String telefone;

    /**
     * Cria um contato recebendo como parâmetros um nome, sobrenome e telefone não nulos, nome e telefone não vazios.
     * @param nome não vazio e não nulo
     * @param sobrenome não nulo
     * @param telefone não vazio e não nulo
     */
    public Contato(String nome, String sobrenome, String telefone) {
        if (nome == null) {
            throw new NullPointerException("CONTATO INVÁLIDO - Nome nulo");
        }
        if (sobrenome == null) {
            throw new NullPointerException("CONTATO INVÁLIDO - Sobrenome nulo");
        }
        if (telefone == null) {
            throw new NullPointerException("CONTATO INVÁLIDO - Telefone nulo");
        }
        if (nome.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVÁLIDO - Nome vazio");
        }
        if (telefone.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVÁLIDO - Telefone vazio");
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }
    /**
     * Formata um contato para impressão na interface.
     *
     * @return A String formatada no modo curto com nome e telefone.
     */
    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
    /**
     * Formata um contato para impressão na interface.
     *
     * @return A String formatada com nome, sobrenome e telefone.
     */
    public String getContatoCompleto() {
        return nome + " " + sobrenome + "\n" + telefone;
    }

    /**
     * Checa se um objeto é igual a um contato.
     * Dois contatos são iguais se tiverem o mesmo nome e sobrenome.
     *
     * @return true ou false para a igualdade dos contatos.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() == Contato.class)) {
            return false;
        }
        Contato objContato = (Contato) obj;
        return nome.equals(objContato.nome) && this.sobrenome.equals(objContato.sobrenome)
                && this.hashCode() == objContato.hashCode();
    }

    /**
     * Cria um hashcode para a identificação do objeto a partir do nome e sobrenome
     *
     * @return o hashcode criado por java.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }
}