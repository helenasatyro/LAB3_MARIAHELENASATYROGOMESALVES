package agenda;

/**
 * Classe que mantém uma lista de contatos com posições. Pode ter 100 contatos.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
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
     * Acessa a representação completa em String de um contatato.
     * @param posicaoUser Posição do contato na agenda, na visão do usuário.
     * @return Dados do contato. "posição inválida" se ele não existir se não há contato na posição.
     */
    public String getContatoString(int posicaoUser) {
        if (posicaoUser <= 100 && posicaoUser >= 1) {
            int posicao = posicaoUser -1;
            if (getContatos()[posicao] != null) {
                if (ehFavorito(posicao)) {
                    return "❤️ " + contatos[posicao].getContatoCompleto();
                } else {
                    return contatos[posicao].getContatoCompleto();
                }
            } else {
                return "POSIÇÃO INVÁLIDA!";
            }
        }
        return "POSIÇÃO INVÁLIDA!";
    }

    /**
     * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     * @param posicao Posição do contato, na visão do usuário.
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone Telefone do contato em String.
     */
    public String cadastraContato(int posicaoUser, String nome, String sobrenome, String telefone) {
        int posicao = posicaoUser -1;
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

    /**
     * Cadastra um contato na lista de favoritos em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior e remove seu status de favorito.
     * @param posContato Posição do contato na lista de contatos, na visão do usuário.
     * @param posFav Posição que o contato terá na lsita de favoritos, na visão do usuário.
     *
     */
    public String cadastraFavorito(int posContato, int posFav) {
        int posContReal = posContato -1;
        int posFavReal = posFav -1;
        if ((!(posFav <= 10 && posFav >= 1) || !(posContato <= 100 && posContato >= 1)) || contatos[posContReal] == null) {
            return "POSIÇÃO INVÁLIDA";
        }
        if (ehFavorito(posContReal)) {
            return "CONTATO JÁ É FAVORITO";
        }
        if (favoritos[posFavReal] != null) {
            removeFavorito(posFavReal);
        }
        favoritos[posFavReal] = contatos[posContReal];
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

    public boolean ehFavorito(int posReal) {
        for (int i=0; i<10; i++) {
            if (favoritos[i] != null) {
                if (favoritos[i].equals(contatos[posReal])) {
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