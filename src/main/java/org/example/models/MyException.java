package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyException extends Exception{
    Integer code;
    String message;
}
