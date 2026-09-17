import java.io.*;

public class Funzioni {
    public void addRecord(File csv, String campo, String record) throws IOException{
        BufferedReader bReader = new BufferedReader(new FileReader(csv));
        campo = campo.trim().toUpperCase();
        record = record.trim().toLowerCase();
        int index = indiceCampo(bReader, campo); //consumo la riga campi
        String riga;
        boolean isNewRecord = true;
        int id = 0;
        while (!((riga = bReader.readLine()) == null)) {
            id++;
            String[] records = riga.split(",");
            if(records[index].equals(record)){
                isNewRecord = false;
                break;
            }
        }
        bReader.close();
        if (isNewRecord) {
            FileWriter fWriter = new FileWriter(csv, true);
            fWriter.write("\n" + id + "," + record.toLowerCase());
            fWriter.close();
        }
    }

    private int indiceCampo(BufferedReader bReader, String campo) throws IOException{
        String[] campi = bReader.readLine().split(",");
        for (int i = 0; i < campi.length; i++) {
            if (campi[i].equals(campo)) {
                return i;
            }
        }
        return -1;
    }
}
