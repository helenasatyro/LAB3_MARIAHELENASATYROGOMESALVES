package agenda;
import java.util.Objects;
/**
 * Classe que mantém dados de um contato. Um contato deve ter nome e telefone não vazios, obrigatoriamente.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Contato {
    private String nome;
    private String sobrenome;
    private String telefone;

    /**
     * Cria um contato recebendo como parâmetros um nome, sobrenome e telefone não nulos, nome e telefone não vazios.
     * @param nome não vazio e não nulo
     * @param sobrenome não nulo
     * @param telefone não vazio e não nulo
     * @throws NullPointerException se algum parâmetro for nulo
     * @throws IllegalArgumentException se nome ou telefone forem vazios
     */
    public Contato(String nome, String sobrenome, String telefone) throws IllegalArgumentException, NullPointerException  {
        if (nome == null) {
            throw new NullPointerException("CONTATO INVÁLIDO - Nome Nulo");
        }
        if (sobrenome == null) {
            throw new NullPointerException("CONTATO INVÁLIDO - Sobrenome Nulo");
        }
        if (telefone == null) {
            throw new NullPointerException("CONTATO INVÁLIDO - Telefone Nulo");
        }
        if (nome.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVÁLIDO - Nome Vazio");
        }
        if (telefone.isBlank()) {
            throw new IllegalArgumentException("CONTATO INVÁLIDO - Telefone Vazio");
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

    /**
     * Define um novo telefone para um contato.
     * @param fone o telefone em string
     * @throws NullPointerException se o parâmetro não for passado
     * @throws IllegalArgumentException se o par?âmetro for vazio
     */
    public void setTelefone(String fone) throws NullPointerException, IllegalArgumentException {
        if (fone == null) {
            throw new NullPointerException("TELEFONE INVÁLIDO");
        }
        if (fone.isBlank()) {
            throw new IllegalArgumentException("TELEFONE INVÁLIDO");
        }
        telefone = fone;
    }
}