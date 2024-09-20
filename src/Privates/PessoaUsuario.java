package Privates;

public class PessoaUsuario{
    private String nome;
    String email;    
    String senha;
    
    public PessoaUsuario ( String nome, String email,String senha){
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }
    public PessoaUsuario (){
	this.email = "";
	this.nome = "";
        this.senha="";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }  

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Lista{" + "nome=" + nome + ", email=" + email + ", senha=" + senha + '}';
    }
}


    
