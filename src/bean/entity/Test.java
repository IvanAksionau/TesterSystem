package bean.entity;

import java.util.ArrayList;

/**
 * Created by Ivan on 25.10.2016.
 */
public class Test {
    private String testName;
    private String subject;
    private int testId;
    private ArrayList<Question> questions = new ArrayList<>();

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
