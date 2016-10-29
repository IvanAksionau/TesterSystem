package bean;

import bean.entity.Test;

/**
 * Created by Ivan on 26.10.2016.
 */
public class PassTestResponse extends Response {
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
