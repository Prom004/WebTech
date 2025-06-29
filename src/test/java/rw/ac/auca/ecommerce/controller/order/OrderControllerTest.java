package rw.ac.auca.ecommerce.controller.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import rw.ac.auca.ecommerce.core.order.service.IOrderService;
import rw.ac.auca.ecommerce.core.order.model.Order;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;

    private List<Order> orderList;

    @BeforeEach
    public void setup() {
        Order order1 = new Order();
        order1.setId(UUID.randomUUID());
        // Set other order fields as needed

        Order order2 = new Order();
        order2.setId(UUID.randomUUID());
        // Set other order fields as needed

        orderList = Arrays.asList(order1, order2);
    }

    @Test
    @WithMockUser
    public void testGetAllOrders() throws Exception {
        when(orderService.findAllOrders()).thenReturn(orderList);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orderList"))
                .andExpect(model().attributeExists("orders"));

        verify(orderService, times(1)).findAllOrders();
    }

    // Additional tests for order details, create, update, delete can be added similarly
}
