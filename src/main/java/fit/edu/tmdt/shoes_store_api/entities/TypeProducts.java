package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type_products")
public class TypeProducts {
    @Id
    @Column(name = "id_type")
    private Integer idType;

    @Column(name = "name_type")
    private String nameType;
}
