//package entelect.training.incubator.spring.loyalty.client;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//
//public class RewardsBalanceClientConfig {
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("entelect.training.incubator.spring.loyalty");
//        return marshaller;
//    }
//    @Bean
//    public RewardsBalanceClient countryClient(Jaxb2Marshaller marshaller) {
//        RewardsBalanceClient client = new RewardsBalanceClient();
//        client.setDefaultUri("http://localhost:8208/ws");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
//}
