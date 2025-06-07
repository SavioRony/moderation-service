package com.dante.moderation_service.api.controller;


import com.dante.moderation_service.api.model.ModerationInput;
import com.dante.moderation_service.api.model.ModerationOutput;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/moderation")
@RequiredArgsConstructor
public class ModerationController {

    private static final Logger logger = LoggerFactory.getLogger(ModerationController.class);

    @PostMapping
    public ModerationOutput validComment(@RequestBody ModerationInput moderationInput){
        return validarComentario(moderationInput);

    }

    public static ModerationOutput validarComentario(ModerationInput commentInput) {
        ModerationOutput output = new ModerationOutput();
        String textoLower = commentInput.getText().toLowerCase();

        for (Map.Entry<String, String> entrada : PALAVRAS_PROIBIDAS.entrySet()) {
            if (textoLower.contains(entrada.getKey())) {
                String motivo = entrada.getValue() + " (\"" + entrada.getKey() + "\")";
                output.setApproved(false);
                output.setReason(motivo);
                logger.warn(String.format("Comentário {0} reprovado: {1}",commentInput.getCommentId(), commentInput.getText()));
                return output;
            }
        }

        output.setApproved(true);
        output.setReason("Comentário aprovado");
        logger.info(String.format("Comentário {0} aprovado: {1}",commentInput.getCommentId(), commentInput.getText()));
        return output;
    }

    private static final Map<String, String> PALAVRAS_PROIBIDAS = Map.of(
            "idiota", "Linguagem ofensiva",
            "burro", "Termo pejorativo",
            "palavrão", "Uso de palavrão",
            "racista", "Conteúdo discriminatório",
            "homofóbico", "Discurso de ódio"
    );

}
