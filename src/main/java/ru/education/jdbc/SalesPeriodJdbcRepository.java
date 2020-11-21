package ru.education.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.education.entity.SalesPeriodJdbcDemo;
import ru.education.jpa.Product;

import java.util.List;

@Repository
public class SalesPeriodJdbcRepository {

    //будет читать все необходимые настройки базы данных из application.yml

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesPeriodJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //Методы нашего репозитория:

    //Кол-во записей в нашей таблице
    public int count(){
        //в () пишем запрос и в каком виде преобразовать результат
        return jdbcTemplate.queryForObject("select count(*) from dev_db.sales_period",Integer.class);
    }

    //Метод вычитки записей
    public List<SalesPeriodJdbcDemo> getSalesPeriods(){
        //используем RowMapper
        return jdbcTemplate.query("select * from dev_db.sales_period",(rs,rowNum) ->
                new SalesPeriodJdbcDemo(
                        rs.getLong("id"),
                        rs.getInt("price"),
                        rs.getDate("date_from"),
                        rs.getDate("date_to"),
                        rs.getInt("product")
                ));
    }

    public List<SalesPeriodJdbcDemo> getSalesPeriodsPriceIsHigher(long price){
         return jdbcTemplate.query(String.format("select * from dev_db.sales_period where price >= %s",price),
                (rs,rowNum) ->
                    new SalesPeriodJdbcDemo(
                            rs.getLong("id"),
                            rs.getInt("price"),
                            rs.getDate("date_from"),
                            rs.getDate("date_to"),
                            rs.getInt("product")
                    ));
    }

    public List<Product> getProductWhithActivePeriod(){
        return jdbcTemplate.query("select p.id product_id, p.name product_name from dev_db.product p inner join "+
                "dev_db.sales_period sp on p.id = sp.product where sp.date_to is null",
        (rs, rowNum) ->
                new Product(
                        rs.getInt ("product_id"),
                        rs.getString("product_name")
                ));

    }
}
