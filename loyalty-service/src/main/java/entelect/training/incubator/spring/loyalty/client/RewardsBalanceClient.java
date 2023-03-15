//package entelect.training.incubator.spring.loyalty.client;
//
//import entelect.training.incubator.spring.loyalty.ws.model.CaptureRewardsRequest;
//import entelect.training.incubator.spring.loyalty.ws.model.CaptureRewardsResponse;
//import entelect.training.incubator.spring.loyalty.ws.model.RewardsBalanceRequest;
//import entelect.training.incubator.spring.loyalty.ws.model.RewardsBalanceResponse;
//import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
//
//import java.math.BigDecimal;
//
//public class RewardsBalanceClient extends WebServiceGatewaySupport {
//
//    public RewardsBalanceResponse getRewards(String passportNumber) {
//        RewardsBalanceRequest request = new RewardsBalanceRequest();
//        request.setPassportNumber(passportNumber);
//
//        RewardsBalanceResponse response = (RewardsBalanceResponse) getWebServiceTemplate()
//                .marshalSendAndReceive(request);
//        return response;
//    }
//}
