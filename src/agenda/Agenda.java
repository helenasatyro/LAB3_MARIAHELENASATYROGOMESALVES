package agenda;

import java.util.NoSuchElementException;

/**
 * Classe que mantém uma lista de contatos com posições. Pode ter 100 contatos, 10 podendo ser favoritos.
 *
 * @author Maria Helena Sátyro Gomes Alves
 */
public class Agenda {
    private static final int TAMANHO_AGENDA = 100;
    private static final int TAMANHO_FAVS = 10;
    private static final int TAMANHO_TAGS = 5;
    private final Contato[]  contatos;
    private final Contato[] favoritos;
    private String[] tags;

    /**
     * Cria uma agenda de tamanho 100, e uma lista de favoritos de tamanho 10.
     */
    public Agenda() {
        this.contatos = new Contato[TAMANHO_AGENDA];
        this.favoritos = new Contato[TAMANHO_FAVS];
        this.tags = new String[TAMANHO_TAGS];
    }


    /**
     * Acessa a representação completa em String de um contatato.
     * @param posicaoUser Posição do contato na agenda, na visão do usuário.
     * @return Dados do contato. "Posição inválida" se ele não existir se não há contato na posição.
     */
    public String getContatoString(int posicaoUser) {
        if (posicaoUser <= 100 && posicaoUser >= 1) {
            int posReal = posicaoUser -1;
            if (getContatos()[posReal] != null) {
                if (ehFavorito(posReal)) {
                    return "❤️ " + contatos[posReal].getContatoCompleto();
                } else {
                    return contatos[posReal].getContatoCompleto();
                }
            } else {
                return "POSIÇÃO INVÁLIDA!";
            }
        }
        return "POSIÇÃO INVÁLIDA!";
    }

    /**
     * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     * Não lança erros, apenas retorna uma string informando o motivo de não ter sido possível completar a ação.
     * @param posicaoUser Posição do contato, na visão do usuário.
     * @param nome Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone Telefone do contato em String.
     */
    public void cadastraContato(int posicaoUser, String nome, String sobrenome, String telefone) {
        int posReal = posicaoUser -1;
        if (!(posReal >= 0 && posReal <= 99)) {
            throw new ArrayIndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        Contato contatoCriado;
        contatoCriado = new Contato(nome, sobrenome, telefone);
        for (int i=0; i < 100; i++) {
            if (contatos[i] != null) {
                if (contatos[i].equals(contatoCriado)) {
                    throw new IllegalCallerException("CONTATO JÁ CADASTRADO");
                }
            }
        }
            this.contatos[posReal] = contatoCriado;
    }

    /**
     * Verifica se há contato na posição
     * @return O array de contatos.
     */
    public Contato[] getContatos() {
        return contatos.clone();
    }

    /**
     * Cadastra um contato na lista de favoritos em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     * @param posContatoUsr Posição do contato na lista de contatos, na visão do usuário.
     * @param posFavUsr Posição que o contato terá na lsita de favoritos, na visão do usuário.
     * @throws ArrayIndexOutOfBoundsException se a posição do contato ou favorito passadas não corresponderem ao tamanho dos seus respectivos arrays
     * @throws NullPointerException se a posição de contato passada não tem contato.
     * @throws IllegalCallerException quando o contato a ser favoritado já é favorito
     */
    public void cadastraFavorito(int posContatoUsr, int posFavUsr) {
        int posContReal = posContatoUsr -1;
        int posFavReal = posFavUsr -1;
        if (!(posFavUsr <= 10 && posFavUsr >= 1) || !(posContatoUsr <= 100 && posContatoUsr >= 1)) {
            throw new ArrayIndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        if (contatos[posContReal] == null) {
            throw new NullPointerException("POSIÇÃO INVÁLIDA");
        }
        if (ehFavorito(posContReal)) {
            throw new IllegalCallerException("CONTATO JÁ É FAVORITO");
        }
        if (favoritos[posFavReal] != null) {
            removeFavorito(posFavUsr);
        }
        favoritos[posFavReal] = contatos[posContReal];
    }
    /**
     * Remove um contato da lista de favoritos em uma posição.
     * @param posFavUsr Posição que o contato terá na lista de favoritos, na visão do usuário.
     * @throws ArrayIndexOutOfBoundsException se a posição não estiver entre 0 e 9 inclusive
     * @throws NoSuchElementException se não houver contato na posição especificada
     */
    public void removeFavorito(int posFavUsr) {
        int posFavReal = posFavUsr -1;
        if (!(posFavReal <= 9 && posFavReal >= 0)) {
            throw new ArrayIndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        if (favoritos[posFavReal] == null) {
            throw new NoSuchElementException("POSIÇÃO INVÁLIDA");
        }
        favoritos[posFavReal] = null;
    }

    /**
     * Informa se o contato é ou não favorito.
     * @param posContReal Posição que o contato ocupa na lista de contatos.
     * @return boolean true ou false.
     */
    public boolean ehFavorito(int posContReal) {
        for (int i=0; i<10; i++) {
            if (favoritos[i] != null) {
                if (favoritos[i].equals(contatos[posContReal])) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Formata o array de favoritos para impressão.
     * @return a string formatada representando os favoritos.
     */
    public String getFavoritos() {
        String retorno = "";
        for (int i=0; i < TAMANHO_FAVS; i++) {
            if (favoritos[i] != null) {
                retorno += (i +1) + " - " + favoritos[i] + "\n";
            }
        }
        return retorno;
    }
    /**
     * Formata o array de contatos para impressão.
     * @return a string formatada representando o array de contatos.
     */
    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                retorno +=  (i+1) + " - " + contatos[i] + "\n";
            }
        }
        return retorno;
    }

    /**
     * Muda o telefone de um contato.
     *
     * @param posUsr a posição do contato a ser muydado, na visão do usuário
     * @param fone em string, o telefone a ser definido
     * @throws NoSuchElementException se não existe contato na posição especificada
     * @throws ArrayIndexOutOfBoundsException se a posição especificada não corresponde com o tamanho do array de contatos.
     */
    public void mudaFone(int posUsr, String fone) {
        int posReal = posUsr -1;
        if (!(posReal <= 99 && posReal >= 0)) {
            throw new ArrayIndexOutOfBoundsException("POSIÇÃO INVÁLIDA");
        }
        if (contatos[posReal] == null) {
            throw new NullPointerException("POSIÇÃO INVÁLIDA");
        }
        contatos[posReal].setTelefone(fone);

    }

}