package rw.ac.auca.ecommerce.core.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rw.ac.auca.ecommerce.core.base.AbstractBaseEntity;
import rw.ac.auca.ecommerce.core.customer.model.Customer;
import rw.ac.auca.ecommerce.core.product.model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends AbstractBaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private Boolean active = true;

    public ArrayList<Product> setProducts(ArrayList<Product> products) {
        return products;
    }

    public LocalDateTime setOrderDate(LocalDateTime now) {
        return now;
    }

    public double setTotalAmount(double total) {
        return total;
    }
}
