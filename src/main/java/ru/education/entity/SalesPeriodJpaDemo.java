package ru.education.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.education.jpa.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sales_period")
@NoArgsConstructor
@Getter
@Setter
public class SalesPeriodJpaDemo {

    @Id
    @Column(name = "id", nullable = false)
    //для генерации первичного ключа мы будем пользоваться данной последовательностью (sales_period_id_seq)
    @GeneratedValue (strategy = GenerationType.IDENTITY, generator = "sales_period_id_seq") // для PostgreSQL .SEQUENCE
    //для PostgreSQL @SequenceGenerator(name = "sales_period_id_seq",sequenceName = "sales_period_id_seq", allocationSize = 1)
    private long id;

    @Column (name = "price")
    private long price;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;

    @OneToOne
    @JoinColumn (name = "product", referencedColumnName = "id", nullable = false)
    private Product product;
}
