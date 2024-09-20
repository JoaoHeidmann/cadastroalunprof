package Privates;

import java.util.ArrayList;

public class Professor extends PessoaUsuario {

    private ArrayList<Ensino> ensinos;

    public Professor() {
        ensinos = new ArrayList<>();
    }

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
        ensinos = new ArrayList<>();
    }
    
    public Professor(String nome, String email, String senha, ArrayList<Ensino> ensinos) {
        super(nome, email, senha);
        this.ensinos = ensinos;
    }

    public ArrayList<Ensino> getEnsinos() {
        return ensinos;
    }

    public void setEnsinos(ArrayList<Ensino> ensinos) {
        this.ensinos = ensinos;
    }

    public void adicionarEnsino(Ensino ens) {
        ensinos.add(ens);
    }

    public String Dados() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("%s|%s|%s", getNome(), getEmail(), getSenha()));
        
        for (Ensino ensino : getEnsinos()) {
            sb.append("|");
            sb.append(ensino.Dados());
        }
        return sb.toString();
    }

}
