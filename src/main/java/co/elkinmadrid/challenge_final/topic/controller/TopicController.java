package co.elkinmadrid.challenge_final.topic.controller;


import co.elkinmadrid.challenge_final.topic.dto.TopicCreateDto;
import co.elkinmadrid.challenge_final.topic.dto.TopicDto;
import co.elkinmadrid.challenge_final.topic.entity.Topic;
import co.elkinmadrid.challenge_final.topic.repository.TopicRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

@RestController()
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {

    private static final Logger log = LoggerFactory.getLogger(TopicController.class);
    private final TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicCreateDto body,
                                         UriComponentsBuilder uriBuilder) {

        Topic topic = topicRepository.save(Topic.builder()
                .title(body.title())
                .author(body.author())
                .course(body.course())
                .message(body.message())
                .status("CREATED")
                .created(new Date())
                .build());

        log.info("Entro");
        URI url = uriBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topic);
    }

    @GetMapping()
    public ResponseEntity<Page<TopicDto>> getAllTopic(@PageableDefault(sort = {"created"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findAll(pageable).map(TopicDto::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable Long id) {

        Optional<Topic> optionalTopic = topicRepository.findById(id.intValue());
        if (optionalTopic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Topic topic = optionalTopic.get();
        return ResponseEntity.ok(new TopicDto(topic));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id.intValue());
        if (optionalTopic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicRepository.deleteById(id.intValue());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable Long id,
                                                @Valid @RequestBody TopicCreateDto body) {
        Optional<Topic> optionalTopic = topicRepository.findById(id.intValue());
        if (optionalTopic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Topic topic = optionalTopic.get();
        topic.setTitle(body.title());
        topic.setAuthor(body.author());
        topic.setCourse(body.course());
        topic.setMessage(body.message());
        log.info("Topic to update: {}", topic);
        topicRepository.save(topic);
        return ResponseEntity.ok(new TopicDto(topic));
    }
}
