package com.devtalles.junit;

public class MessageService {
    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hola, invitado!";
        }
        return "Hola, " + name + "!";
    }
}
