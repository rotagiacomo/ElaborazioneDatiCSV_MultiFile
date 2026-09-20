import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Funzioni {
    public void addRecord(File csv, Campo campo, String valore) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
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

    public int getID(File csv, Campo campo, String valore) throws IOException{ //Cerca se esiste gia' il record, in caso contrario lo crea. Restituisce l'ID
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
            valore = valore.trim().toLowerCase();
            int index = indiceCampo(bReader, campo); //consumo la riga campi
            String riga;
            int id = 0;
            while ((riga = bReader.readLine()) != null) {
                String[] records = riga.split(",");
                if(records[index].equals(valore)){
                    bReader.close();
                    return id;
                }
                id++;
            }
            try(FileWriter fWriter = new FileWriter(csv, true)){
                fWriter.write("\n" + id + "," + valore);
                fWriter.close();
                return id;
            }
        }
    }
    
    private int indiceCampo(BufferedReader bReader, Campo campo) throws IOException{
        String[] campi = bReader.readLine().split(",");
        for (int i = 0; i < campi.length; i++) {
            if (campi[i].equals(campo.toString())) {
                return i;
            }
        }
        throw new RuntimeException("Campo not found");
    }

    public boolean recordExists(HashMap<String, String> reocrd){
        for () {
            
        }
    }

    public void addStudente(String nome, String cognome, String pcto, String vacanza) throws IOException{
        if(//studente esiste){
            return;
        }
        //dare ID a studente 
        File fileStudendte = (Path.of("Studente.CSV")).toFile();
        String newStudente = "";

        File fileNome = (Path.of("Nome.CSV")).toFile();
        newStudente += getID(fileNome, Campo.NOME, nome) + ",";

        File fileCognome = (Path.of("Cognome.CSV")).toFile();
        newStudente += getID(fileCognome, Campo.COGNOME, cognome) + ",";

        File fileAzienda = (Path.of("Azienda.CSV")).toFile();
        newStudente += getID(fileAzienda, Campo.AZIENDA, pcto) + ",";

        File filePaese = (Path.of("Paese.CSV")).toFile();
        newStudente += getID(filePaese, Campo.PAESE, vacanza);
    }

    private enum Campo {
        NOME,
        COGNOME,
        AZIENDA,
        PAESE
    }
}
