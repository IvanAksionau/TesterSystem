package app.bean;

import app.bean.entity.StudentTest;

/**
 * Created by Ivan on 26.10.2016.
 */
public class PassTestResponse extends Response {
    private StudentTest test;

    public StudentTest getTest() {
        return test;
    }

    public void setTest(StudentTest test) {
        this.test = test;
    }
}
