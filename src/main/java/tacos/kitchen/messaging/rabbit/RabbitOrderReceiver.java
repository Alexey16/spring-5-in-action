package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
public class RabbitOrderReceiver {
    private RabbitTemplate rabbitTemplate;
    private MessageConverter messageConverter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    public Order receiveOrder() {
        Message message = rabbitTemplate.receive("tacocloud.orders");
        return message != null ? (Order) messageConverter.fromMessage(message) : null;
        //or return (Order) rabbitTemplate.receiveAndConvert("tacocloud.order.queue", new ParameterizedTypeReference<Order>() {});
    }
}
