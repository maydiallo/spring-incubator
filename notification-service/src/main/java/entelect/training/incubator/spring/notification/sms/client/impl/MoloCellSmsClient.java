package entelect.training.incubator.spring.notification.sms.client.impl;

import entelect.training.incubator.spring.notification.sms.client.SmsClient;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;

/**
 * A custom implementation of a fictional SMS service.
 */
@Service
public class MoloCellSmsClient implements SmsClient {
    
    @Override
    @JmsListener( destination = "confirmation message"
            , containerFactory = "jmsListenerContainerFactory" )
    public void sendSms(final String text)throws JMSException {
        String[] arrOfStr = text.split("\r\n", 2);
        String phoneNumber= arrOfStr[0];
        String message = arrOfStr[1];

       System.out.println(String.format("Sending SMS, destination='{}', '{}'", phoneNumber, message));
    }
}
