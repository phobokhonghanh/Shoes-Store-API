package fit.edu.tmdt.shoes_store_api.controller.statistical;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/admin/statistical")
public class StatisticalController {
    @Autowired
    StatisticalService statisticalService;

    @GetMapping(value = "/order/money/all", produces = "application/json")
    public ResponseEntity sumTotalMoneyAll() {
        BigDecimal sumTotalMoneyOrderAll = statisticalService.sumTotalMoneyOrderAll();
        return ResponseUtil.getResponse(sumTotalMoneyOrderAll, OK);
    }

    @GetMapping(value = "/order/money/current", produces = "application/json")
    public ResponseEntity sumTotalMoneyCurrentDate() {
        BigDecimal sumTotalMoneyOrderCurrentDate = statisticalService.sumTotalMoneyOrderCurrentDate();
        return ResponseUtil.getResponse(sumTotalMoneyOrderCurrentDate, OK);
    }

    @GetMapping(value = "/order/money")
    public ResponseEntity sumTotalMoneyMonths(@RequestParam(name = "year", defaultValue = "2024") Integer year) {
        BigDecimal[] sumTotalMoneyOrderCurrentDate = statisticalService.sumTotalMoneyOrderMonths(year);
        return ResponseUtil.getResponse(sumTotalMoneyOrderCurrentDate, OK);
    }

    @GetMapping(value = "/order/count/all", produces = "application/json")
    public ResponseEntity sumTotalAll() {
        Long sumTotalOrderAll = statisticalService.sumTotalOrderAll();
        return ResponseUtil.getResponse(sumTotalOrderAll, OK);
    }

    @GetMapping(value = "/order/count/current", produces = "application/json")
    public ResponseEntity sumTotalCurrentDate() {
        Long sumTotalOrderCurrentDate = statisticalService.sumTotalOrderCurrentDate();
        return ResponseUtil.getResponse(sumTotalOrderCurrentDate, OK);
    }

    @GetMapping(value = "/order/count", produces = "application/json")
    public ResponseEntity sumTotalMonths(@RequestParam int year) {
        Integer[] sumTotalOrderMonths = statisticalService.sumTotalOrderMonths(year);
        return ResponseUtil.getResponse(sumTotalOrderMonths, OK);
    }

}
