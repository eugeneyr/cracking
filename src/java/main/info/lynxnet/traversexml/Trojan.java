package info.lynxnet.traversexml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.*;

public class Trojan {
    public static int countCompromised(String xml, String infectedFileId) {
        if (infectedFileId == null || xml == null) {
            return 0;
        }
        int result = 0;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));
            Set<Set<String>> infectedSets = new HashSet<>();
            Set<String> currentSet = null;
            Stack<Set<String>> setsByFolder = null;

            int snapshots = 0;

            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elemName = reader.getLocalName();
                        switch (elemName) {
                            case "snapshot":
                                snapshots++;
                                currentSet = new HashSet<>();
                                setsByFolder = new Stack<>();
                                break;
                            case "folder":
                                if (currentSet != null) {
                                    setsByFolder.push(currentSet);
                                }
                                currentSet = new HashSet<>();
                                break;
                            case "file":
                                String fileId = reader.getAttributeValue(null, "fileId");
                                if (fileId != null) {
                                    if (infectedFileId.equals(fileId)) {
                                        infectedSets.add(currentSet);
                                    } else {
                                        currentSet.add(fileId);
                                    }
                                }
                                break;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "snapshot":
                                setsByFolder = null;
                                currentSet = null;
                                break;
                            case "folder":
                                currentSet = setsByFolder.empty() ? null : setsByFolder.pop();
                                break;
                        }
                        break;
                }
            }
            System.out.println("Total snapshots: " + snapshots);
            Set<String> superset = new HashSet<>();
            for (Set<String> set : infectedSets) {
                superset.addAll(set);
            }
            result = superset.size();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<root>" +
                        "   <snapshot>" +
                        "      <folder>" +
                        "         <file fileId=\"1\"/>" +
                        "         <file fileId=\"2\"/>" +
                        "         <folder>" +
                        "            <file fileId=\"3\"/>" +
                        "            <file fileId=\"4\"/>" +
                        "         </folder>" +
                        "      </folder>" +
                        "   </snapshot>" +
                        "   <snapshot>" +
                        "      <file fileId=\"1\"/>" +
                        "      <file fileId=\"3\"/>" +
                        "      <folder>" +
                        "         <file fileId=\"2\"/>" +
                        "         <file fileId=\"4\"/>" +
                        "         <folder>" +
                        "            <file fileId=\"3\"/>" +
                        "            <file fileId=\"5\"/>" +
                        "         </folder>" +
                        "      </folder>" +
                        "   </snapshot>" +
                        "</root>";

        System.out.println(Trojan.countCompromised(xml, "3"));
    }
}