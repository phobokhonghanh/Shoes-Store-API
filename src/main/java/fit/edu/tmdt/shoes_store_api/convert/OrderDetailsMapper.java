package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.OrderDetailsDTO;
import fit.edu.tmdt.shoes_store_api.entities.OrderDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public OrderDetails toEntity(OrderDetailsDTO orderDetailsDTO) {
        return modelMapper.map(orderDetailsDTO, OrderDetails.class);
    }

    public OrderDetailsDTO toDTO(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDetailsDTO.class);
    }
}
