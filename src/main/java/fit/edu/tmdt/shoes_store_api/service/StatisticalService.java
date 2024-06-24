package fit.edu.tmdt.shoes_store_api.service;

import java.math.BigDecimal;

public interface StatisticalService {
    // Tính tổng tiền của các đơn hàng đã thanh toán
    BigDecimal sumTotalMoneyOrderAll();
    // Tính tổng các đơn hàng

    Long sumTotalOrderAll();

    // Tính tổng tiền của các đơn hàng đã thanh toán trong ngày hôm nay
    BigDecimal sumTotalMoneyOrderCurrentDate();

    // tổng đơn hàng ngày hôm nay
    Long sumTotalOrderCurrentDate();


    // tổng tiền theo năm (đã thanh toán)
    BigDecimal[] sumTotalMoneyOrderMonths(int year);

    // tổng đơn hàng theo năm
    Integer[] sumTotalOrderMonths(int year);

    BigDecimal[] sumOrderByProductMonths(int year, Long id);


    Long countOrdersByProductId(int year, Long productId);

}
