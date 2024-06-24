package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.constant.App;
import fit.edu.tmdt.shoes_store_api.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("${api.prefix}/client-api")
public class ClientPaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/payment/paypal/success")
    public RedirectView paymentSuccessAndCaptureTransaction(HttpServletRequest request) {
        String paypalOrderId = request.getParameter("token");
        String payerId = request.getParameter("PayerID");
        RedirectView redirectView = new RedirectView();
        if (!paymentService.captureTransactionPaypal(paypalOrderId, payerId)) {
            redirectView.setUrl(App.CLIENT_HOST + "/payment/cancel");
            return redirectView;
        }
        redirectView.setUrl(App.CLIENT_HOST + "/payment/success");
        return redirectView;
    }

    @GetMapping(value = "/payment/paypal/cancel")
    public RedirectView paymentCancel() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(App.CLIENT_HOST + "/payment/cancel");
        return redirectView;
    }
}
