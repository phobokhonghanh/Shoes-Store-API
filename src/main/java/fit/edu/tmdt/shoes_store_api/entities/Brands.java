package fit.edu.tmdt.shoes_store_api.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brands implements Serializable {
    @Id
    @Column(name = "id_brand")
    private Integer idBrand;

    @Column(name = "name_brand")
    private String nameBrand;
}

