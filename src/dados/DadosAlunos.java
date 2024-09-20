package dados;


import Privates.Aluno;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DadosAlunos {

    private String nomePasta = "banco";
    private String nomeArquivo = "alunos.txt";

    private File pasta = new File(nomePasta);
    private File arquivo = new File(pasta, nomeArquivo);

    public DadosAlunos() {
        Criar();
    }

    private void Criar() {
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                
            }
        }
    }

    public void adicionar(Aluno aluno) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
            writer.write(aluno.Dados());
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Aluno> getAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                alunos.add(transformar(linha));
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alunos;
    }

    private Aluno transformar(String linha) {
        String[] partes = linha.split("\\|");
        Aluno aluno = new Aluno(partes[0], partes[1], partes[2]);
        
        return aluno;
    }

    public Aluno pesquisar(String email, String senha) {
       ArrayList<Aluno> alunos = getAlunos();
       
       for(Aluno aluno : alunos) {
           if(aluno.getEmail().equalsIgnoreCase(email) && aluno.getSenha().equals(senha)) {
               return aluno;
           }
       } 
       
       return null;
    }
    
    public void limpar() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
