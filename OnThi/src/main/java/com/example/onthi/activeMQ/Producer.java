package com.example.onthi.activeMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String queueName, final String message) {
//        Map map = new Gson().fromJson(message, Map.class);
//        final String textMessage = "Chào nhé " + map.get("name");
        System.err.println(">>>>>>> Gửi tin nhắn <<<<<<<<<<");
        System.err.println("== " + message +  " đến queue - " + queueName);
        jmsTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
