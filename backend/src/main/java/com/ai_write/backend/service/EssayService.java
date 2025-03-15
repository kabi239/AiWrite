package com.ai_write.backend.service;

import com.ai_write.backend.model.Level;
import com.ai_write.backend.payload.RequestDTO;
import com.ai_write.backend.payload.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EssayService {

    @Value("${gemini.api.url}")
    private String GEMINI_API_URL;

    public ResponseDTO genrateEssay(RequestDTO body) {
        String topic = body.getTopic();
        Long wordCount= body.getWord_count();
        Long noOfParagraphs = body.getNo_of_paragraphs();
        Level level = body.getLevel();

        // Construct request body as per Gemini API format
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(
                Map.of("parts", List.of(
                        Map.of("text", "Write a detailed essay on: " + topic+". With number_of_words:"+wordCount+", no_of_paragraphs:"+noOfParagraphs+"with the level of understanding as "+level)
                ))
        ));

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Send API request
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(GEMINI_API_URL, entity, Map.class);

        // 🔴 DEBUG: Print the full response to see its structure
        System.out.println("🔍 Raw API Response: " + response.getBody());

        // Extract response text
        String essay = extractGeminiResponse(response.getBody());

        // Return response
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setTopic(topic);
        responseDTO.setContent(essay);
        return responseDTO;
    }

    private String extractGeminiResponse(Map<String, Object> responseBody) {
        try {
            if (responseBody == null) {
                return "Error: Empty API response.";
            }

            // ✅ Check the Gemini response structure
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                return "Error: No candidates found in the response.";
            }

            // ✅ Extracting text content
            Map<String, Object> firstCandidate = candidates.get(0);
            Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
            if (content == null) {
                return "Error: Content is missing in the response.";
            }

            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            if (parts == null || parts.isEmpty()) {
                return "Error: Parts are missing in the response.";
            }
            String text=(String) parts.get(0).get("text");
            // return text.replace("\n"," ");
            return text;
        } catch (Exception e) {
            return "Error: Exception occurred while extracting essay content.";
        }
    }


}
