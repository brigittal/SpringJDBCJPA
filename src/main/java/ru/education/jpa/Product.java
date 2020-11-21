package ru.education.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //указываем что данный класс является сущностью
@Table (name = "product") //указываем таблицу
@NoArgsConstructor
@Getter
@Setter
public class Product {
    // поля класса
    @Id //показываем что это id и первичный ключ
    @Column (name = "id", nullable = false) //показываем наименование колонки и
    // первичный ключ не может быть пустым
    private Integer id;

    @Column(name = "name")
    private String name;

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //Все далее можем заменить @NoArgsConstructor, @Getter, @Setter
    //пустой конструктор для джейсон который по дефолту будет происходить
    /*public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
