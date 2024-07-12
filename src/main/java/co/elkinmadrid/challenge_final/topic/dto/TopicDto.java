package co.elkinmadrid.challenge_final.topic.dto;

import co.elkinmadrid.challenge_final.topic.entity.Topic;


import java.util.Date;

public record TopicDto(

        String title,
        String message,
        String author,
        String course,
        String status,
        Date created
) {
    public TopicDto(Topic topic) {
        this(topic.getTitle(),
                topic.getMessage(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getStatus(),
                topic.getCreated()
        );
    }
}
