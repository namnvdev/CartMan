package vn.tlu.edu.android.cartman.product.model;

import android.net.Uri;

public class Product {
    private int id;
    private String name;
    private String code;
    private float price;
    private Uri image;

    public Product(int id, String name, String code, float price, Uri image) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.image = image;
    }

    public Product (int id, String name){
        this.id = id;
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
