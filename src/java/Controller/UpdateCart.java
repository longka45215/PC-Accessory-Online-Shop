/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAO;
import Model.OrderPurchase;
import Model.Products;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateCart extends HttpServlet {

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
        DAO dao = new DAO();
        if (request.getParameter("type").equals("0")) {
            String pid = request.getParameter("pid");
            Products p = dao.getProductById(Integer.parseInt(pid));
            request.setAttribute("product", p);
            request.getRequestDispatcher("Views/UpdateCart.jsp").forward(request, response);
        } else if (request.getParameter("type").equals("3")) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int id = Integer.parseInt(request.getParameter("id"));            
            HttpSession session = request.getSession();
            ArrayList<OrderPurchase> cart = (ArrayList<OrderPurchase>) session.getAttribute("cart");
            int total = (int) session.getAttribute("total");           
            for (OrderPurchase o : cart) {
                if (o.getProduct().getId() == id) {
                    if(quantity>o.getQuantity()){
                        total+=(quantity-o.getQuantity())*o.getProduct().getPrice();
                    }
                    if(quantity<o.getQuantity()){
                        total-=(o.getQuantity()-quantity)*o.getProduct().getPrice();
                    }
                   o.setQuantity(quantity);
                }
            }            
            session.setAttribute("total", total);
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
        } else if (request.getParameter("type").equals("2")) {
            String pid = request.getParameter("pid");
            int id = Integer.parseInt(pid);
            HttpSession session = request.getSession();
            ArrayList<OrderPurchase> cart = (ArrayList<OrderPurchase>) session.getAttribute("cart");
            OrderPurchase remove = new OrderPurchase();
            for (OrderPurchase o : cart) {
                if (o.getProduct().getId() == id) {
                    remove = o;
                }
            }
            cart.remove(remove);
            int total = (int) session.getAttribute("total");
            total -= remove.getProduct().getPrice() * remove.getQuantity();
            session.setAttribute("total", total);
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
        } else if (request.getParameter("type").equals("1")) {
            request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
        }

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
