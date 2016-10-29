package bean;

import bean.entity.Test;

import java.util.ArrayList;

public class ShowAllTestResponse extends Response {
    private ArrayList<Test> allTests;

    public ArrayList<Test> getAllTests() {
        return allTests;
    }

    public void setAllTests(ArrayList<Test> allTests) {
        this.allTests = allTests;
    }
}
