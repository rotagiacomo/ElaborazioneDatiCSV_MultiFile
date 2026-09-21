import java.io.*;
import java.nio.file.Path;

public class GestoreDatabase {

    File fileNome = (Path.of("Nome.CSV")).toFile();
    File fileCognome = (Path.of("Cognome.CSV")).toFile();
    File fileAzienda = (Path.of("Azienda.CSV")).toFile();
    File filePaese = (Path.of("Paese.CSV")).toFile();
    File fileStudendte = (Path.of("Studente.CSV")).toFile();

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

    public int getID(File csv, Campo campo, String valore) throws IOException, RecordNotFoundException{ 
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
        }
        throw new RecordNotFoundException("Record not found", campo);
    }

    public String getRecordByID(File csv, Campo campo, int id) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
            int indexCampo = indiceCampo(bReader.readLine().split(","), campo);
            String riga;
            while ((riga = bReader.readLine()) != null) {
                String[] rigaSplit = riga.split(",");
                if(Integer.parseInt(rigaSplit[0]) == id){
                    return rigaSplit[indexCampo];
                }
            }
        }
        throw new RuntimeException("ID not found");
    }
    
    public String[] getRecordByID(File csv, int id) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(csv))){
            String riga;
            while ((riga = bReader.readLine()) != null) {
                String[] rigaSplit = riga.split(",");
                if(Integer.parseInt(rigaSplit[0]) == id){
                    return rigaSplit;
                }
            }
        }
        throw new RuntimeException("ID not found");
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
        String newStudente = "";
        try{
            newStudente += getID(fileNome, Campo.NOME, nome) + ",";
            newStudente += getID(fileCognome, Campo.COGNOME, cognome) + ",";
            newStudente += getID(fileAzienda, Campo.AZIENDA, pcto) + ",";
            newStudente += getID(filePaese, Campo.PAESE, vacanza);
        }catch(RecordNotFoundException ex){
            if(ex.getCampo().equals(Campo.NOME)){
                addRecord(fileNome,Campo.NOME, nome);
            }
            if(ex.getCampo().equals(Campo.COGNOME)){
                addRecord(fileCognome,Campo.COGNOME, cognome);
            }
            if(ex.getCampo().equals(Campo.AZIENDA)){
                addRecord(fileAzienda,Campo.AZIENDA, pcto);
            }
            if(ex.getCampo().equals(Campo.PAESE)){
                addRecord(filePaese,Campo.PAESE, vacanza);
            }
        }


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

    public String getStudente(int id) throws IOException{
        try(BufferedReader bReader = new BufferedReader(new FileReader(fileStudendte))){
            String riga;
            boolean isRigaCampi = true;
            while((riga = bReader.readLine()) != null){
                String[] rigaSplit = riga.split(",");
                if(!isRigaCampi && Integer.parseInt(rigaSplit[0]) == id){
                    String studente = "";
                    studente += getRecordByID(fileNome, Campo.NOME, Integer.parseInt(rigaSplit[1])) + ",";
                    studente += getRecordByID(fileCognome, Campo.COGNOME, Integer.parseInt(rigaSplit[2])) + ",";
                    studente += getRecordByID(fileAzienda, Campo.AZIENDA, Integer.parseInt(rigaSplit[3])) + ",";
                    studente += getRecordByID(filePaese, Campo.PAESE, Integer.parseInt(rigaSplit[4]));
                    return studente;
                }
                isRigaCampi = false;
            }
        }
        throw new RuntimeException("ID not found");
    }

    enum Campo {
        NOME,
        COGNOME,
        AZIENDA,
        PAESE
    }
}
