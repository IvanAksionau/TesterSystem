package service;


import service.impl.TestAppServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private TestAppService testAppService = new TestAppServiceImpl();

    public static  ServiceFactory getInstance() {   ///synchronized ?
        return instance;
    }

    public TestAppService getTestAppService()
    {
        return testAppService;
    }


}
