package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Getter
    String type;
    List<String> message;

    public Integer countWord(String word) {
        return Collections.frequency(message, word);
    }

    public Integer getMessageSize() {
        return message.size();
    }
}


