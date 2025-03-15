package com.ai_write.backend.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "Topic should not be blank")
    private String topic;
    private Long word_count;
    private Long no_of_paragraphs;
    private Level level;
}
