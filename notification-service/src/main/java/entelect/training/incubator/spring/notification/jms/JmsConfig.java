package entelect.training.incubator.spring.notification.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

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
   public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
       DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
       factory.setConnectionFactory(connectionFactory());
       factory.setConcurrency("1-1");
       factory.setPubSubDomain(false);
       return factory;
   }

}
