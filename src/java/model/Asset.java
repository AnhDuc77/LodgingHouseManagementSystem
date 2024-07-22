/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Asset {
    private int assetId;
    private String assetName;
    private String description;
    private String status;
    private double price;
    private int quantity;
    private int lodginghouseId;

    public Asset() {
    }

    public Asset(int assetId, String assetName, String description, String status, double price, int quantity, int lodginghouseId) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.description = description;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.lodginghouseId = lodginghouseId;
    }

    public Asset(String assetName, String description, String status, double price, int quantity, int lodginghouseId) {
        this.assetName = assetName;
        this.description = description;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.lodginghouseId = lodginghouseId;
    }
    
    public int getLodginghouseId() {
        return lodginghouseId;
    }

    public void setLodgingHouseId(int lodginghouseId) {
        this.lodginghouseId = lodginghouseId;
    }

    
    
    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
