package fit.edu.tmdt.shoes_store_api.Utils;

import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
}
