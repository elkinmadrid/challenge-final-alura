package co.elkinmadrid.challenge_final.topic.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicCreateDto(
        @NotBlank(message = "El titulo es requerido")
        String title,
        @NotBlank(message = "El mensaje es requerido")
        String message,
        @NotBlank(message = "El autor es requerido")
        String author,
        @NotBlank(message = "el curso es requerido")
        String course
) {
}
