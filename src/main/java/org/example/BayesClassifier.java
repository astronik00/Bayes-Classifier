package org.example;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.models.TrainingSet;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BayesClassifier {
    @Setter
    private Integer alpha;
    @Setter
    private TrainingSet trainingSet;

    public Double getQ1Probability() {
        //System.out.println("getQ1Probability() : " + (double) trainingSet.getHamMessagesNumber() / trainingSet.getSetSize());
        return (double) trainingSet.getHamMessagesNumber() / trainingSet.getSetSize();
    }

    public Double getQ2Probability() {
        //System.out.println("getQ2Probability() : " + (double) trainingSet.getSpamMessagesNumber() / trainingSet.getSetSize());
        return (double) trainingSet.getSpamMessagesNumber() / trainingSet.getSetSize();
    }

    public Double getHamProbability(List <String> message) {
        return Math.log(getQ1Probability()) +
                message.stream().map(word -> (double)
                        (alpha + trainingSet.findWord(word)) /
                        (alpha * trainingSet.getWordsNumber() + trainingSet.getHamWordsNumber()))
                .map(Math::log)
                .reduce(0.0, Double::sum);
    }

    public Double getSpamProbability(List <String> message) {
        return Math.log(getQ2Probability()) +
                message.stream().map(word -> (double)
                         (alpha + trainingSet.findWord(word)) /
                         (alpha * trainingSet.getWordsNumber() + trainingSet.getSpamWordsNumber()))
                .map(Math::log)
                .reduce(0.0, Double::sum);
    }

    public void predict(List <String> message) {
        System.out.println(
                "Ham with " + getHamProbability(message) + " probability" +
                "\nSpam with " + getSpamProbability(message) + " probability");
    }
}