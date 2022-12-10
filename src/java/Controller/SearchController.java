/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.DAO;
import Model.Category;
import Model.Products;
import java.io.IOException;
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
public class SearchController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String searchValue = request.getParameter("search");
        String sort = request.getParameter("sort");
        String numPerPage = request.getParameter("num");
        int num = Integer.parseInt(numPerPage);
        DAO dao = new DAO();
        ArrayList<Category> categorylist = dao.getCategoryList();
        Products last = dao.getLastProduct();
        if (searchValue == null) {
            searchValue = (String) session.getAttribute("search");
        }
        ArrayList<Products> productlist = dao.getProductByName(searchValue,dao.getProductsList());
        
        int count = productlist.size();
        int endP = (count % num == 0 ? count / num : (count / num) + 1);
        String index = request.getParameter("page");
        if (request.getParameter("page") == null) {
            index = "1";
        }
        int page = Integer.parseInt(index);   
        dao.sortList(sort, productlist);
        ArrayList<Products> list = dao.getPagingProductsList(productlist, page, num);     
        session.setAttribute("search", searchValue);
        request.setAttribute("plist", list);
        request.setAttribute("clist", categorylist);
        request.setAttribute("last", last);
        request.setAttribute("valueS", searchValue);
        request.setAttribute("currentSort", sort);
        request.setAttribute("servlet", request.getRequestURI());
        session.setAttribute("currentNum", num);
        request.setAttribute("currentPage", page);
        request.setAttribute("endP", endP);
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

}
