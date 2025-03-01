package com.ai_write.backend.payload;

import com.ai_write.backend.model.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private String topic;
    private Long word_count;
    private Long no_of_paragraphs;
    private Level level;
}
