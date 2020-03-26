package javaapirest;

import XMLUtils.XMLUtils;
import XMLUtils.XMLValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Films extends ArrayList<Film> {

    public void parseXml(String xml) throws ParserConfigurationException, IOException, SAXException {
        if (!XMLValidator.validate("xml/films.xsd", xml))
            throw new IllegalArgumentException("xml mast be a films element\n" + xml);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource inputSource = new InputSource(new StringReader(xml));
        final Document document = builder.parse(inputSource);
        final Element root = document.getDocumentElement();

        parseXml(root);
    }

    public void parseXml(Element root) {
        if (!root.getTagName().equals("films"))
            throw new IllegalArgumentException("Error in xml");

        List<Node> films = XMLUtils.wrapNodeList(root.getChildNodes());
        films.removeIf(c -> c.getNodeType() != Node.ELEMENT_NODE);
        for (Node n : films)
            add(Film.fromXml((Element) n));
    }

    public static Films fromXml(String xml) throws IOException, SAXException, ParserConfigurationException {
        Films c = new Films();
        c.parseXml(xml);
        return c;
    }

    public static Films fromXml(Element xml) throws IOException, SAXException, ParserConfigurationException {
        Films c = new Films();
        c.parseXml(xml);
        return c;
    }



}
