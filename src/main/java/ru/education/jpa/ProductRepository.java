package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //обозначаем компонентой спринга
//наследуется от дефолтной реализации репозитория,
//указываем класс, тип ключа(Integer потому что у нас тип ключа в классе Product такой
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
