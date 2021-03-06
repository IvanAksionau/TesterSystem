package app.view.menu;

import app.bean.AddNewTestRequest;
import app.bean.Response;
import app.bean.ShowAllTestRequest;
import app.bean.ShowAllTestResponse;
import app.bean.entity.Question;
import app.bean.entity.StudentTest;
import app.controller.Controller;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ivan on 26.10.2016.
 */
public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Controller controller = new Controller();
    private static String adminMenu = "Enter :" + "\n"
            + "1 - Add new test" + "\n"
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
                    System.out.println(adminMenu);
                    break;
                case "1"://AddNewTest
                    AddTest();
                    break;
                case "2"://Show all tests
                    showTestList(controller);
                    break;
            }
        }
    }

    public static void AddTest(){
        AddNewTestRequest request2 = new AddNewTestRequest();
        request2.setCommandName("ADD_NEW_TEST");
        System.out.println("Enter test name :");
        request2.setTestName(getNotEmptyData());
        System.out.println("Enter subject type of test :");
        request2.setSubjectType(getNotEmptyData());
        //используем метод формирующий список вопросов
        ArrayList<Question> questionList = setTestQuestions();
        request2.setQuestions(questionList);
        Response response2 = controller.doRequest(request2);
        if (response2.isErrorStatus() == false) {
            System.out.println(response2.getResultMessage());
        } else {
            System.out.println(response2.getErrorMessage());
        }
    }

    static ArrayList<Question> setTestQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        while (true) {
            System.out.println("Add new question for test ? y - yes, n - no ");
            String inputData = getNotEmptyData();
            if (inputData.equals("n")) {
                break;
            }
            if (inputData.equals("y")) {
                Question question = new Question();
                ArrayList<String> answerVariants = new ArrayList<>();
                System.out.println("Enter your question :");
                question.setDescription(getNotEmptyData());
                System.out.println("Enter correct answer :");
                String correctAnswer = getNotEmptyData();
                question.setCorrectAnswer(correctAnswer);
                System.out.println("Enter first incorrect answers :");
                answerVariants.add(getNotEmptyData());
                System.out.println("Enter second incorrect answers :");
                answerVariants.add(getNotEmptyData());
                answerVariants.add(correctAnswer);
                question.setVariants(answerVariants);
                questionList.add(question);
            }
        }
        return questionList;
    }

    public static void showTestList(Controller controller) {
        ShowAllTestRequest request3 = new ShowAllTestRequest();
        request3.setCommandName("SHOW_All_TESTS");
        ShowAllTestResponse response3 = (ShowAllTestResponse) controller.doRequest(request3);
        if (response3.isErrorStatus() == false) {
            System.out.println(response3.getResultMessage());
            ArrayList<StudentTest> tests = response3.getAllTests();
            for (StudentTest test : tests) {
                System.out.println("№ - " + test.getTestId() + " StudentTest name - "
                        + test.getTestName() + " Subject type - " + test.getSubject());
            }
        } else {
            System.out.println(response3.getErrorMessage());
        }

    }

    public static String getNotEmptyData() {
        String notEmptyData = "";
        while (notEmptyData.isEmpty()) {
            notEmptyData = new Scanner(System.in).nextLine();
            if (notEmptyData.isEmpty()) {
                System.out.println("Data can not be empty!");
            }
        }
        return notEmptyData;
    }

}
