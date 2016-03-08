package com.dotdex.frenzy.model;/**
 * Created by DABBY(3pleMinds) on 06-Mar-16.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * DABBY(3pleMinds) 06-Mar-16 10:29 PM 2016 03
 * 06 22 29 Frenzy
 **/
public class Menu implements Parcelable {

    private int menuId;
    private String menuName;
    private String menuDescription,currentPrice;
    private int drawableId;
    private double menuPrice;

    public Menu(int menuId,String menuName,String menuDescription, int drawable,double menuPrice)
    {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.currentPrice = null;
        this.menuPrice = menuPrice;
        this.drawableId = drawable;

    }

    protected Menu(Parcel in) {
        menuId = in.readInt();
        menuName = in.readString();
        menuDescription = in.readString();
        menuPrice = in.readDouble();
        drawableId = in.readInt();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

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

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(menuId);
        parcel.writeString(menuName);
        parcel.writeString(menuDescription);
        parcel.writeDouble(menuPrice);
        parcel.writeInt(drawableId);
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }
}
