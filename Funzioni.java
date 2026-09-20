import java.io.*;
import java.nio.file.Path;

public class Funzioni {

    public void addRecord(File csv, Campo campo, String valore) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
            valore = valore.trim().toLowerCase();
            String[] riga = bReader.readLine().split(",");
             int index = indiceCampo(riga, campo);
            boolean isNewRecord = true;
            int id = 0;
            while ((riga = bReader.readLine().split(",")) != null) {
                id++;
                if(riga[index].equals(valore)){
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
            String[] rigaSplit = bReader.readLine().split(",");
            String riga;
            int index = indiceCampo(rigaSplit, campo);
            int id = 0;
            while ((riga = bReader.readLine()) != null) {
                rigaSplit = riga.split(",");
                if(rigaSplit[index].equals(valore)){
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
    
    private int indiceCampo(String[] campi, Campo campo) throws IOException{
        for (int i = 0; i < campi.length; i++) {
            if (campi[i].equals(campo.toString())) {
                return i;
            }
        }
        throw new RuntimeException("Campo not found");
    }

    public void addStudente(String nome, String cognome, String pcto, String vacanza) throws IOException{
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

        try(FileWriter fWriter = new FileWriter(fileStudendte, true)){
            int studentID = -1;
            try(BufferedReader bReader = new BufferedReader(new FileReader(fileStudendte))){
                String riga;
                while ((riga = bReader.readLine()) != null) {
                    String[] rigaSplit = riga.split(",");
                    for(int i = 0; i<rigaSplit.length; i++){
                        if(/**FUNZIONE PER VERIFICARE VERIFICARE CAMPO PER CAMPO SE SONO UGUALI, RICHIAMARE CSV DA ID PER OGNI CAMPO && **/i !=0){
                            System.out.println((rigaSplit[i] + " " + newStudente.split(",")[i]));
                            break;
                        }
                        if(i == rigaSplit.length-1){
                            return ; //lo studente con queste caratteristiche esiste gia'
                        }
                    }
                    studentID++;
                }
                bReader.close();
            }
            fWriter.write("\n" + studentID + "," + newStudente);
            fWriter.close();
        }
    }

    private enum Campo {
        NOME,
        COGNOME,
        AZIENDA,
        PAESE
    }
}
