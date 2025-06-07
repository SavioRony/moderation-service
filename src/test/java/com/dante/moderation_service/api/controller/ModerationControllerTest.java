package com.dante.moderation_service.api.controller;

import com.dante.moderation_service.api.model.ModerationInput;
import com.dante.moderation_service.api.model.ModerationOutput;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class ModerationControllerTest {
    @InjectMocks
    ModerationController moderationController;

    @Test
    void Dado_um_comentario_valido_Quando_criar_Entao_deve_retorna_um_id_de_cadastro(){
        //Arrange
        ModerationInput moderationInput = ModerationInput.builder().commentId("123").text("Texto valido").build();

        //Act
        ModerationOutput moderationOutput = moderationController.validComment(moderationInput);

        //Assert
        assertEquals("Comentário aprovado", moderationOutput.getReason());
        assertTrue(moderationOutput.isApproved());

    }

    @Test
    void Dado_um_comentario_inapropriado_Quando_criar_Entao_deve_retorna_uma_exececao(){
        //Arrange
        ModerationInput moderationInput = ModerationInput.builder().commentId("123").text("idiota").build();

        //Act
        ModerationOutput moderationOutput = moderationController.validComment(moderationInput);

        //Assert
        assertFalse(moderationOutput.isApproved());
        assertEquals("Linguagem ofensiva (\"idiota\")",moderationOutput.getReason());

    }
}