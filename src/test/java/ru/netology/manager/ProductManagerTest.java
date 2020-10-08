package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductManagerTest {
    private Product product = new Product(1,"Rus",100);
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product first = new Book(1, "Оно", 100, "Рус");
    private Product second = new Smartphone(2, "айфон", 1000, "апл");
    private Product third = new Smartphone(3, "sam", 500, "китай");
    private Product forth = new Book(4, "Валя", 100, "Галя");


    @Test
    void searchByBookName() {
        manager.add(first);
        Product[] actual = manager.searchBy("Оно");
        Product[] expected = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneName() {
        manager.add(second);
        Product[] actual = manager.searchBy("айфон");
        Product[] expected = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookAuthor() {
        manager.add(forth);
        Product[] actual = manager.searchBy("Галя");
        Product[] expected = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneManufacturer() {
        manager.add(third);
        Product[] actual = manager.searchBy("китай");
        Product[] expected = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchNotMatch() {
        manager.add(second);
        Product[] actual = manager.searchBy("ruz");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }


    @Test
    void returnFalseBook() {
//        manager.add(first);

        boolean actual = product.matches("Оно");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    void returnFalseSmartphone() {
        manager.add(second);

        boolean actual = product.matches("айфон");
        boolean expected = false;
        assertEquals(expected, actual);
    }
}