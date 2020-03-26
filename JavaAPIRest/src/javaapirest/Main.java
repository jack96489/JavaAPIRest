package javaapirest;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        final WSConsumer webService = new WSConsumer("http://localhost/cinema/service.php");
        System.out.println(webService.get("localhost"));
        final String result = webService.getResult();

        try {
            Films films = Films.fromXml(result);
            for (Film f : films)
                System.out.println(f.getCodFilm()+". "+f.getTitolo());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        System.out.print("selezione un film: ");
        final int codFilm = sc.nextInt();
        sc.nextLine();


        try {
            webService.addParameter("idFilm", Integer.toString(codFilm));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(webService.get("localhost"));

        final String film = webService.getResult();
        try {
            Film f = Film.fromXml(film);
            System.out.println(f);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }


    }
}
