package app.bean.entity;

import java.util.ArrayList;

/**
 * Created by Ivan on 25.10.2016.
 */
public class StudentTest {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentTest that = (StudentTest) o;

        if (testId != that.testId) return false;
        if (testName != null ? !testName.equals(that.testName) : that.testName != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        return questions != null ? questions.equals(that.questions) : that.questions == null;

    }

    @Override
    public int hashCode() {
        int result = testName != null ? testName.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + testId;
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }
}
