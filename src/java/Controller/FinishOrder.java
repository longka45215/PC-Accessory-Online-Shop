/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAO;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;

/**
 *
 * @author DELL
 */
public class FinishOrder extends HttpServlet {

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
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userlogin");
        Orders order = (Orders) session.getAttribute("order");
        ArrayList<OrderPurchase> cart = (ArrayList<OrderPurchase>) session.getAttribute("cart");
        int total = (int) session.getAttribute("total");
        String pay = request.getParameter("pay");        
        order.setPayMethod(pay);
        order.setTotalPrice(total - total * 10 / 100);
        DAO dao = new DAO();
        dao.insertOrders(order);
        int id = dao.getLastOrderPurchase().getId()+1;       
        for (OrderPurchase o : cart) {
            dao.insertOrderPurchase(id, o.getOrderId(), o.getProduct(), o.getQuantity());
            id++;
        }              
        total = 0;
        ArrayList<OrderPurchase> listCartP = new ArrayList<>();  
        order = new Orders(dao.getLastOrders().getId() + 1, user.getId(), "", "", 0);
        session.setAttribute("order", order);
        session.setAttribute("cart", listCartP);
        session.setAttribute("total", total);
        response.sendRedirect("HomePageController");
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
