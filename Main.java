import java.io.IOException;

public class Main {
    public static void main() throws IOException{
        GestoreDatabase gestoreDatabase = new GestoreDatabase();
        String studente = gestoreDatabase.getStudente(1);
        System.out.println(studente);
    }
}