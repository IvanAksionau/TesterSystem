package XMLdata;

import bean.entity.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ivan on 18.10.2016.
 */
public class XMLTestsData {
    public static ArrayList<Test> getAllTests(){
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(true);
        SaxHandler saxHandler = new SaxHandler();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(new File("src/XMLdata/menu.data"),saxHandler);
        } catch (ParserConfigurationException| SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saxHandler.getTestList();
    }
}

