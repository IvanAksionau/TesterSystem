package app.service.impl;

import XMLdata.XMLTestsData;
import app.bean.entity.StudentTest;
import app.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;



/**
 * Created by Ivan on 31.10.2016.
 */
public class TestAppServiceImplTest {
    private XMLTestsData XMLData;
    private TestAppServiceImpl appData;
    ArrayList<StudentTest> appTests;
    ArrayList<StudentTest> XMLTests;

    @Before
    public void setData() throws ServiceException {
        appData = new TestAppServiceImpl();
        XMLData = new XMLTestsData();
        XMLTests = XMLData.getAllTests();
    }


    @Test
    public void showAllTests() throws Exception {
        appTests = appData.showAllTests();
        Assert.assertArrayEquals(XMLTests.toArray(),appTests.toArray());
    }
    @Test
    public void passTest() throws Exception {
        StudentTest testApp = appData.passTest(1);
        StudentTest testXml = XMLData.getTest(1);
        Assert.assertEquals(testXml,testApp);
    }

}