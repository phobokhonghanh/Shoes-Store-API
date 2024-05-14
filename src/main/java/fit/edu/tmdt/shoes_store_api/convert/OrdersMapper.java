package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.OrdersDTO;
import fit.edu.tmdt.shoes_store_api.entities.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdersMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Orders toEntity(OrdersDTO orderDTO) {
        return modelMapper.map(orderDTO, Orders.class);
    }

    public OrdersDTO toDTO(Orders order) {
        return modelMapper.map(order, OrdersDTO.class);
    }
}
