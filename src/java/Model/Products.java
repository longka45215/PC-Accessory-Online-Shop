/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DELL
 */
public class Products {
    private int id;
    private String name;
    private int price;
    private int categoryId;
    private boolean stocking;
    private String image;
    private String description;

    public Products() {
    }

    public Products(int id, String name, int price, int categoryId, boolean stocking, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.stocking = stocking;
        this.image = image;
        this.description = description;
    }

   
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public boolean isStocking() {
        return stocking;
    }

    public void setStocking(boolean stocking) {
        this.stocking = stocking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

   

    
    
    
}
