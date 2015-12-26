package Test.Util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.yawlfoundation.yawl.elements.YSpecVersion;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceB_EnvironmentBasedClient;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by adam on 2015/12/25.
 */
public class TestInterfaceB {
    private String ibURL = "http://localhost:8080/yawl/ib";

    @Test
    public void testGetSpec() throws IOException, ParserConfigurationException, SAXException, DocumentException {
        YSpecVersion version = new YSpecVersion("2.1");
        YSpecificationID id = new YSpecificationID("UID_ba9f200f-c009-4d76-be6a-b341ff435259",version,"SaveHoke");
        InterfaceB_EnvironmentBasedClient ibClient =
                new InterfaceB_EnvironmentBasedClient(ibURL);
        String handler = ibClient.connect("admin","YAWL");
//        String spec = ibClient.getSpecification(id,handler);
//        handler = "af21717f-ee65-4716-ac99-b1f1f831fe34";

//        String itemSpec = ibClient.getTaskInformationStr(id,"Step1",handler);

        String rsSepc = ibClient.getResourcingSpecs(id,"Step1",handler);

//        System.out.println(spec);
        System.out.println("==========");
//        System.out.println(itemSpec);
        System.out.println("----------");
        System.out.println(rsSepc);

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new StringReader(rsSepc));

        if(rsSepc.contains("<failure>")){
            List<Node> nodes =  doc.selectNodes("/response/failure/reason");
            System.out.println(nodes.get(0).getText());
        }else{
            System.out.println("success");
            Element root = doc.getRootElement();
            Element offer = root.element("offer");
            Element distributionSet = offer.element("distributionSet");
            Element initialSet = distributionSet.element("initialSet");


            List<Element> roles = initialSet.content();
            for (Element e : roles){
                System.out.println(e.getText());
            }
        }

//        DocumentBuilderFactory factory = DocumentBuilderFactory
//                .newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder
//                .parse(new InputSource(new StringReader(rsSepc)));
//
//        Element root = doc.getDocumentElement();
//        NodeList books = root.getChildNodes();
//        if (books != null) {
//            for (int i = 0; i < books.getLength(); i++) {
//                Node book = books.item(i);
//                System.out.println("节点=" + book.getNodeName());
//                if (book.getNodeName().equals("offer")){
//                    NodeList offers = book.getChildNodes();
//                    if (offers != null){
//                        for (int j=0;j<offers.getLength();j++){
//                            Node offer = offers.item(j);
//                            System.out.println(offer.getNodeName());
//                        }
//                    }
//                }
////                System.out.println(book.getChildNodes().item(0).getNodeValue());
//            }
//
//        }
    }
}
