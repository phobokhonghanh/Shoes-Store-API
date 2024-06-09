package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import fit.edu.tmdt.shoes_store_api.repository.SupportRepo;
import fit.edu.tmdt.shoes_store_api.repository.TypeRepo;
import fit.edu.tmdt.shoes_store_api.service.SupportService;
import fit.edu.tmdt.shoes_store_api.service.TypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
