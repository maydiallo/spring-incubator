package entelect.training.incubator.spring.booking.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class JmsConfig {

   String BROKER_URL = "tcp://localhost:61616";
   String BROKER_USERNAME = "admin";
   String BROKER_PASSWORD = "admin";

   @Bean
   public ActiveMQConnectionFactory connectionFactory(){
       ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
       connectionFactory.setBrokerURL(BROKER_URL);
       connectionFactory.setPassword(BROKER_USERNAME);
       connectionFactory.setUserName(BROKER_PASSWORD);
       return connectionFactory;
   }

   @Bean
   public JmsTemplate jmsTemplate(){
       JmsTemplate template = new JmsTemplate();
       template.setConnectionFactory(connectionFactory());
       template.setPubSubDomain(false);
       return template;
   }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
