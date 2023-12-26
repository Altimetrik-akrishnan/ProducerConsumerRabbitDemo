package com.onlineshop.products.catalogue.config;
import com.onlineshop.products.catalogue.constants.CatalogueConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        
        return rabbitTemplate;
    }
    
    @Bean
	public Queue myQueue() {
		return new Queue(CatalogueConstants.MQ_NAME, true, false, false);
	}
}
