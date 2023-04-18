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
    public String getContatoString(int posicao) {
        posicao--;
        if (ehFavorito(posicao)) {
            return "❤️ " + contatos[posicao].getContatoCompleto();
        } else {
            return contatos[posicao].getContatoCompleto();
        }
    }

    public Contato getContato(int posicao) {
        return contatos[posicao];
    }

    /**
     * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     * @param posicao Posição do contato.
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone Telefone do contato.
     */
    public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
        posicao--;
        if (!(posicao >= 0 && posicao <= 99)) {
            return "POSIÇÃO INVÁLIDA";
        }
        if (nome.equals("")) {
            return "CONTATO INVÁLIDO";
        }
        if (telefone.equals("")) {
            return "CONTATO INVÁLIDO";
        }
        Contato contatoCriado = new Contato(nome, sobrenome, telefone);
        for (int i=0; i < 100; i++) {
            if (contatos[i] != null) {
                if (contatos[i].equals(contatoCriado)) {
                    return "CONTATO JA CADASTRADO";
                }
            }
        }
            this.contatos[posicao] = contatoCriado;
            return "CADASTRO REALIZADO";
    }
    /**
     * Verifica se há contato na posição
     * @return O array de contatos.
     */
    public Contato[] getContatos() {
        return contatos.clone();
    }

    public String cadastraFavorito(int posContato, int posFav) {

        if (contatos[posContato-1] == null || !(posFav <= 10 && posFav >= 1) || !(posContato <= 100 && posContato >= 1) ) {
            return "POSIÇÃO INVÁLIDA";
        }
        if (ehFavorito(posContato -1)) {
            return "CONTATO JÁ É FAVORITO";
        }
        if (favoritos[posFav-1] != null) {
            removeFavorito(posFav-1);
        }
        favoritos[posFav-1] = contatos[posContato-1];
        return "CONTATO FAVORITADO NA POSIÇÃO " + (posFav) + "!";
    }

    public String removeFavorito(int posFav) {
        posFav--;
        if (!(posFav <= 9 && posFav >= 0)) {
            return "POSIÇÃO INVÁLIDA";
        }
        if (favoritos[posFav] == null) {
            return "POSIÇÃO INVÁLIDA";
        }
        favoritos[posFav] = null;
        return "REMOVIDO!";
    }

    public boolean ehFavorito(int pos) {
        for (int i=0; i<10; i++) {
            if (favoritos[i] != null) {
                if (favoritos[i].equals(contatos[pos])) {
                    return true;
                }
            }
        }
        return false;
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