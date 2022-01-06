package controller;

import model.Product;
import service.CategoryService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createForm(request, response);
                break;
            case "edit":
                editForm(request, response);
                break;
            case "delete":
                deleteForm(request, response);
            default:
                showList(request, response);
                break;
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("productList", productService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/display.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        request.setAttribute("categories", CategoryService.getAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/edit.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createForm(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categories", CategoryService.getAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/create.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product student = productService.findById(id);
        req.setAttribute("product", student);
        req.setAttribute("categories", CategoryService.getAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/delete.jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "delete":
                deleteStudent(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
            default:
                break;
        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) {

        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String description = req.getParameter("description");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        Product product = new Product(name, price, quantity, color, description, idCategory);

        productService.add(product);
        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String description = req.getParameter("description");
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));

        Product product = new Product(name, price, quantity, color, description, idCategory);
        productService.edit(product, id);
        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {
        String searchName = request.getParameter("search");
        List<Product> productList = productService.search(searchName);
        request.setAttribute("productList", productList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/search.jsp");
        request.setAttribute("categories", CategoryService.getAll());
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
