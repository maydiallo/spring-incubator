//package entelect.training.incubator.spring.notification.jms;
//import org.springframework.jms.annotation.JmsListener;
//
//import org.springframework.stereotype.Component;
//
//import javax.jms.JMSException;
//import entelect.training.incubator.spring.notification.sms.client.impl.MoloCellSmsClient;
//import javax.jms.Message;
//import javax.jms.TextMessage;
//
//
//@Component
//public class Listener {
//
//    MoloCellSmsClient moloCellSmsClient;
//
//    public Listener(){
//        moloCellSmsClient= new MoloCellSmsClient();
//    }
//
//    @JmsListener( destination = "comeonnn"//)
//            , containerFactory = "jmsListenerContainerFactory" )
//    public void onMessageReceived(final String message) throws JMSException {
//       String text = message;
//        //moloCellSmsClient.sendSms("testnumber", text);
//        System.out.println(text);
//    }
//
////    @JmsListener(destination = "inbound.topic"
//////            containerFactory = "jmsListenerContainerFactory"
////    )
////    @SendTo("outbound.topic")
////    public String receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
////        String messageData = null;
////        System.out.println("Received message " + jsonMessage);
////        String response = null;
////        if(jsonMessage instanceof TextMessage) {
////            TextMessage textMessage = (TextMessage)jsonMessage;
////            messageData = textMessage.getText();
////            Map map = new Gson().fromJson(messageData, Map.class);
////            response  = "Hello " + map.get("name");
////        }
////        return response;
////    }
//
//
////    @JmsListener(destination = "inbound.queue")
////    @SendTo("outbound.queue")
////    public String receiveMessage(final Message jsonMessage) throws JMSException {
////        String messageData = null;
////        System.out.println("Received message " + jsonMessage);
////        String response = null;
////        if(jsonMessage instanceof TextMessage) {
////            TextMessage textMessage = (TextMessage)jsonMessage;
////            messageData = textMessage.getText();
////            Map map = new Gson().fromJson(messageData, Map.class);
////            response  = "Hello " + map.get("name");
////        }
////        return response;
////    }
//
//}