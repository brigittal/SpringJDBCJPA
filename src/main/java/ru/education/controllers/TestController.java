package ru.education.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.education.entity.SalesPeriodJdbcDemo;
import ru.education.entity.SalesPeriodJpaDemo;
import ru.education.jdbc.SalesPeriodJdbcRepository;
import ru.education.jpa.Product;
import ru.education.jpa.ProductRepository;
import ru.education.jpa.SalesPeriodRepository;
import ru.education.model.Formatter;

import java.util.List;

@RestController //По этой антотации спринг поймет что нужно создать
//экземпляр этого класса и положить его в контекст
@RequestMapping ("api/v1")//Путь для запросов из вне
public class TestController {

    //объявляем
    @Autowired
    @Qualifier("fooFormatter") //указали конкретную реализацию
    private final Formatter formatter;

    //эту часть лучше не смешивать, но для примера используем
    @Autowired
    private final ProductRepository productRepository;
    //вот до этого момента

    //объявляем jdbs репозиторий:
    private final SalesPeriodJdbcRepository salesPeriodJdbcRepository;

    private final SalesPeriodRepository salesPeriodRepository;

    @Autowired
    public TestController(Formatter formatter, ProductRepository productRepository, SalesPeriodJdbcRepository salesPeriodJdbcRepository, SalesPeriodRepository salesPeriodRepository) {
        this.formatter = formatter;
        this.productRepository = productRepository;
        this.salesPeriodJdbcRepository = salesPeriodJdbcRepository;
        this.salesPeriodRepository = salesPeriodRepository;
    }

    //реализуем метод по которому
    //сможем обратиться к нашему контроллеру
    @GetMapping ("/hello")//указываем что мы обращаемся по методу гет
    public String getHello(){
        return "Hello, World!";
    }

    @GetMapping("/format")
    public String getFormat(){
        return formatter.format();
    }

    //список продуктов
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    //добавляем метод для тестирования работы (кол-во строк)
    @GetMapping("sales/count")
    public Integer getSalesCount(){
        return salesPeriodJdbcRepository.count();
    }

    @GetMapping("/sales")
    public List<SalesPeriodJdbcDemo> getSalesPeriods(){
        return salesPeriodJdbcRepository.getSalesPeriods();}

    @GetMapping("/sales/byhigherprice")
    public List<SalesPeriodJdbcDemo> getSalesPeriodsByHigherPrice(){
        return salesPeriodJdbcRepository.getSalesPeriodsPriceIsHigher(90);
    }

    @GetMapping("/products/sales/active")
    public List<Product> getProductsWhithActivePeriod(){
        return salesPeriodJdbcRepository.getProductWhithActivePeriod();
    }

    @GetMapping("/sales/jpa")
    public List<SalesPeriodJpaDemo> getSalesPeriodJpa() {
        return salesPeriodRepository.findAll();
    }

    @PostMapping("/sales/jpa")
    public SalesPeriodJpaDemo addSalePeriodsJpa(@RequestBody SalesPeriodJpaDemo salesPeriodJpaDemo){
        return salesPeriodRepository.save(salesPeriodJpaDemo);
    }

    @GetMapping("/sales/jpa/max/price")
    public Integer getMaxPriceByProductId(){ return salesPeriodRepository.getMaxPriceByProductId(1);}

    @GetMapping("/sales/jpa/exists/price")
    public boolean existsByPrice() {return salesPeriodRepository.existsByPrice(50);}

    @GetMapping("/sales/jpa/active")
    public List<SalesPeriodJpaDemo> findByDateToIsNull(){return salesPeriodRepository.findByDateToIsNull();}

    @GetMapping("/sales/jpa/byproductname")
    public List<SalesPeriodJpaDemo> findByProductName(){return salesPeriodRepository.findByProductName("bike");}
}
