package dados;

import Privates.Ensino;
import Privates.Professor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DadosProfessor {

    private String nomePasta = "banco";
    private String nomeArquivo = "professores.txt";

    private File pasta = new File(nomePasta);
    private File arquivo = new File(pasta, nomeArquivo);

    public DadosProfessor() {
        Arquivo();
    }

    private void Arquivo() {
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DadosProfessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void adicionarProf(Professor professor) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
            writer.write(professor.Dados());
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Professor> getProfessor() {
        ArrayList<Professor> professores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                professores.add(transformaProf(linha));
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return professores;
    }

    public void atualizarProf(Professor professorAtualizado) {
        String email = professorAtualizado.getEmail();

        try {
            ArrayList<Professor> professores = getProfessor();
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
            for (Professor professor : professores) {
                if (professor.getEmail().equals(email)) {
                    writer.write(professorAtualizado.Dados());
                } else {
                    writer.write(professor.Dados());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Professor transformaProf(String linha) {
        String[] partes = linha.split("\\|");
        Professor professor = new Professor(partes[0], partes[1], partes[2]);
        for (int i = 3; i < partes.length; i += 3) {
            Ensino ensino = new Ensino(partes[i], Double.parseDouble(partes[i + 1]), partes[i + 2]);
            professor.adicionarEnsino(ensino);
        }
        return professor;
    }

    public Professor pesquisar(String email, String senha) {
        ArrayList<Professor> professores = getProfessor();

        for (Professor professor : professores) {
            if (professor.getEmail().equalsIgnoreCase(email) && professor.getSenha().equals(senha)) {
                return professor;
            }
        }

        return null;

    }

    public ArrayList<Professor> buscarProfessor(String valorBusca) {
        ArrayList<Professor> professores = getProfessor();

        for (int i = professores.size() - 1; i >= 0; i--) {

            Professor professor = professores.get(i);
            ArrayList<Ensino> ens = professor.getEnsinos();

            for (int j = ens.size() - 1; j >= 0; j--) {
                Ensino ensino = ens.get(j);

                if (!containsIgnoreCase(ensino.getNome(), valorBusca)) {
                    ens.remove(j);
                }
            }

            if (ens.isEmpty()) {
                professores.remove(i);
            }
        }

        return professores;
    }
    
    public ArrayList<Professor> buscarProf(String valorBusca){
        ArrayList<Professor> listaProfessor = getProfessor();
        
        for(int i = listaProfessor.size()- 1; i >= 0 ; i--){
            Professor item = listaProfessor.get(i);
            
            if(!containsIgnoreCase(item.getNome(), valorBusca)){
                listaProfessor.remove(i);
                
            }
        }
        
        return listaProfessor;
    }

    public boolean containsIgnoreCase(String str, String busca) {
        if (str == null || busca == null) {
            return false;
        }

        return str.toLowerCase().contains(busca.toLowerCase());
    }
    
    public void limparArquivo() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DadosProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
