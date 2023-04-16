package agenda;
public class Agenda {
    private static final int TAMANHO_AGENDA = 100;
    private static final int TAMANHO_FAVS = 10;
    private final Contato[]  contatos;
    private final Contato[] favoritos;

    /**
     * Cria uma agenda.
     */
    public Agenda() {
        this.contatos = new Contato[TAMANHO_AGENDA];
        this.favoritos = new Contato[TAMANHO_FAVS];
    }


    /**
     * Acessa os dados de um contato específico.
     * @param posicao Posição do contato na agenda.
     * @return Dados do contato. Null se não há contato na posição.
     */
    public String getContato(int posicao) {
        return contatos[posicao].getContatoCompleto();
    }

    /**
     * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     * @param posicao Posição do contato.
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone Telefone do contato.
     */
    public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
        Contato contatoCriado = new Contato(nome, sobrenome, telefone);
        for (Contato cont : contatos) {
            if(cont != null)
                if (cont.equals(contatoCriado)) {
                    return false;
                }
        }
        this.contatos[posicao] = contatoCriado;
        return true;
    }
    /**
     * Verifica se há contato na posição
     * @return O array de contatos.
     */
    public boolean temContato(int pos) {
        if (pos >= 0 && pos <= 99) {
            return contatos[pos] != null;
        }
        return false;
    }

    public boolean cadastraFavorito(int posContato, int posFav) {
        if (ehFavorito(posContato)) {
            return false;
        } else {
            if (favoritos[posFav] != null) {
                removeFavorito(posFav);
            }
            contatos[posContato].setFavorito(true);
            favoritos[posFav] = contatos[posContato];
            return true;
        }
    }

    public boolean removeFavorito(int posFav) {
        if (favoritos[posFav] == null) {
            return false;
        } else {
            favoritos[posFav].setFavorito(false);
            favoritos[posFav] = null;
        }
        return true;
    }

    public boolean ehFavorito(int pos) {
        return contatos[pos].getEhFavorito();
    }

    public String getFavoritos() {
        String retorno = "";
        for (int i=0; i < TAMANHO_FAVS; i++) {
            if (favoritos[i] != null) {
                retorno += (i +1) + " - " + favoritos[i] + "\n";
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                retorno += "\n" + (i+1) + " - " + contatos[i];
            }
        }
        return retorno;
    }
}