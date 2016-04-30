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
    private double total;
    private double subTotal;
    private double serviceCharge;
    private double deliveryCharge;
    private double vat;

    //delivery details
    private int deliveryType,paymentType;
    private String contact,address,userName,userEmail,profileImageUrl,orderNameString,
            orderAmountString, basketBreakDown;


    private final double VAT = 0.0;
    private final double S_CHARGE_RATIO = 0.0;
    private final double D_CHARGE_RATIO = 50.0;

    public Basket()
    {

    }

    public Basket(String basketId)
    {
        this.orders = new ArrayList<>();
        this.basketId = basketId;
        this.total = 0.0;
        this.subTotal = 0.0;
        this.serviceCharge = 0.0;
        this.deliveryCharge = 0.0;
        this.vat = 0.0;
        this.deliveryType = -1;
        this.paymentType = -1;
        this.contact = "";
        this.address = "";
    }


    protected Basket(Parcel in) {
        basketId = in.readString();
        orders = in.createTypedArrayList(Order.CREATOR);
        total = in.readDouble();
        subTotal = in.readDouble();
        serviceCharge = in.readDouble();
        deliveryCharge = in.readDouble();
        vat = in.readDouble();
        deliveryType = in.readInt();
        paymentType = in.readInt();
        contact = in.readString();
        address = in.readString();
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

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public boolean calculateBasket()
    {
        this.subTotal = getBasketSubTotal();
        this.serviceCharge = subTotal*S_CHARGE_RATIO;
        this.deliveryCharge = getAllCount()*D_CHARGE_RATIO;
        this.vat = subTotal*VAT;
        this.total = getBasketTotal();

        orderNameString  = "";
        orderAmountString = "";
        for (Order o : orders) {
            orderNameString += o.getOrderQty()+"x"+o.getOrderName()+" @ "+o.getOrderUnitPrice()+"\n";
            orderAmountString += "₦ " + o.getOrderTotalPrice() + "\n";
        }

        basketBreakDown = "\n ₦ " + getSubTotal() + "\n₦ " + getServiceCharge() + "\n₦ " + getVat() + "\n₦ " + getDeliveryCharge() + "\n";
        return true;

    }

    private double getBasketTotal()
    {
        double p  = 0.0;
        for (Order o:orders)
        {
            p+=o.getOrderTotalPrice();
        }

        //include vat... adding vat
        p+=this.vat;
        //include deliveryCharge and serviceCharge
        p+=this.serviceCharge;
        p+=this.deliveryCharge;
        return p;
    }

    private double getBasketSubTotal()
    {
        double p = 0.0;
        for (Order o : orders) {
            p += o.getOrderTotalPrice();
        }
        return p;
    }



    public int getAllCount() {
        int p = 0;
        for (Order o : orders) {
            p += o.getOrderQty();
        }

        return p;
    }




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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }


    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(basketId);
        parcel.writeTypedList(orders);
        parcel.writeDouble(total);
        parcel.writeDouble(subTotal);
        parcel.writeDouble(serviceCharge);
        parcel.writeDouble(deliveryCharge);
        parcel.writeDouble(vat);
        parcel.writeInt(deliveryType);
        parcel.writeInt(paymentType);
        parcel.writeString(contact);
        parcel.writeString(address);
    }


    public String getOrderNameString() {


        return orderNameString;
    }

    public String getOrderAmountString() {

        return orderAmountString;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setOrderNameString(String orderNameString) {
        this.orderNameString = orderNameString;
    }

    public void setOrderAmountString(String orderAmountString) {
        this.orderAmountString = orderAmountString;
    }

    public String getBasketBreakDown() {
        return basketBreakDown;
    }

    public void setBasketBreakDown(String basketBreakDown) {
        this.basketBreakDown = basketBreakDown;
    }

    public void empty() {
        orders.clear();
    }
}
