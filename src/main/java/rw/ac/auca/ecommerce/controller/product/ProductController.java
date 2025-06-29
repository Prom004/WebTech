package rw.ac.auca.ecommerce.controller.product;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.ecommerce.core.product.model.Product;
import rw.ac.auca.ecommerce.core.product.service.IProductService;

import java.util.List;
// import java.util.Optional;

/**
 * The class ProductController.
 *
 * @author Jeremie Ukundwa Tuyisenge
 * @version 1.0
 */
@RequiredArgsConstructor
// @AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    //   public ProductController(IProductService productService) {
    //       this.productService = productService;
    //   }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<Product> products = productService.findProductsByState(Boolean.TRUE);
        model.addAttribute("products", products);
        return "product/productList";
    }

    @GetMapping("/details/{id}")
    public String productDetails(@PathVariable java.util.UUID id, Model model) {
        var product = productService.findProductByIdAndState(id, Boolean.TRUE);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/productDetails";
        } else {
            return "redirect:/product/list";
        }
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/productForm";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable java.util.UUID id, Model model) {
        var product = productService.findProductByIdAndState(id, Boolean.TRUE);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/productForm";
        } else {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("product") Product product) {
        productService.updateProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable java.util.UUID id) {
        var product = productService.findProductByIdAndState(id, Boolean.TRUE);
        if (product != null) {
            productService.deleteProduct(product);
        }
        return "redirect:/product/list";
    }
}
