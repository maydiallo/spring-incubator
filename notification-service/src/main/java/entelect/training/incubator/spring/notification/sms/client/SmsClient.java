package entelect.training.incubator.spring.notification.sms.client;

import javax.jms.JMSException;

public interface SmsClient {
    
    void sendSms(String message) throws JMSException;
}
