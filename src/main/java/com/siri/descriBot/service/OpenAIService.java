package com.siri.descriBot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@Service
public class OpenAIService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public OpenAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public String generateDescription(String productName, String category) {
        Map<String, Object> requestBody = getStringObjectMap(productName, category);

        try {
            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + openaiApiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            var choices = (java.util.List<Map<String, Object>>) response.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map msg = (Map) choices.get(0).get("message");
                return  (String) msg.get("content");
            } else {
                return "Our AI went on a coffee break ☕ — please try again later!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Looks like the product copywriter is daydreaming \uD83D\uDCA4 — no description generated.";
        }
    }

    private static Map<String, Object> getStringObjectMap(String productName, String category) {
        String prompt = "Write a friendly, engaging product description for a product named "
                + productName + " in the category " + category + "." + "Make sure it is trendy and attractive to all age groups.";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are a industry expert Product Copywriter"),
                        Map.of("role", "user", "content", prompt)
                }
        );
        return requestBody;
    }
}
