package XMLdata;

import bean.entity.Question;
import bean.entity.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


/**
 * Created by Ivan on 18.10.2016.
 */
public class SaxHandler extends DefaultHandler {
    private String elementName;
    private Test test;
    private ArrayList<Test> testList = new ArrayList<>();
    private Question question;
    private ArrayList<Question> questionList;
    private ArrayList<String> variants;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("end parsing...");
    }

    @Override
    public void startElement(String nameSpace, String localName, String qName, Attributes attr) throws SAXException {
        elementName = qName;
        switch (elementName) {
            case "Test":
                test = new Test();
                test.setTestId(Integer.parseInt(attr.getValue(0)));
                questionList = new ArrayList<>();
                break;
            case "question":
                question = new Question();
                break;
            case "variants":
                variants = new ArrayList();
                break;
        }
    }

    @Override
    public void endElement(String nameSpace, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Test":
                test.setQuestions(questionList);
                testList.add(test);
                break;
            case "question":
                questionList.add(question);
                break;
            case "variants":
                question.setVariants(variants);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int end) {
        switch (elementName) {
            case "testName":
                test.setTestName(new String(ch, start, end));
                break;
            case "subject":
                test.setSubject(new String(ch, start, end));
                break;
            case "description":
                question.setDescription(new String(ch, start, end));
                break;
            case "correctAnswer":
                question.setCorrectAnswer(new String(ch, start, end));
                break;
            case "variant":
                variants.add(new String(ch, start, end));
                break;
        }
    }

    public ArrayList<Test> getTestList() {
        return testList;
    }

}
