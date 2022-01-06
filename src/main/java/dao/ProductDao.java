package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static Connection connection = ConnectMySQL.getConnection();

    public static List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT product.*, category.name as categoryname FROM product join category on product.idcategory = category.id;");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int idcategory = resultSet.getInt("idcategory");
                String categoryName = resultSet.getString("categoryname");
                productList.add(new Product(id, name, price, quantity, color, description, idcategory, categoryName));

            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (`name`, `price`, `quantity`, `color`, `description`,`idcategory`) VALUES (?,?,?,?,?,?);");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getIdCategory());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Product findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id2 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int idcategory = resultSet.getInt("idcategory");
                return new Product(id2, name, price, quantity, color, description, idcategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void editProduct(Product product, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name = ?, price = ?, quantity = ?,color=?,description=?, idcategory=? WHERE id =? ");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getIdCategory());
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from product where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> searchProduct(String searchName) {
        ArrayList<Product> searchList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT product.*, category.name as categoryname FROM product join category on product.idcategory = category.id WHERE product.name LIKE ?");
            preparedStatement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int idcategory = resultSet.getInt("idcategory");
                String categoryName = resultSet.getString("categoryname");

                searchList.add(new Product(id, name, price, quantity, color, description, idcategory, categoryName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }
}
