package org.example.models;

import lombok.Setter;
import org.example.readers.TrainingSetReader;

import java.util.Collections;
import java.util.List;

@Setter
public class TrainingSet {
    private List<Message> messageList;

    public TrainingSet(String filepath) throws MyException {
        TrainingSetReader trainingSetReader = new TrainingSetReader(filepath);
        messageList = trainingSetReader.readSet();
    }
    public Integer getSetSize() {
        //System.out.println("getSetSize() : " + messageList.size());
        return messageList.size();
    }

    public Integer getHamMessagesNumber() {
        //System.out.println("getHamMessagesNumber(): " + Collections.frequency(messageList.stream().map(Message::getType).toList(), "ham"));
        return Collections.frequency(messageList.stream().map(Message::getType).toList(), "ham");
    }

    public Integer getSpamMessagesNumber() {
        //System.out.println("getSpamMessagesNumber(): " + Collections.frequency(messageList.stream().map(Message::getType).toList(), "spam"));
        return Collections.frequency(messageList.stream().map(Message::getType).toList(), "spam");
    }

    public Integer getHamWordsNumber() {
        return messageList.stream()
                .filter(message -> message.getType().equals("ham"))
                .map(Message::getMessageSize)
                .reduce(0, Integer::sum);
    }

    public Integer getSpamWordsNumber() {
        return messageList.stream()
                .filter(message -> message.getType().equals("spam"))
                .map(Message::getMessageSize)
                .reduce(0, Integer::sum);
    }

    public Integer findWord(String word) {
        //System.out.println("findWord('" + word + "') : " + messageList.stream().map(message -> message.countWord(word)).reduce(0, Integer::sum));
        return messageList.stream()
                .map(message -> message.countWord(word))
                .reduce(0, Integer::sum);
    }

    public Integer getWordsNumber() {
        //System.out.println("getWordsNumber() : " + messageList.stream().map(Message::getMessageSize).reduce(0, Integer::sum));
        return messageList.stream()
                .map(Message::getMessageSize)
                .reduce(0, Integer::sum);
    }
}
