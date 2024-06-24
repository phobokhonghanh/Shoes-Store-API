package fit.edu.tmdt.shoes_store_api.service.impl;

import fit.edu.tmdt.shoes_store_api.dto.Statistical.StatisticalOtherResponse;
import fit.edu.tmdt.shoes_store_api.dto.Statistical.StatisticalResponse;
import fit.edu.tmdt.shoes_store_api.service.StatisticalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticalImpl implements StatisticalService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BigDecimal sumTotalMoneyOrderAll() {
        Query query = entityManager.createNativeQuery("SELECT COALESCE(SUM(total_amount), 0) AS total_orders FROM `order` WHERE payment_method_order_id IS NOT NULL ",
                BigDecimal.class);
        return (BigDecimal) query.getSingleResult();
    }

    @Override
    public Long sumTotalOrderAll() {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) AS total_orders FROM `order`",
                Long.class);
        return (Long) query.getSingleResult();
    }

    @Override
    // Tính tổng tiền của các đơn hàng đã thanh toán trong ngày hôm nay
    public BigDecimal sumTotalMoneyOrderCurrentDate() {
        Query query = entityManager.createQuery(
                "SELECT COALESCE(SUM(o.totalAmount), 0) " +
                        "FROM Order o " +
                        "WHERE DATE(o.createdAt) = CURRENT_DATE() " +
                        "AND o.paymentMethodOrderId IS NOT NULL"
        );
        return (BigDecimal) query.getSingleResult();
    }

    @Override
    // tổng đơn hàng ngày hôm nay
    public Long sumTotalOrderCurrentDate() {
        Query query = entityManager.createQuery(
                "SELECT COUNT(o.id) " +
                        "FROM Order o " +
                        "WHERE DATE(o.createdAt) = CURRENT_DATE() " +
                        "AND o.paymentMethodOrderId IS NOT NULL"
        );
        return (Long) query.getSingleResult();
    }

    @Override
    // tổng tiền theo năm (đã thanh toán)
    public BigDecimal[] sumTotalMoneyOrderMonths(int year) {
        Query query = entityManager.createNativeQuery("SELECT MONTH(created_at) AS month, COALESCE(SUM(total_amount), 0) AS total_orders FROM `order` WHERE YEAR(created_at) = :year AND payment_method_order_id IS NOT NULL GROUP BY  MONTH(created_at) ORDER BY month;",
                StatisticalResponse.class);
        query.setParameter("year", year);  // Thiết lập giá trị cho tham số năm

        List<StatisticalResponse> list = query.getResultList();
        BigDecimal[] listResult = new BigDecimal[12];
        // Khởi tạo tất cả các giá trị của mảng là BigDecimal.ZERO
        Arrays.fill(listResult, BigDecimal.ZERO);

        // Điền dữ liệu vào mảng
        for (StatisticalResponse s : list) {
            listResult[s.getMonth() - 1] = s.getSum();
        }
        return listResult;
    }

    @Override
    // tổng đơn hàng theo năm
    public Integer[] sumTotalOrderMonths(int year) {
        Query query = entityManager.createNativeQuery("SELECT MONTH(created_at) AS month, COUNT(*) AS total_orders FROM `order` WHERE YEAR(created_at) = :year GROUP BY  MONTH(created_at) ORDER BY month;",
                StatisticalOtherResponse.class);
        query.setParameter("year", year);  // Thiết lập giá trị cho tham số năm

        List<StatisticalOtherResponse> list = query.getResultList();
        Integer[] listResult = new Integer[12];
        Arrays.fill(listResult, 0);

        // Điền dữ liệu vào mảng
        for (StatisticalOtherResponse s : list) {
            listResult[s.getMonth() - 1] = Math.toIntExact(s.getSum());
        }
        return listResult;
    }

    @Override
    public BigDecimal[] sumOrderByProductMonths(int year, Long id) {
        Query query = entityManager.createNativeQuery(
                "SELECT MONTH(o.created_at) AS month, SUM(o.total_amount) AS total_amount " +
                        "FROM `order` o " +
                        "JOIN `order_detail` od ON od.id_order_detail = o.id " +
                        "JOIN `size` s ON od.size_id = s.id " +
                        "JOIN `product` p ON s.product_id = p.id " +
                        "WHERE YEAR(o.created_at) = :year AND p.id = :productId " +
                        "GROUP BY MONTH(o.created_at) " +
                        "ORDER BY month;"
        );
        query.setParameter("year", year);
        query.setParameter("productId", id);

        List<Object[]> resultList = query.getResultList();
        BigDecimal[] listResult = new BigDecimal[12];
        Arrays.fill(listResult, BigDecimal.ZERO);

        // Điền dữ liệu vào mảng
        for (Object[] result : resultList) {
            int month = ((Number) result[0]).intValue();
            BigDecimal totalAmount = (BigDecimal) result[1];
            listResult[month - 1] = totalAmount;
        }
        return listResult;
    }

    @Override
    public Long countOrdersByProductId(int year, Long id) {
        Query query = entityManager.createNativeQuery(
                "SELECT COUNT(o.id) AS total_orders " +
                        "FROM `order` o " +
                        "JOIN `order_detail` od ON od.id_order_detail = o.id " +
                        "JOIN `size` s ON od.size_id = s.id " +
                        "JOIN `product` p ON s.product_id = p.id " +
                        "WHERE YEAR(o.created_at) = :year AND p.id = :productId"
        );
        query.setParameter("year", year);
        query.setParameter("productId", id);

        Object result = query.getSingleResult();
        return ((Number) result).longValue();
    }


}
