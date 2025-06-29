package rw.ac.auca.ecommerce.controller.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import rw.ac.auca.ecommerce.core.product.service.IProductService;
import rw.ac.auca.ecommerce.core.product.model.Product;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ProductPageUITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Test
    public void testProductListPage() throws Exception {
        when(productService.findProductsByState(true)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    public void testProductDetailsPage() throws Exception {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setProductName("Test Product");
        product.setDescription("Test Description");

        when(productService.findProductByIdAndState(product.getId(), true)).thenReturn(product);

        mockMvc.perform(get("/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productDetails"))
                .andExpect(model().attributeExists("product"));
    }
}
