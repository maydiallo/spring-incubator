//package entelect.training.incubator.spring.loyalty.client;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//
//@Configuration
//public class CaptureRewardsClientConfig {
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("entelect.training.incubator.spring.loyalty");
//        return marshaller;
//    }
//    @Bean
//    public CaptureRewardsClient countryClient(Jaxb2Marshaller marshaller) {
//        CaptureRewardsClient client = new CaptureRewardsClient();
//        client.setDefaultUri("http://localhost:8208/ws");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
//}
