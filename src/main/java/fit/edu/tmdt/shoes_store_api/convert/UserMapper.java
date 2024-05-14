package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.UsersDTO;
import fit.edu.tmdt.shoes_store_api.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Users toEntity(UsersDTO userDTO) {
        Users user = modelMapper.map(userDTO, Users.class);
        return user;
    }

    public UsersDTO toDTO(Users user) {
        UsersDTO userDTO = modelMapper.map(user, UsersDTO.class);
        return userDTO;
    }
}
