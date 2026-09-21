public class RecordNotFoundException extends Exception {
    private GestoreDatabase.Campo campoRecordNotFound;

    public RecordNotFoundException(String messaggio, GestoreDatabase.Campo campoRecordNotFound) {
        super(messaggio);
        this.campoRecordNotFound = campoRecordNotFound;
    }

    public GestoreDatabase.Campo getCampo(){
        return campoRecordNotFound;
    }
}