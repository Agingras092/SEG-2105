package com.example.productmanager;

public class Product {
    private int prodID;
    private String prodName;
    private double prodPrice;

    // Constructors
    public Product() {
    }

    public Product(int prodID, String prodName, double prodPrice) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    public Product(String prodName, double prodPrice) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

    // Getters and setters
    public void setProdID(int prodID) {this.prodID = prodID;}
    public int getProdId() {return prodID;}
    public void setProdName(String prodName) {this.prodName = prodName;}
    public String getProdName() {return prodName;}
    public void setProdPrice(double prodPrice) {this.prodPrice = prodPrice;}
    public double getProdPrice() {return this.prodPrice;}

}
