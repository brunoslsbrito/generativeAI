package br.com.brittosw.generativeai.service;

import br.com.brittosw.generativeai.config.ConfigRabbitMQ;
import br.com.brittosw.generativeai.model.ChatRequest;
import br.com.brittosw.generativeai.model.ChatResponse;
import br.com.brittosw.generativeai.model.Prompt;
import br.com.brittosw.generativeai.repository.PromptRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PromptService {
    private final RabbitTemplate rabbitTemplate;
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.ai.openai.chat.options.model}")
    private String model;

    @Value("${spring.ai.openai.chat.base-url}")
    private String apiUrl;
    @Autowired
    private PromptRepository promptRepository;

    public PromptService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String produceMessage(String message) {

        // create a request
        ChatRequest request = new ChatRequest(model, message);
        var prompt = Prompt.builder().content(message).build();

        //check if exists

        var promptOptional = promptRepository.findByContent(message);
        if (promptOptional.isEmpty()) {
            // call the API
            ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

            if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
                return "No response";
            }
            rabbitTemplate.convertAndSend(ConfigRabbitMQ.EXCHANGE_NAME, "myRoutingKey.messages", response.getChoices().getFirst().getMessage().getContent());
            prompt.setMessage(response.getChoices().getFirst().getMessage());
            this.promptRepository.save(prompt);
            return prompt.getMessage().getContent();
        } else {
            return promptOptional.stream().findFirst().get().getMessage().getContent();
        }
    }


}

