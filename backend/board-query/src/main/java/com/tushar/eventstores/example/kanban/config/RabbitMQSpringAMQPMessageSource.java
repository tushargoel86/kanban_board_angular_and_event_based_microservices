package com.tushar.eventstores.example.kanban.config;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("rabbitMQSpringAMQPMessageSource")
public class RabbitMQSpringAMQPMessageSource {

    

    @RabbitListener(queues = "${queue_name}")
    	public void onMessage(final Message message, final Channel channel) {
        log.debug("received message: message={}, channel={}", message, channel);
      //  super.onMessage(message, channel);
    }

}