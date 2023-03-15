//package entelect.training.incubator.spring.loyalty.client;
//
//import entelect.training.incubator.spring.loyalty.ws.model.CaptureRewardsRequest;
//import entelect.training.incubator.spring.loyalty.ws.model.CaptureRewardsResponse;
//import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
//
//import java.math.BigDecimal;
//
//public class CaptureRewardsClient extends WebServiceGatewaySupport {
//
//    public CaptureRewardsResponse getRewards(String passportNumber, BigDecimal amount) {
//        CaptureRewardsRequest request = new CaptureRewardsRequest();
//        request.setAmount(amount);
//        request.setPassportNumber(passportNumber);
//
//        CaptureRewardsResponse response = (CaptureRewardsResponse) getWebServiceTemplate()
//                .marshalSendAndReceive(request);
//        return response;
//    }
//}
//
