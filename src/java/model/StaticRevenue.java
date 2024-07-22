/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.LodgingHousesDAO;
import model.LodgingHouse;

/**
 *
 * @author ASUS
 */
public class StaticRevenue {
    private int lodgingHouseId;
    private int year;
    private int month;
    private double revenue;

    public StaticRevenue(int lodgingHouseId, int year, int month, double revenue) {
        this.lodgingHouseId = lodgingHouseId;
        this.year = year;
        this.month = month;
        this.revenue = revenue;
    }

    public StaticRevenue() {
    }
    
    public StaticRevenue(int lodgingHouseId, int year, double revenue) {
        this.lodgingHouseId = lodgingHouseId;
        this.year = year;
        this.revenue = revenue;
    }

    public int getLodgingHouseId() {
        return lodgingHouseId;
    }

    public void setLodgingHouseId(int lodgingHouseId) {
        this.lodgingHouseId = lodgingHouseId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    public LodgingHouse getLodgingHouse(){
        LodgingHousesDAO lDao = new LodgingHousesDAO();
        return lDao.getLodgingHouseById(lodgingHouseId);
    }

    @Override
    public String toString() {
        return "StaticRevenue{" + "lodgingHouseId=" + lodgingHouseId + ", year=" + year + ", month=" + month + ", revenue=" + revenue + '}';
    }
    
}
