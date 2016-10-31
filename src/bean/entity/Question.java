package bean.entity;

import java.util.ArrayList;

/**
 * Created by Ivan on 25.10.2016.
 */
public class Question {
    private String description;
    private String correctAnswer;
    private ArrayList<String> variants = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<String> variants) {
        this.variants = variants;
    }
}
