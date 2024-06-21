package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Order.NotifyOrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderDTO;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderPaymentResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductDTO;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.service.OrderService;
import fit.edu.tmdt.shoes_store_api.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/client-api/order")
public class ClientOrdersController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/{accountId}", produces = "application/json")
    public ResponseEntity<Page<OrderResponse>> getAllByAccountID(@PathVariable Long accountId,
                                                                 @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                                 @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        Page<OrderResponse> list = orderService.getAllByAccountId(accountId, pageNo, pageSize, search);
        return ResponseUtil.getResponse(list, OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderPaymentResponse> create(@RequestBody OrderDTO orderDTO) {
        OrderPaymentResponse saveOrderResponse = orderService.create(orderDTO);
        return ResponseUtil.getResponse(saveOrderResponse, CREATED);
    }

}
