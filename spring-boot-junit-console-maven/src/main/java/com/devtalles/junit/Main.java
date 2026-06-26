package com.devtalles.junit;

public class Main {
    public static void main(String[] args) {
        MessageService messageService = new MessageService();
        String message = messageService.greet("Equipo");
        System.out.println(message);
    }
}