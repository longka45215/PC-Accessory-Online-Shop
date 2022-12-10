/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author DELL
 */
public class DAO {

    private String status;
    private Connection con;
    private ArrayList<Users> usersList;
    private ArrayList<Products> productsList;
    private ArrayList<Orders> ordersList;
    private ArrayList<Category> categoryList;
    private ArrayList<OrderPurchase> orderPurchaseList;
    private ArrayList<UserAddress> userAddressList;

    public DAO() {
        try {
            con = new DBContext().getConnection();
            loadCategorys();
            loadOrders();
            loadProducts();
            loadUsers();
            loadOrderPurchase();
            loadUserAddress();
        } catch (Exception ex) {
            status = "Error!!" + ex.getMessage();
        }
    }

    public ArrayList<Users> getUsersList() {
        return usersList;
    }

    public ArrayList<Products> getProductsList() {
        return productsList;
    }

    public ArrayList<Orders> getOrdersList() {
        return ordersList;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public ArrayList<OrderPurchase> getOrderPurchaseList() {
        return orderPurchaseList;
    }

    public ArrayList<UserAddress> getUserAddressList() {
        return userAddressList;
    }

    public final void loadUsers() {
        usersList = new ArrayList<>();
        String sql = "select * from Users_HE161192 order by id asc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String pass = rs.getString(3).trim();
                String fullname = rs.getString(4);
                int age = rs.getInt(5);
                boolean gender = rs.getBoolean(6);
                String email = rs.getString(7);
                String phone = rs.getString(8);
                boolean add = rs.getBoolean(9);
                int authority = rs.getInt(10);
                usersList.add(new Users(id, username, pass, fullname, age, gender, email, phone, add, authority));

            }
        } catch (SQLException e) {
            status = "Erro Load Users" + e.getMessage();
        }
    }

    public void insertUser(int id, String username, String pass, String fullname,
            int age, boolean gender, String email, String phone, boolean active, int authority) {
        String sql = "insert into Users_HE161192 values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setString(4, fullname);
            ps.setInt(5, age);
            ps.setBoolean(6, gender);
            ps.setString(7, email);
            ps.setString(8, phone);
            ps.setBoolean(9, active);
            ps.setInt(10, authority);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Insert" + e.getMessage();
        }
    }

    public void updateUsers(int id, String fullname, int age, boolean gender, String email,
            String phone, boolean active, int authority) {
        String sql = "Update Users_HE161192 set fullname = ?, age = ?, gender = ?,email =?,phone = ?,active = ?,authority = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(8, id);
            ps.setString(1, fullname);
            ps.setInt(2, age);
            ps.setBoolean(3, gender);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setBoolean(6, active);
            ps.setInt(7, authority);
            ps.execute();

        } catch (SQLException e) {
            status = "Error Update" + e.getMessage();
        }
    }

    public void delUsers(int id) {
        String sql = "delete from Users_HE161192 where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Delete" + e.getMessage();
        }
    }

    public final void loadProducts() {
        productsList = new ArrayList<>();
        String sql = "select * from Products_HE161192 order by id asc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                boolean stocking = rs.getBoolean(5);
                String image = rs.getString(6);
                String description = rs.getString(7);
                productsList.add(new Products(id, name, price, categoryId, stocking, image, description));
            }
        } catch (SQLException e) {
            status = "Erro Load Products" + e.getMessage();
        }
    }

    public void insertProducts(int id, String name, int price, int categoryId, boolean stocking, String image, String description) {
        String sql = "insert into Products_HE161192 values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setInt(4, categoryId);
            ps.setBoolean(5, stocking);
            ps.setString(6, image);
            ps.setString(7, description);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Insert Products" + e.getMessage();
        }
    }

    public void updateProducts(int id, String name, int price, int categoryId, boolean stocking, String image, String description) {
        String sql = "Update Products_HE161192 set name = ?, price = ?, categoryId = ?, stocking = ?, image = ?,description = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(7, id);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, categoryId);
            ps.setBoolean(4, stocking);
            ps.setString(5, image);
            ps.setString(6, description);
            ps.execute();

        } catch (SQLException e) {
            status = "Error Update Products" + e.getMessage();
        }
    }

    public void delProducts(int id) {
        String sql = "delete from Products_HE161192 where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Delete Product" + e.getMessage();
        }
    }

    public final void loadOrders() {
        ordersList = new ArrayList<>();
        String sql = "select * from Orders_HE161192 order by id asc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(2);

                String payMethod = rs.getString(3);
                String orderDate = rs.getString(4);
                float totalPrice = rs.getFloat(5);
                ordersList.add(new Orders(id, userId, payMethod, orderDate, totalPrice));

            }
        } catch (SQLException e) {
            status = "Error Load Orders" + e.getMessage();
        }
    }

    public void insertOrders(Orders order) {
        String sql = "insert into Orders_HE161192 values (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.getId());
            ps.setInt(2, order.getUserId());
            ps.setString(3, order.getPayMethod());
            ps.setString(4, order.getOrderDate());
            ps.setFloat(5, order.getTotalPrice());
            ps.execute();
        } catch (SQLException e) {
            status = "Error Insert Orders" + e.getMessage();
        }
    }

    public final void loadCategorys() {
        categoryList = new ArrayList<>();
        String sql = "select * from Categorys_HE161192 order by id asc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                categoryList.add(new Category(id, name));
            }
        } catch (SQLException e) {
            status = "Erro Load Category" + e.getMessage();
        }
    }

    public void insertCategorys(int id, String name) {
        String sql = "insert into Categorys_HE161192 values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Insert Categorys" + e.getMessage();
        }
    }

    public void updateCategorys(int id, String name) {
        String sql = "Update Categorys_HE161192 set name = ? where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(2, id);
            ps.setString(1, name);
            ps.execute();

        } catch (SQLException e) {
            status = "Error Update Products" + e.getMessage();
        }
    }

    public void delCategorys(int id) {
        String sql = "delete from Categorys_HE161192 where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Delete Categorys" + e.getMessage();
        }
    }

    public final void loadOrderPurchase() {
        orderPurchaseList = new ArrayList<>();
        String sql = "select * from OrderPurchase_HE161192 order by id asc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int orderId = rs.getInt(2);
                Products product = getProductById(rs.getInt(3));
                int quantity = rs.getInt(4);
                orderPurchaseList.add(new OrderPurchase(id, orderId, product, quantity));
            }
        } catch (SQLException e) {
            status = "Error Load Category" + e.getMessage();
        }
    }

    public void insertOrderPurchase(int id, int orderId, Products productId, int quantity) {
        String sql = "insert into OrderPurchase_HE161192 values (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, orderId);
            ps.setInt(3, productId.getId());
            ps.setInt(4, quantity);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Insert Categorys" + e.getMessage();
        }
    }

    public final void loadUserAddress() {
        userAddressList = new ArrayList<>();
        String sql = "select * from UserAddress_HE161192 order by id asc";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                String address = rs.getString(3);
                userAddressList.add(new UserAddress(id, userId, address));
            }
        } catch (SQLException e) {
            status = "Error Load Category" + e.getMessage();
        }
    }
    public void insertAddress(int userId, String address){
        String sql = "insert into UserAddress_HE161192 values (?,?,?)";
        int id = userAddressList.size();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, userId);
            ps.setString(3, address);
            ps.execute();
        } catch (SQLException e) {
            status = "Error Insert Categorys" + e.getMessage();
        }
    }

