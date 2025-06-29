package rw.ac.auca.ecommerce.core.category.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rw.ac.auca.ecommerce.core.base.AbstractBaseEntity;
import rw.ac.auca.ecommerce.core.product.model.Product;

import java.util.List;

/**
 * The class Category.
 *
 * @author Jeremie Ukundwa Tuyisenge
 * @version 1.0
 */
@Getter
@Setter
@Entity
public class Category extends AbstractBaseEntity {
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
