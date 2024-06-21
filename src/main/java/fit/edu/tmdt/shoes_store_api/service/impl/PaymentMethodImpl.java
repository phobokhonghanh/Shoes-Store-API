package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodDTO;
import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.Status;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.entities.PaymentMethod;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import fit.edu.tmdt.shoes_store_api.repository.PaymentMethodRepo;
import fit.edu.tmdt.shoes_store_api.repository.Specification.PaymentMethodSpecification;
import fit.edu.tmdt.shoes_store_api.repository.Specification.TypeSpecification;
import fit.edu.tmdt.shoes_store_api.repository.TypeRepo;
import fit.edu.tmdt.shoes_store_api.service.PaymentMethodService;
import fit.edu.tmdt.shoes_store_api.service.TypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentMethodImpl implements PaymentMethodService {
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;


    @Override
    public List<PaymentMethodResponse> getAll() {
        return convertBase.toListConvert(paymentMethodRepo.getAllByStatusId(Status.UNLOCK), PaymentMethodResponse.class);

    }

    @Override
    public Page<PaymentMethodResponse> getAll(Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<PaymentMethod> spec = Specification.where(PaymentMethodSpecification.containsKeywordInMultipleAttribute(search, "name","code"));
        Page<PaymentMethod> paymentEntity = paymentMethodRepo.findAll(spec, pageable);
        List<PaymentMethodResponse> typeResponses = convertBase.toListConvert(paymentEntity.getContent(), PaymentMethodResponse.class);
        return new PageImpl<>(typeResponses, pageable, paymentEntity.getTotalElements());
    }

    @Override
    public PaymentMethodResponse create(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = paymentMethodRepo.save(convertBase.convert(paymentMethodDTO, PaymentMethod.class));
        return convertBase.convert(paymentMethod, PaymentMethodResponse.class);
    }

    @Override
    public PaymentMethodResponse update(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(paymentMethodDTO.getId()).orElse(null);
        if (paymentMethod == null) return null;
        implUtil.updateFieldIfNotNull(paymentMethodDTO.getCode(), paymentMethod::setCode);
        implUtil.updateFieldIfNotNull(paymentMethodDTO.getName(), paymentMethod::setName);
        implUtil.updateFieldIfNotNull(new Support(paymentMethodDTO.getStatus().getId()), paymentMethod::setStatus);
        PaymentMethod update = paymentMethodRepo.save(paymentMethod);
        return convertBase.convert(update, PaymentMethodResponse.class);
    }
}
