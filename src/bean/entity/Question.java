package bean.entity;

/**
 * Created by Ivan on 25.10.2016.
 */
public class Question {
    private String description;
    private String correctAnswer;
    private String[] variants = new String[3];

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

    public String[] getVariants() {
        return variants;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }
}
