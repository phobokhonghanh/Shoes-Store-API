package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.SupportDTO;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupportMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Support toEntity(SupportDTO supportDTO) {
        Support support = modelMapper.map(supportDTO, Support.class);
        return support;
    }

    public SupportDTO toDTO(Support support) {
        SupportDTO supportDTO = modelMapper.map(support, SupportDTO.class);
        return supportDTO;
    }
}
