package nl.bongers.testdome;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement a function folderNames, which accepts a string containing an XML file that specifies folder structure and returns
 * all folder names that start with startingLetter. The XML format is given in the main below.
 *
 * For example, for the letter 'u' and the given XML file the function should return a collection
 * with items "uninstall information" and "users" (in any order).
 */
public class Folders {

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        final Folders folders = new Folders();
        Collection<String> names = folders.folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }

    public Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        final Document document = createDocumentFromXML(xml);
        final List<String> folderNames = parseChildNodes(document.getChildNodes());
        return folderNames
                .stream()
                .filter(f -> f.startsWith(Character.toString(startingLetter)))
                .collect(Collectors.toList());
    }

    private Document createDocumentFromXML(String xml) throws Exception {
        final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        final InputSource inputSource = new InputSource(new StringReader(xml));
        return builder.parse(inputSource);
    }

    private List<String> parseChildNodes(NodeList nodeList) {
        final List<String> items = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            final Node node = nodeList.item(i);
            if (node.hasChildNodes()) {
                items.addAll(parseChildNodes(node.getChildNodes()));
            }
            items.addAll(parseNodeAttributes(node.getAttributes()));
        }

        return items;
    }

    private List<String> parseNodeAttributes(NamedNodeMap attributes) {
        final List<String> items = new ArrayList<>(attributes.getLength());
        for (int i = 0; i < attributes.getLength(); i++) {
            final String value = attributes.item(i).getNodeValue();
            items.add(value);
        }
        return items;
    }
}