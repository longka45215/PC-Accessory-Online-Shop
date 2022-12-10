/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAO;
import Model.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author DELL
 */
public class HomePageController extends HttpServlet {

    DAO dao;

    @Override
    public void init() {
        dao = new DAO();
    }

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
        dao = new DAO();
        String sort = request.getParameter("sort");
        String numPerPage = request.getParameter("num");
        if(numPerPage==null){
            numPerPage = "6";
        }
        int num = Integer.parseInt(numPerPage);
        ArrayList<Products> list = dao.getProductsList();  
        dao.sortList(sort, list);                                
        int count = dao.getProductsList().size();
        int endP = (count % num == 0 ? count / num : (count / num) + 1);
        
        String index = request.getParameter("page");
        if (request.getParameter("page") == null) {
            index = "1";
        }
        int page = Integer.parseInt(index);
        Products last = dao.getLastProduct();
        ArrayList<Products> productlist = dao.getPagingProductsList(list, page, num);
        ArrayList<Category> categorylist = dao.getCategoryList();
        request.setAttribute("servlet", request.getRequestURI());
        request.setAttribute("currentNum", num);
        request.setAttribute("currentSort", sort);
        request.setAttribute("currentPage", page);
        request.setAttribute("endP", endP);
        request.setAttribute("clist", categorylist);
        request.setAttribute("last", last);
        request.setAttribute("plist", productlist);
        request.getRequestDispatcher("Views/HomePage.jsp").forward(request, response);

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
//    public static void main(String[] args) {
//        DAO dao;
//        dao = new DAO();
//         ArrayList<Products> plist = dao.getProductsList();
//         for (Products products : plist) {
//             System.out.println(products.getName());
//        }
//    }

}
