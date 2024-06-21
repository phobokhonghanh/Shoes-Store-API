package fit.edu.tmdt.shoes_store_api.controller.order;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.Status;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import fit.edu.tmdt.shoes_store_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}/admin/order")
public class OrdersController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Page<OrderResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseUtil.getResponse(orderService.getAll(pageNo, pageSize, search), OK);
    }


    @PatchMapping("/{code}")
    public ResponseEntity<OrderResponse> updateBrand(@PathVariable String code, @RequestParam(name = "status") String status) {
        return ResponseUtil.getResponse(orderService.updateStatus(code,status), CREATED);
    }
}
