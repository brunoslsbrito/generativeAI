package br.com.brittosw.generativeai.controller;

import br.com.brittosw.generativeai.model.ChatRequest;
import br.com.brittosw.generativeai.model.ChatResponse;
import br.com.brittosw.generativeai.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {

    @Autowired
    private PromptService promptService;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
      return this.promptService.produceMessage(prompt);

    }
}
