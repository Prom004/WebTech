package rw.ac.auca.ecommerce.core.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rw.ac.auca.ecommerce.core.product.model.Product;
import rw.ac.auca.ecommerce.core.product.repository.IProductRepository;
import rw.ac.auca.ecommerce.core.util.product.EStockState;

public class ProductServiceImplTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(UUID.randomUUID());
        product.setProductName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setStockState(EStockState.AVAILABLE);
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(product)).thenReturn(product);
        Product created = productService.createProduct(product);
        assertNotNull(created);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        when(productRepository.save(product)).thenReturn(product);
        Product updated = productService.updateProduct(product);
        assertNotNull(updated);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.save(product)).thenReturn(product);
        Product deleted = productService.deleteProduct(product);
        assertNotNull(deleted);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testFindProductsByState() {
        when(productRepository.findAllByActive(true)).thenReturn(List.of(product));
        List<Product> products = productService.findProductsByState(true);
        assertNotNull(products);
        assertFalse(products.isEmpty());
        verify(productRepository, times(1)).findAllByActive(true);
    }
}
