package view.menu;

import bean.PassTestRequest;
import bean.PassTestResponse;
import bean.ShowAllTestRequest;
import bean.ShowAllTestResponse;
import bean.entity.Question;
import bean.entity.Test;
import controller.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static jdk.nashorn.internal.runtime.JSType.isNumber;
import static jdk.nashorn.internal.runtime.JSType.isString;

/**
 * Created by Ivan on 26.10.2016.
 */
public class UserMenu {
    private static final Controller controller = new Controller();
    private static final Scanner scanner = new Scanner(System.in);
    private static String userMenu = "Enter :" + "\n"
            + "1 - Pass test" + "\n"
            + "2 - Show all tests";

    public static void getMenu() {
        while (true) {
            System.out.println("Enter \"menu\" in order to get the CommandMenu, or \"logout\" to logout");
            String Command = scanner.nextLine();
            if (Command.equals("logout")) {
                System.out.println("System is logout!");
                return;
            }
            switch (Command) {
                case "menu":
                    System.out.println(userMenu);
                    break;
                case "1"://Pass test
                    passTest();
                    break;
                case "2"://Show all tests
                    AdminMenu.showTestList(controller);
            }
        }
    }

    static void passTest() {
        PassTestRequest request1 = new PassTestRequest();
        request1.setCommandName("PASS_TEST");
        System.out.println("Enter test № :");
        int testID = getVariantNumber();
        request1.setTestId(testID);
        PassTestResponse response1 = (PassTestResponse) controller.doRequest(request1);
        if (response1.isErrorStatus() == false) {
            System.out.println(response1.getResultMessage());
            Test test = response1.getTest();
            if (test.getTestName() != null) {
                int correctAnswer = 0;
                ArrayList<Question> questions = test.getQuestions();
                for (Question question : questions) {
                    System.out.println(question.getDescription());
                    ArrayList variants = question.getVariants();
                    //меняем индекс правильного ответа
                    Collections.shuffle(variants);
                    for (int i = 0; i < variants.size(); i++) {
                        System.out.println("№ " + (i + 1) + " - " + variants.get(i));
                    }
                    System.out.println("Enter correct question №");
                    int numberUserVariant = getVariantNumber();
                    if (variants.get(numberUserVariant - 1).equals(question.getCorrectAnswer())) {
                        correctAnswer++;
                    }
                }
                System.out.println("Count of correct answers is " + correctAnswer);
            }
        } else {
            System.out.println(response1.getErrorMessage());
        }
    }

    public static int getVariantNumber() {
        String variantNumber = "";
        int number = 0;
        try {
            variantNumber = new Scanner(System.in).nextLine();
            number = Integer.parseInt(variantNumber);
        } catch (NumberFormatException e) {
            while (variantNumber.isEmpty() || !isNumber(variantNumber)) {
                //проверяем регуляркой, являеться ли положительным числом числом
                if (variantNumber.matches("[+]?\\d+")) {
                    return Integer.parseInt(variantNumber);
                }
                System.out.println("Data can not be empty or not is number!");
                variantNumber = new Scanner(System.in).nextLine();
            }
        }
        return number;
    }
}
