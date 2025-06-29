package rw.ac.auca.ecommerce.controller.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import rw.ac.auca.ecommerce.core.product.service.IProductService;
import rw.ac.auca.ecommerce.core.product.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    private List<Product> productList;

    @BeforeEach
    public void setup() {
        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setProductName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setProductName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(20.0);

        productList = Arrays.asList(product1, product2);
    }

    @Test
    @WithMockUser
    public void testGetAllProducts() throws Exception {
        when(productService.findProductsByState(true)).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findProductsByState(true);
    }

    // Additional tests for product details, create, update, delete can be added similarly
}
