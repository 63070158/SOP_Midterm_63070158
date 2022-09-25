package com.example.sop_midterm_63070158;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean serviceAddProduct(@RequestBody Product product) {
        try {
            return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "add", product);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean serviceUpdateProduct(@RequestBody Product product) {
        try {
            return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "update", product);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean serviceDeleteProduct(@RequestBody Product product) {
        try {
            return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "delete", product);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @RequestMapping(value = "/getProduct/{name}", method = RequestMethod.GET)
    public Product serviceGetProductName(@PathVariable("name") Product product) {
        try {
            Object out = rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", product);
            return (Product) out;

        } catch (Exception e) {
            return null;

        }
    }

    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    public List<Product> serviceGetAllProduct() {
        try {
            Object out = rabbitTemplate.convertSendAndReceive("ProductExchange", "getall", "get all");
            return (List<Product>) out;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
