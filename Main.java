import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main() throws IOException{
        Funzioni funzioni = new Funzioni();
        //9 nomi
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Marco");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Luca");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Andrea");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Matteo");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Alessandro");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Francesco");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Davide");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Simone");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Giovanni");
        funzioni.addRecord((Path.of("Nome.CSV")).toFile(), "Nome", "Lisa");

        //9 cognomi
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Rossi");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Bianchi");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Esposito");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Romano");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Colombo");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Ricci");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Marino");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Greco");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Bruno");
        funzioni.addRecord((Path.of("Cognome.CSV")).toFile(), "Cognome", "Gallo");

        // 5 aziende
        funzioni.addRecord((Path.of("Azienda.CSV")).toFile(), "Azienda", "Apple");
        funzioni.addRecord((Path.of("Azienda.CSV")).toFile(), "Azienda", "Microsoft");
        funzioni.addRecord((Path.of("Azienda.CSV")).toFile(), "Azienda", "Google");
        funzioni.addRecord((Path.of("Azienda.CSV")).toFile(), "Azienda", "Amazon");
        funzioni.addRecord((Path.of("Azienda.CSV")).toFile(), "Azienda", "Samsung");

        //7 paesi
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Italia");
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Francia");
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Germania");
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Spagna");
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Portogallo");
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Inghilterra");
        funzioni.addRecord((Path.of("Paese.CSV")).toFile(), "Paese", "Svizzera");

    }
}