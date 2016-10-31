package app.bean;

import app.bean.entity.StudentTest;

import java.util.ArrayList;

public class ShowAllTestResponse extends Response {
    private ArrayList<StudentTest> allTests;

    public ArrayList<StudentTest> getAllTests() {
        return allTests;
    }

    public void setAllTests(ArrayList<StudentTest> allTests) {
        this.allTests = allTests;
    }
}
