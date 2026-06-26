package com.devtalles.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    @Test
    public void greetShouldReturnMessageWithName() {
        //Arrange - Preparación
        MessageService service = new MessageService();
        String input = "Gabriel";

        //Act - Acción
        String result = service.greet(input);

        //Assert - Verificación
        assertEquals("Hola, Gabriel!", result);
    }

    @Test
    public void greetShouldReturnMessageForGuestWhenNameIsEmpty() {
        //Arrange - Preparación
        MessageService service = new MessageService();
        String input = "";

        //Act - Acción
        String result = service.greet(input);

        //Assert - Verificación
        assertEquals("Hola, invitado!", result);
    }


}