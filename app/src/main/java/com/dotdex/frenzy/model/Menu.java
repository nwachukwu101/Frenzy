package com.dotdex.frenzy.model;/**
 * Created by DABBY(3pleMinds) on 06-Mar-16.
 */

import android.graphics.drawable.Drawable;

/**
 * DABBY(3pleMinds) 06-Mar-16 10:29 PM 2016 03
 * 06 22 29 Frenzy
 **/
public class Menu {

    private int menuId;
    private String menuName;
    private String menuDescription;
    private Drawable drawable;
    private double menuPrice;

    public Menu(int menuId,String menuName,String menuDescription, Drawable drawable,double menuPrice)
    {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.drawable = drawable;
        this.menuPrice = menuPrice;

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

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
