/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAO;
import Model.*;
import Model.Products;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int quantity = 1;
        int id = Integer.parseInt(request.getParameter("id"));
        DAO dao = new DAO();
        Products product = dao.getProductById(id);
        if (product != null) {
            if (request.getParameter("quantity") != null) {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }
            HttpSession session = request.getSession();
            if (session.getAttribute("order") == null) {               
                Users user = (Users) session.getAttribute("userlogin");
                Orders order = new Orders(dao.getLastOrders().getId() + 1, user.getId(), "", "", 0);
                ArrayList<OrderPurchase> cart = (ArrayList<OrderPurchase>) session.getAttribute("cart");
                OrderPurchase odp = new OrderPurchase(dao.getOrderPurchaseList().size(), order.getId(), product, quantity);
                cart.add(odp);

                session.setAttribute("cart", cart);
                session.setAttribute("order", order);
            } else {
                Orders order = (Orders) session.getAttribute("order");
                ArrayList<OrderPurchase> cart = (ArrayList<OrderPurchase>) session.getAttribute("cart");
                boolean check = false;
                for (OrderPurchase o : cart) {
                    if (o.getProduct().getId() == product.getId()) {
                        o.setQuantity(o.getQuantity() + quantity);
                        check = true;
                    }
                }
                if (check == false) {
                    OrderPurchase odp = new OrderPurchase(dao.getOrderPurchaseList().size(), order.getId(), product, quantity);
                    cart.add(odp);
                }
                session.setAttribute("cart", cart);
            }
            int total = (int) session.getAttribute("total");
            total += product.getPrice() * quantity;
            ArrayList<OrderPurchase> cart = (ArrayList<OrderPurchase>) session.getAttribute("cart");
            session.setAttribute("productNumber", cart.size());
            session.setAttribute("total", total);
            
        }
        request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
