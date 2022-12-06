package org.example.readers;

import org.example.models.Message;
import org.example.models.MyException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class TrainingSetReader {
    private final String filePath;

    public TrainingSetReader(String filePath) {
        this.filePath = filePath;
    }
    public List<Message> readSet() throws MyException {
        try {
            return Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8)
                    .stream()
                    .map(text -> Arrays.stream(text.split("\t")).toList())
                    .map(list -> new Message(list.get(0), List.of(list.get(1).split(" "))))
                    .toList();
        } catch (IOException e) {
            throw new MyException(1, "Cannot read file by path " + filePath);
        }
    }
}

