package model;

import org.jetbrains.annotations.NotNull;

public class Feedback {
    private String plus;
    private String minus;
    private String text;
    private String author;

    public Feedback(@NotNull String plus, @NotNull String minus, @NotNull String text, @NotNull String author) {
        this.plus = plus;
        this.minus = minus;
        this.text = text;
        this.author = author;
    }

    public @NotNull String getPlus() {
        return plus;
    }

    public void setPlus(@NotNull String pros) {
        this.plus = plus;
    }

    public @NotNull String getMinus() {
        return minus;
    }

    public void setMinus(@NotNull String cons) {
        this.minus = minus;
    }

    public @NotNull String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    public @NotNull String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Автор: "+ author + "\nПлюсы: " + plus + "\nМинусы: " + minus + "\nОтзыв: " + text + "\n";
    }
}
