package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import fit.edu.tmdt.shoes_store_api.repository.Specification.TypeSpecification;
import fit.edu.tmdt.shoes_store_api.repository.TypeRepo;
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
public class TypeImpl implements TypeService {
    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;

    @Override
    public Type getType(Long id) {
        Type type = typeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Type not found"));
        return type;
    }

    @Override
    public List<TypeResponse> getAll() {
        return convertBase.toListConvert(typeRepo.findAll(), TypeResponse.class);

    }

    @Override
    public Page<TypeResponse> getAll(Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<Type> spec = Specification.where(TypeSpecification.containsTextInField(search, "name"));
        Page<Type> typeEntity = typeRepo.findAll(spec, pageable);
        List<TypeResponse> typeResponses = convertBase.toListConvert(typeEntity.getContent(), TypeResponse.class);
        return new PageImpl<>(typeResponses, pageable, typeEntity.getTotalElements());
    }

    @Override
    public TypeResponse createType(TypeDTO typeDTO) {
        Type type = typeRepo.save(convertBase.convert(typeDTO, Type.class));
        return convertBase.convert(type, TypeResponse.class);
    }

    @Override
    public TypeResponse updateType(TypeDTO typeDTO) {
        Type type = typeRepo.findById(typeDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Type not found"));
        if (typeDTO.getName() != null) {
            type.setName(typeDTO.getName());
        }
        Type updateType = typeRepo.save(type);
        return convertBase.convert(updateType, TypeResponse.class);
    }
}
