package rw.ac.auca.ecommerce.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.ecommerce.core.order.model.Order;
import rw.ac.auca.ecommerce.core.order.service.IOrderService;
import rw.ac.auca.ecommerce.core.product.model.Product;
import rw.ac.auca.ecommerce.core.product.service.IProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private IOrderService orderService;
    private IProductService productService;

    private List<Product> cart = new ArrayList<>();

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        double total = 0.0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        model.addAttribute("total", total);
        return "order/cart";
    }

    @GetMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable UUID productId) {
        var product = productService.findProductByIdAndState(productId, true);
        if (product != null) {
            cart.add(product);
        }
        return "redirect:/order/cart";
    }

    @GetMapping("/remove-from-cart/{productId}")
    public String removeFromCart(@PathVariable UUID productId) {
        cart.removeIf(p -> p.getId().equals(productId));
        return "redirect:/order/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cart", cart);
        double total = 0.0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        model.addAttribute("total", total);
        model.addAttribute("order", new Order());
        return "order/checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute Order order) {
        order.setProducts(new ArrayList<>(cart));
        order.setOrderDate(LocalDateTime.now());
        double total = 0.0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        order.setTotalAmount(total);
        order.setActive(true);
        orderService.createOrder(order);
        cart.clear();
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation() {
        return "order/confirmation";
    }
}
