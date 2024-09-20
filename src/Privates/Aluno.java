package Privates;

public class Aluno extends PessoaUsuario {

    public Aluno() {
        super();
    }

    public Aluno(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public String Dados() {
        return String.format("%s|%s|%s", getNome(), getEmail(), getSenha());
    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("nome: ").append(this.getNome()).append("\n");
        sb.append("email: ").append(this.getEmail()).append("\n");
        sb.append("senha: ").append(this.getSenha());
        
        return sb.toString();
    }

}
