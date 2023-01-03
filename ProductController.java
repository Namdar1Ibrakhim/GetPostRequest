package com.example.getzapros;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/home")//По умолчанию эта аннотация GET запрос по пути /home
    //@GetMapping
    public String viewProduct(Model model){
        var products = productService.findAll();
        model.addAttribute("products", products); //Product — это класс модели, так что мы поместим его в пакет model

        return "home.html"; //Возвращаем имя в представление представления — представление будет получено и воспроизведено диспетчером сервлетов
    }


    @RequestMapping(path = "/home", method = RequestMethod.POST) //Связываем действие контроллера с путем /products. С помощью атрибута аннотации @RequestMapping изменяем HTTP-метод на POST
    //@PostMapping
    public String addProduct(@RequestParam String name, @RequestParam double price, Model model){ //Из параметров запроса получаем наименование и цену добавляемого товара
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.addProduct(product);

        var products = productService.findAll();
        model.addAttribute("products", products);
        return "home.html";
    }

    /*

    //Можно не писать @RequestParam и наименование а принять сразу Product p в таком случае если параемтры
    //Поскольку имена параметров запроса совпадают с именами атрибутов класса Product, Spring знает,
    //какие параметры каким атрибутам соответствуют, и автоматически создает объект.
       @PostMapping("/products")
           public String addProduct(Product p, Model model){
                productService.addProduct(p);
                var products = productService.findAll();
                model.addAttribute("products", products);
                return "products.html";
            }
     */
}
