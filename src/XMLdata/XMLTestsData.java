package XMLdata;

import app.bean.entity.StudentTest;
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

    private ArrayList<StudentTest> tests;

    public XMLTestsData() {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(true);
        SaxHandler saxHandler = new SaxHandler();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(new File("src/XMLdata/data_file/data.xml"),saxHandler);
        } catch (ParserConfigurationException| SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tests = saxHandler.getTestList();
    }

    public  ArrayList<StudentTest> getAllTests(){
        return tests;
    }

    public  StudentTest getTest(int testID){
        StudentTest getedTest = null;
        for (StudentTest test :tests){
            if (testID == test.getTestId()){
                getedTest = test;
                return getedTest;
            }
        }
        return getedTest;
    }

}

