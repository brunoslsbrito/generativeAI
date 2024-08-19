package br.com.brittosw.generativeai.service;

import br.com.brittosw.generativeai.config.ConfigRabbitMQ;
import br.com.brittosw.generativeai.model.Message;
import br.com.brittosw.generativeai.repository.MessageRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues = {"myQueue"})
    public void consume(String message){
        messageRepository.save(Message.builder().content(message).build());
        LOGGER.info(String.format("Received message -> %s", message));
    }
}

