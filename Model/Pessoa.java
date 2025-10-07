public class Pessoa {
    private String nome;
    private String cpf;
    private Integer idade;
    private Genero genero;
    private String telefone;

    public Pessoa(String nome, String cpf, Integer idade, Genero genero, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.genero = genero;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf;
    }
}