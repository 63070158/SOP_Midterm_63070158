package com.example.sop_midterm_63070158;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public boolean addProduct(Product product) {
        try{
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return  false;
        }
    }
    public boolean updateProduct(Product product) {
        try{
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return  false;
        }
    }
    public boolean deleteProduct(Product product) {
        try{
            this.productRepository.delete(product);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return  false;
        }
    }
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }
    public Product getProductByName(String name) {
        try{
            return this.productRepository.findByName(name);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
