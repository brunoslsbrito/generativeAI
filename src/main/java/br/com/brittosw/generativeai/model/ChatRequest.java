package br.com.brittosw.generativeai.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n = 1;
    private double temperature;

    public ChatRequest(String model, String msg) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",msg));
    }


}


