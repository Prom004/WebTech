package rw.ac.auca.ecommerce.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import rw.ac.auca.ecommerce.core.product.model.Product;
import rw.ac.auca.ecommerce.core.product.service.IProductService;
import rw.ac.auca.ecommerce.core.util.product.EStockState;

@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    private IProductService productService;

    @Test
    public void testCreateAndFindProduct() {
        Product product = new Product();
        product.setProductName("Integration Test Product");
        product.setDescription("Integration Test Description");
        product.setPrice(50.0);
        product.setStockState(EStockState.AVAILABLE);

        Product created = productService.createProduct(product);
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        List<Product> products = productService.findProductsByState(true);
        assertThat(products).isNotEmpty();
        assertThat(products).extracting("id").contains(created.getId());
    }
}
