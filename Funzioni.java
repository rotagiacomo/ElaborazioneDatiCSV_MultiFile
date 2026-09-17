import java.io.*;
import java.nio.file.Path;

public class Funzioni {
    public void addRecord(File csv, String campo, String valore) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
            campo = campo.trim().toUpperCase();
            valore = valore.trim().toLowerCase();
            int index = indiceCampo(bReader, campo); //consumo la riga campi
            String riga;
            boolean isNewRecord = true;
            int id = 0;
            while ((riga = bReader.readLine()) != null) {
                id++;
                String[] records = riga.split(",");
                if(records[index].equals(valore)){
                    isNewRecord = false;
                    break;
                }
            }
            bReader.close();
            if (isNewRecord) {
                try(FileWriter fWriter = new FileWriter(csv, true)){
                    fWriter.write("\n" + id + "," + valore);
                    fWriter.close();
                }
            }
        }
    }

    public boolean recordExists(File csv, String campo, String valore) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
            campo = campo.trim().toUpperCase();
            valore = valore.trim().toLowerCase();
            int index = indiceCampo(bReader, campo); //consumo la riga campi
            String riga;
            while ((riga = bReader.readLine()) != null) {
                String[] records = riga.split(",");
                if(records[index].equals(valore)){
                    return true;
                }
            }
            return false;
        }
        //modificare affinche si controllino più campi e più valori
    }
    
    private int indiceCampo(BufferedReader bReader, String campo) throws IOException{
        String[] campi = bReader.readLine().split(",");
        for (int i = 0; i < campi.length; i++) {
            if (campi[i].equals(campo)) {
                return i;
            }
        }
        throw new RuntimeException("Campo not found");
    }

    public void addStudente(String nome, String cognome, String PCTO, String Vacanze) throws IOException{
        File file = (Path.of("Nome.CSV")).toFile();

    }

    private int getID(File csv, String campo, String valore){
        if(recordExists(csv, campo, valore)){
            //trovare indice record e ritornare il relativo ID
        }
        //creare un nuovo record e ritornare il relativo ID
    }
}
