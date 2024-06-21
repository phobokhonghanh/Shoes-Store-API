package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodDTO;
import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodResponse;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethodResponse> getAll();

    Page<PaymentMethodResponse> getAll(Integer pageNo, Integer pageSize, String search);

    PaymentMethodResponse create(PaymentMethodDTO paymentMethodDTO);

    PaymentMethodResponse update(PaymentMethodDTO paymentMethodDTO);
}
