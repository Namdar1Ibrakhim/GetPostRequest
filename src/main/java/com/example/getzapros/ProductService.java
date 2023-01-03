package com.example.getzapros;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    // Сервис который добавляет продукты и выводит на экран их
    // Мы добавили этот бин в контекст поскольку нам нужна зависимость этого бина с Контроллером

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p){
        products.add(p);
    }
    public List<Product> findAll() {
        return products;
    }
}
