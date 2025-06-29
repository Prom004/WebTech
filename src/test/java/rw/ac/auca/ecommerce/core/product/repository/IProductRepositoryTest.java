package rw.ac.auca.ecommerce.core.product.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import rw.ac.auca.ecommerce.core.product.model.Product;
import rw.ac.auca.ecommerce.core.util.product.EStockState;

@DataJpaTest
public class IProductRepositoryTest {

    @Autowired
    private IProductRepository productRepository;

    @Test
    public void testFindAllByActive() {
        List<Product> products = productRepository.findAllByActive(true);
        assertNotNull(products);
    }

    @Test
    public void testFindByIdAndActive() {
        List<Product> products = productRepository.findAllByActive(true);
        if (!products.isEmpty()) {
            Product product = products.get(0);
            UUID id = product.getId();
            var found = productRepository.findByIdAndActive(id, true);
            assertTrue(found.isPresent());
            assertEquals(id, found.get().getId());
        }
    }

    @Test
    public void testFindAllByStockStateAndActive() {
        List<Product> products = productRepository.findAllByStockStateAndActive(EStockState.AVAILABLE, true);
        assertNotNull(products);
    }
}
