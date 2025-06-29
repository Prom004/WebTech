package rw.ac.auca.ecommerce.controller.customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import rw.ac.auca.ecommerce.core.customer.service.ICustomerService;
import rw.ac.auca.ecommerce.core.customer.model.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService customerService;

    private List<Customer> customerList;

    @BeforeEach
    public void setup() {
        Customer customer1 = new Customer();
        customer1.setId(UUID.randomUUID());
        // Set other customer fields as needed

        Customer customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        // Set other customer fields as needed

        customerList = Arrays.asList(customer1, customer2);
    }

    @Test
    @WithMockUser
    public void testGetAllCustomers() throws Exception {
        when(customerService.findCustomersByState(true)).thenReturn(customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerList"))
                .andExpect(model().attributeExists("customers"));

        verify(customerService, times(1)).findCustomersByState(true);
    }

    // Additional tests for customer details, create, update, delete can be added similarly
}
