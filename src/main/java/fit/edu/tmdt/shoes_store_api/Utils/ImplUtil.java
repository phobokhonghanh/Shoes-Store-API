package fit.edu.tmdt.shoes_store_api.Utils;

import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

@Service
public class ImplUtil {
    @Autowired
    ConvertBase convertBase;

    public <D> D getOptional(Optional data, Class<D> objClass) {
        if (data.isPresent()) {
            return convertBase.convert(data.get(), objClass);
        }
        return null;
    }

    public String renderOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    public <T> void updateFieldIfNotNull(T field, Consumer<T> setter) {
        if (field != null) {
            setter.accept(field);
        }
    }

    public String generateId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
        int length = 8;
        StringBuilder ticketCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            ticketCode.append(randomChar);
        }
        return ticketCode.toString();
    }
}
