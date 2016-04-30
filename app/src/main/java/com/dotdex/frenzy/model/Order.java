package com.dotdex.frenzy.model;/**
 * Created by DABBY(3pleMinds) on 07-Mar-16.
 */

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.dotdex.frenzy.R;

/**
 * DABBY(3pleMinds) 07-Mar-16 12:51 PM 2016 03
 * 07 12 51 Frenzy
 **/
public class Order implements Parcelable {
    private int orderId;
    private  int menuId;
    private String orderName;
    private String orderDescription;
    private double orderUnitPrice;
    private int orderQty;
    private double orderTotalPrice;

    public Order()
    {

    }

    protected Order(Parcel in) {
        orderId = in.readInt();
        menuId = in.readInt();
        orderName = in.readString();
        orderDescription = in.readString();
        orderUnitPrice = in.readDouble();
        orderQty = in.readInt();
        orderTotalPrice = in.readDouble();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(orderId);
        parcel.writeInt(menuId);
        parcel.writeString(orderName);
        parcel.writeString(orderDescription);
        parcel.writeDouble(orderUnitPrice);
        parcel.writeInt(orderQty);
        parcel.writeDouble(orderTotalPrice);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public double getOrderUnitPrice() {
        return orderUnitPrice;
    }

    public void setOrderUnitPrice(double orderUnitPrice) {
        this.orderUnitPrice = orderUnitPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public static Order fromMenu(Menu menu,int qty,double totalPrice)
    {
        Order order = new Order();
        order.setMenuId(menu.getMenuId());
        order.setOrderName(menu.getMenuName());
        order.setOrderDescription(menu.getMenuDescription());
        order.setOrderUnitPrice(menu.getMenuPrice());
        order.setOrderQty(qty);
        order.setOrderTotalPrice(totalPrice);


        return order;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String toFormattedString(Context context) {

        return String.format(context.getString(R.string.format_order_string),getOrderQty(),getOrderName(),getOrderUnitPrice());

    }

    public String totalToString(Context context)
    {
        return String.format(context.getString(R.string.format_naira),getOrderTotalPrice());
    }
}
