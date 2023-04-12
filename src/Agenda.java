public class Agenda {
    private static final int TAMANHO_AGENDA = 100;
    private final Contato[]  contatos;

    /**
     * Cria uma agenda.
     */
    public Agenda() {
        this.contatos = new Contato[TAMANHO_AGENDA];
    }

    /**
     * Acessa a lista de contatos mantida.
     * @return O array de contatos.
     */
    public Contato[] getContatos() {
        return contatos.clone();
    }

    /**
     * Acessa os dados de um contato específico.
     * @param posicao Posição do contato na agenda.
     * @return Dados do contato. Null se não há contato na posição.
     */
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
    public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
        Contato contato = new Contato(nome, sobrenome, telefone);
        this.contatos[posicao] = contato;
    }

    public boolean temContato(int pos) {
        if (pos >= 0 && pos <= 99) {
            return contatos[pos] != null;
        }
        return false;
    }
}