package service;

import dao.ProductDao;
import model.Product;

import java.util.List;

public class ProductService {
    private static List<Product> studentList = ProductDao.getAllProduct();


    public List<Product> findAll() {
        studentList = ProductDao.getAllProduct();
        return studentList;
    }

    public void add(Product product) {
        ProductDao.addProduct(product);
        studentList = ProductDao.getAllProduct();
    }

    public Product findById(int id) {
        return ProductDao.findById(id);
    }

    public void edit(Product product, int id) {
        ProductDao.editProduct(product, id);
    }

    public void delete(int id) {
        ProductDao.deleteProduct(id);
    }
    public List<Product> search(String name) {
        return ProductDao.searchProduct(name);
    }


//    public void add(Product product) {
//        ProductDao.addStudent(product);
//        studentList = ProductDao.getAllProduct();
//    }
//
//
//    public Product findById(int id) {
//        return ProductDao.findById(id);
//    }
//
//    public void edit(Product product, int id) {
//        ProductDao.editProduct(product, id);
//    }
//
//    public void delete(int id) {
//        ProductDao.deleteProduct(id);
//    }
//
//
//    public List<Product> search(String name) {
//        return ProductDao.searchProduct(name);
//    }
}
