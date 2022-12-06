package org.example;

import org.example.models.MyException;
import org.example.models.TrainingSet;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String filepath = "./src/main/resources/spam.txt";
            String message = "WINNER!! Hey I request reward";

            BayesClassifier bayesClassifier = new BayesClassifier();
            bayesClassifier.setAlpha(1);
            bayesClassifier.setTrainingSet(new TrainingSet(filepath));
            bayesClassifier.predict(List.of(message.split(" ")));
        } catch (MyException e) {
            System.out.println("Exception (" + e.getCode() + "): " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}