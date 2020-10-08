package ru.netology.manager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import java.util.Objects;

public class ProductManager {

    private ProductRepository repository;


    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }
//    }

    public void add(Product item) {
        repository.save(item);
    }


    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }

        }
        return result;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProductManager)) return false;
//        ProductManager that = (ProductManager) o;
//        return repository.equals(that.repository);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(repository);
//    }

    public boolean matches(Product product, String search) {
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getAuthor().equalsIgnoreCase(search)) {
                return true;
            }
            if (book.getName().equalsIgnoreCase(search)) {
                return true;
            }
        }
        if (product instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) product;
            if (smartphone.getName().equalsIgnoreCase(search)) {
                return true;
            }
            if (smartphone.getManufacturer().equalsIgnoreCase(search)) {
                return true;
            }
        }
        return false;
    }

}

