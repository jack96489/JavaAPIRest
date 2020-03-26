package javaapirest;

import XMLUtils.XMLUtils;
import XMLUtils.XMLValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class Film {
    private int codFilm;
    private String titolo;
    private int annoProduzione;
    private String nazionalita;
    private String regista;
    private String genere;
    private int durata;

    public Film() {
    }

    public Film(int codFilm, String titolo, int annoProduzione, String nazionalita, String regista, String genere, int durata) {
        this.codFilm = codFilm;
        this.titolo = titolo;
        this.annoProduzione = annoProduzione;
        this.nazionalita = nazionalita;
        this.regista = regista;
        this.genere = genere;
        this.durata = durata;
    }


    public void parseXml(String xml) throws ParserConfigurationException, IOException, SAXException {
        if (!XMLValidator.validate("xml/film.xsd", xml))
            throw new IllegalArgumentException("xml mast be a film element\n" + xml);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource inputSource = new InputSource(new StringReader(xml));
        final Document document = builder.parse(inputSource);
        final Element root = document.getDocumentElement();

        parseXml(root);
    }

    public void parseXml(Element root) {
        codFilm = Integer.parseInt(XMLUtils.getTextValue(root, "CodFilm"));
        titolo = XMLUtils.getTextValue(root, "Titolo");
        annoProduzione = Integer.parseInt(XMLUtils.getTextValue(root, "AnnoProduzione"));
        nazionalita = XMLUtils.getTextValue(root, "Nazionalita");
        regista = XMLUtils.getTextValue(root, "Regista");
        regista = XMLUtils.getTextValue(root, "Regista");
        genere = XMLUtils.getTextValue(root, "Genere");
        durata = Integer.parseInt(XMLUtils.getTextValue(root, "Durata"));
    }

    public static Film fromXml(String xml) throws ParserConfigurationException, IOException, SAXException {
        final Film c = new Film();
        c.parseXml(xml);
        return c;
    }

    public static Film fromXml(Element root) {
        final Film c = new Film();
        c.parseXml(root);
        return c;
    }


    @Override
    public String toString() {
        return "Titolo: " + titolo +
                "\n Regista: " + regista +
                "\n Genere: " + genere;
    }


    public String toXML() {
        return "<film>\n" +
                "\t<CodFilm>" + codFilm + "</CodFilm>\n" +
                "\t<Titolo>" + titolo + "</Titolo>\n" +
                "\t<AnnoProduzione>" + annoProduzione + "</AnnoProduzione>\n" +
                "\t<Nazionalita>" + nazionalita + "</Nazionalita>\n" +
                "\t<Regista>" + regista + "</Regista>\n" +
                "\t<Genere>" + genere + "</Genere>\n" +
                "\t<Durata>" + durata + "</Durata>\n" +
                "</film>";
    }

    public int getCodFilm() {
        return codFilm;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoProduzione() {
        return annoProduzione;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public String getRegista() {
        return regista;
    }

    public String getGenere() {
        return genere;
    }

    public int getDurata() {
        return durata;
    }

}
