package com.dotdex.model;/**
 * Created by DABBY(3pleMinds) on 06-Mar-16.
 */

/**
 * DABBY(3pleMinds) 06-Mar-16 10:25 PM 2016 03
 * 06 22 25 Frenzy
 **/
public class Plate {

    private int menuId;
    private String menuName;
    private  String menuDescription;
    private double menuPrice;

    @Override
    public String toString() {
        return super.toString();
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }
}
