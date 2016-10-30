package view;

import bean.UserLoggingRequest;
import bean.UserLoggingResponse;
import bean.UserRegistrationRequest;
import bean.UserRegistrationResponse;
import controller.Controller;
import dao.impl.pool.ConnectionPool;
import view.menu.AdminMenu;
import view.menu.UserMenu;

import java.io.IOException;
import java.util.Scanner;

import static view.menu.AdminMenu.getNotEmptyData;

public class View {
    private static final Controller controller = new Controller();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.println("Enter :" + "\n"
                    + "r - for registration" + "\n"
                    + "l - for logging" + "\n"
                    + "e - for exit");
            String appCommand = scanner.nextLine();
            if (appCommand.equals("e")) {
                ConnectionPool.getInstance().closePool();
                System.out.println("Program finished!");
                break;
            }
            switch (appCommand) {
                case "l":
                    UserLoggingRequest request1 = new UserLoggingRequest();
                    int currentUserID;
                    request1.setCommandName("LOGIN_USER");
                    System.out.println("Enter your login name");
                    String loginName = getNotEmptyData();
                    request1.setLogin(loginName);
                    System.out.println("Enter your password");
                    String pass = getNotEmptyData();
                    request1.setPassword(pass);
                    UserLoggingResponse response = (UserLoggingResponse) controller.doRequest(request1);
                    if (response.isErrorStatus() == false) {
                        currentUserID = response.getUserId();
                        System.out.println(response.getResultMessage());
                        //проверяем права доступа и запускаем соотв. menu
                        checkPermissions(currentUserID);
                    } else {
                        System.out.println(response.getErrorMessage());
                        break;
                    }
                    break;
                case "r":
                    UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
                    userRegistrationRequest.setCommandName("REGISTERED_USER");
                    System.out.println("Enter your login name");
                    String newUserName = getNotEmptyData();
                    userRegistrationRequest.setLogin(newUserName);
                    System.out.println("Enter your password");
                    String newUserPass = getNotEmptyData();
                    userRegistrationRequest.setPassword(newUserPass);
                    UserRegistrationResponse userRegistrationResponse
                            = (UserRegistrationResponse) controller.doRequest(userRegistrationRequest);
                    if (userRegistrationResponse.isErrorStatus() == false) {
                        System.out.println(userRegistrationResponse.getResultMessage());
                    } else {
                        System.out.println(userRegistrationResponse.getErrorMessage());
                    }
                    break;
            }
        }
    }

    static void checkPermissions(int userId) {
        if (userId == 1) {
            AdminMenu.getMenu();
        }
        if (userId > 1) {
            UserMenu.getMenu();
        }
    }
}
