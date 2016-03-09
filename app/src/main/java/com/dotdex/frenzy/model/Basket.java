package com.dotdex.frenzy.model;/**
 * Created by DABBY(3pleMinds) on 07-Mar-16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * DABBY(3pleMinds) 07-Mar-16 10:16 PM 2016 03
 * 07 22 16 Frenzy
 **/
public class Basket implements Parcelable {
    private String basketId;
    private List<Order> orders;

    public Basket()
    {

        orders = new ArrayList<>();
    }

    protected Basket(Parcel in) {
        basketId = in.readString();
        orders = in.createTypedArrayList(Order.CREATOR);
    }

    public static final Creator<Basket> CREATOR = new Creator<Basket>() {
        @Override
        public Basket createFromParcel(Parcel in) {
            return new Basket(in);
        }

        @Override
        public Basket[] newArray(int size) {
            return new Basket[size];
        }
    };

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(basketId);
        parcel.writeTypedList(orders);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public double getBasketTotal()
    {
        double p  = 0.0;
        for (Order o:orders)
        {
            p+=o.getOrderTotalPrice();
        }

        //include vat


        return p;
    }

    public double getSubTotal() {
        double p = 0.0;
        for (Order o : orders) {
            p += o.getOrderTotalPrice();
        }

        return p;
    }

    public double getServiceCharge() {
        return 0.00;
    }

    public double getDeliveryCharge() {
        return 0.00;
    }

    public double getVat() {
        return 0.00;
    }
}
