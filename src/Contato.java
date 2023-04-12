public class Contato {
    private String nome;
    private String sobrenome;
    private String telefone;


    Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
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
        return nome + " " + sobrenome + "\n" + telefone;
    }
}