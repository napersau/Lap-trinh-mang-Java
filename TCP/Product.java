package TCP;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 20231107;
    private String name;
    private int id;
    private double price;
    private int discount;

    

    @Override
    public String toString() {
        return "Product [name=" + name + ", id=" + id + ", price=" + price + ", discount=" + discount + "]";
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    
}
