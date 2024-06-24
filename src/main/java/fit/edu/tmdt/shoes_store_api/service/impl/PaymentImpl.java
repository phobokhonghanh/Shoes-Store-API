package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.config.paypal.PayPalHttpClient;
import fit.edu.tmdt.shoes_store_api.constant.App;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Payment.paypal.OrderIntent;
import fit.edu.tmdt.shoes_store_api.dto.Payment.paypal.PaymentLandingPage;
import fit.edu.tmdt.shoes_store_api.dto.Payment.paypal.PaypalRequest;
import fit.edu.tmdt.shoes_store_api.dto.Payment.paypal.PaypalResponse;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Order;
import fit.edu.tmdt.shoes_store_api.repository.OrderRepo;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.service.PaymentService;
import fit.edu.tmdt.shoes_store_api.service.SizeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Log4j2
@Service
@AllArgsConstructor
public class PaymentImpl implements PaymentService {
    @Autowired
    OrderRepo orderRepo;
    private static final int USD_VND_RATE = 23_000;
    private final PayPalHttpClient payPalHttpClient;

    @Override
    public String paymentByPaypal(Order order) {
        try {

            BigDecimal billSum = order.getTotalAmount();
            BigDecimal totalPayUSD = billSum.divide(BigDecimal.valueOf(USD_VND_RATE), 0, BigDecimal.ROUND_HALF_UP);

            // Tạo một yêu cầu giao dịch PayPal
            PaypalRequest paypalRequest = new PaypalRequest();

            paypalRequest.setIntent(OrderIntent.CAPTURE);
            paypalRequest.setPurchaseUnits(List.of(
                    new PaypalRequest.PurchaseUnit(
                            new PaypalRequest.PurchaseUnit.Money("USD", totalPayUSD.toString())
                    )
            ));

            paypalRequest.setApplicationContext(new PaypalRequest.PayPalAppContext()
                    .setBrandName("Sneaker Shoes Store")
                    .setLandingPage(PaymentLandingPage.BILLING)
                    .setReturnUrl(App.BACKEND_API + "/client-api/payment/paypal/success")
                    .setCancelUrl(App.BACKEND_API + "/client-api/payment/paypal/cancel"));
            // tạo giao dịch
            PaypalResponse paypalResponse = payPalHttpClient.createPaypalTransaction(paypalRequest);
            order.setPaymentMethodOrderId(paypalResponse.getId());
            orderRepo.save(order);
            // Trả về đường dẫn checkout cho user
            for (PaypalResponse.Link link : paypalResponse.getLinks()) {
                if ("approve".equals(link.getRel())) {
                    return link.getHref();
                }
            }
        } catch (Exception e) {
            log.warn("Cannot create PayPal transaction request!");
            throw new RuntimeException("Cannot create PayPal transaction request!" + e.getMessage());
        }
        return null;
    }
    @Override
    public boolean captureTransactionPaypal(String paypalOrderId, String payerId) {
        Order order = orderRepo.findByPaymentMethodOrderId(paypalOrderId);
        if (order != null) {
            try {
                // (1) Capture
                payPalHttpClient.capturePaypalTransaction(paypalOrderId, payerId);
                order.setPaymentMethodOrderId("true"); // (2) Đã thanh toán
            } catch (Exception e) {
                log.warn("Cannot create PayPal capture transaction request!" + e.getMessage());
                return false;
            }
            orderRepo.save(order);
            return true;
        }
        return false;
    }
}