//------------------------------------------------------------------------------------------------------------------    
    public Products getLastProduct() {
        try {          
            int max = 0;           
            for (Products p : productsList) {
                if(p.getId()>max){
                    max = p.getId();
                   
                }
            }
            Products pr = getProductById(max);
            return pr;

        } catch (Exception e) {
            status = "Erro Load Products" + e.getMessage();
        }
        return null;
    }

    public Orders getLastOrders() {
        try {
            int max = 0;           
            for (Orders orders : ordersList) {
                if(orders.getId()>max){
                    max = orders.getId();
                }
            }
            Orders o = null;
            for (Orders orders : ordersList) {
                if(orders.getId()==max){
                    o=orders;
                }
            }
            
            return o;
        } catch (Exception e) {
            status = "Error Load Orders" + e.getMessage();
        }
        return null;
    }

    public OrderPurchase getLastOrderPurchase() {

        try {
            int max = 0;           
            for (OrderPurchase o : orderPurchaseList) {
                if(o.getId()>max){
                    max = o.getId();
                }
            }
            OrderPurchase o1 = null;
            for (OrderPurchase o : orderPurchaseList) {
                if(o.getId()==max){
                    o1=o;
                }
            }           
            return o1;
        } catch (Exception e) {
            status = "Error Load Orders" + e.getMessage();
        }
        return null;
    }

    public ArrayList<Products> getProductByCategoryId(int cid,ArrayList<Products>list) {
        ArrayList<Products> pList = new ArrayList<>();
        try {
            for (Products p : list) {
                if (p.getCategoryId() == cid) {
                    pList.add(p);
                }
            }
            return pList;
        } catch (Exception e) {
            status = "Erro Load Products" + e.getMessage();
        }
        return null;
    }

    public Products getProductById(int pid) {
        try {
            for (Products products : productsList) {
                if (products.getId() == pid) {
                    return products;
                }
            }
        } catch (Exception e) {
            status = "Erro Load Products" + e.getMessage();
        }
        return null;
    }

    public ArrayList<Products> getProductByName(String pname,ArrayList<Products> list) {
        ArrayList<Products> pList = new ArrayList<>();
        
        try {
            for (Products p : list) {
                if (p.getName().toLowerCase().contains(pname.toLowerCase())) {
                    pList.add(p);
                }
            }
            return pList;
        } catch (Exception e) {
            status = "Erro Load Products" + e.getMessage();
        }
        return null;
    }
   
    public Users login(String uname, String password) {

        try {
            for (Users users : usersList) {
                if (users.getPass().equals(password) && users.getUsername().equals(uname)) {
                    return users;
                }
            }
        } catch (Exception e) {
            status = "Erro Load Users" + e.getMessage();
        }
        return null;
    }

    public ArrayList<Products> getPagingProductsList(ArrayList<Products> list,int page, int index) {
        ArrayList<Products> paging = new ArrayList<>();   
        int start, end;
        start = (page - 1) * index;
        end = Math.min(index * page, list.size());
        for (int i = start; i < end; i++) {
            paging.add(list.get(i));
        }
        return paging;
    }
    public void sortList(String sort, ArrayList<Products> list){
        if(sort==null){
            sort="cunhat";
        }
        if(sort.equals("cunhat")){
            
        }else if(sort.equals("moinhat")){
            Collections.reverse(list);
        }else if(sort.equals("tangdan")){
            Collections.sort(list, new  Comparator<Products>() {
                @Override
                public int compare(Products o1, Products o2) {
                    return o1.getPrice()-o2.getPrice();
                }
            });
        }else if(sort.equals("giamdan")){
             Collections.sort(list, new  Comparator<Products>() {
                @Override
                public int compare(Products o1, Products o2) {
                    return o2.getPrice()-o1.getPrice();
                }
            });
        }else if(sort.equals("az")){
            Collections.sort(list, new  Comparator<Products>() {
                @Override
                public int compare(Products o1, Products o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }else if(sort.equals("za")){
            Collections.sort(list, new  Comparator<Products>() {
                @Override
                public int compare(Products o1, Products o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
        }     
    }
    public ArrayList<UserAddress> getAddressByUserId(int id){
        ArrayList<UserAddress> list = new ArrayList<>();
        for (UserAddress o : userAddressList) {
            if(o.getUserId()==id){
                list.add(o);
            }
        }
        return list;
    }

}

class test {

    public static void main(String[] args) {
        try {
            DAO dao = new DAO();
           
            for (UserAddress userAddress : dao.getAddressByUserId(0)) {
                System.out.println(userAddress.getAddress());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
