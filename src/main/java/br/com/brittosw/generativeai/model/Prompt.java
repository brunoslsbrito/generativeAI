package br.com.brittosw.generativeai.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Prompt")
@Builder
@AllArgsConstructor
public class Prompt {

    private String content;
    @OneToOne
    @JoinColumn(name = "prompt")
    private Message message;
}
