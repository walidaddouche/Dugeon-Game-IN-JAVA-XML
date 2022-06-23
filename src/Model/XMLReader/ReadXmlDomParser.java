package Model.XMLReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;
import java.util.*;


public class ReadXmlDomParser {




    public static HashMap<Integer, Integer> getMapDataID(String path) {
        List<String> arrayList = Arrays.stream(Objects.requireNonNull(getMapData(path))).toList();
        HashSet<String> strings = new HashSet<>(arrayList);
        int i = 0;
        HashMap<Integer, Integer> hashSet = new HashMap<>();
        for (String string : strings) {
            hashSet.put(Integer.parseInt(string), i);
            i++;
        }
        return hashSet;
    }

    public static String[] getMapData(String path) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            System.out.println(path);
            Document document = db.parse(new File(String.valueOf(Path.of(path))));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("map");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            String target = eElement.getElementsByTagName("data").item(0).getTextContent().replaceAll("\\s", "");
            return target.split(",");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

