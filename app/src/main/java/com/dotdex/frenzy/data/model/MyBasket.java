package com.dotdex.frenzy.data.model;/**
 * Created by DABBY(3pleMinds) on 12-Mar-16.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.dotdex.frenzy.model.Basket;
import com.dotdex.frenzy.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * DABBY(3pleMinds) 12-Mar-16 9:08 PM 2016 03
 * 12 21 08 Frenzy
 **/
@Table(name = "Baskets")
public class MyBasket extends Model {

    @Column
    public String basketId;
    @Column
    public double total;
    @Column
    public double subTotal;
    @Column
    public double serviceCharge;
    @Column
    public double deliveryCharge;
    @Column
    public double vat;
    @Column
    public int deliveryType;
    @Column
    public int paymentType;
    @Column
    public String contact;
    @Column
    public String address;
    @Column
    public String userName;
    @Column
    public String userEmail;
    @Column
    public String profileImageUrl;
    @Column
    public String orderNameString;
    @Column
    public String orderAmountString;
    @Column
    public String basketBreakDown;

    private List<MyOrder> orders;

    public MyBasket() {
        super();
    }



    public List<MyOrder> orders()
    {
        return getMany(MyOrder.class,"basket");
    }


    public static MyBasket fromBasket(Basket basket)
    {
        MyBasket myBasket = new MyBasket();
        myBasket.basketId = basket.getBasketId();
        myBasket.total = basket.getTotal();
        myBasket.subTotal = basket.getSubTotal();
        myBasket.address = basket.getAddress();
        myBasket.contact = basket.getAddress();
        myBasket.deliveryCharge = basket.getDeliveryCharge();
        myBasket.deliveryType = basket.getDeliveryType();
        myBasket.orderAmountString = basket.getOrderAmountString();
        myBasket.orderNameString = basket.getOrderNameString();
        myBasket.basketBreakDown = basket.getBasketBreakDown();
        myBasket.paymentType = basket.getPaymentType();
        myBasket.profileImageUrl = basket.getProfileImageUrl();
        myBasket.userEmail = basket.getUserEmail();
        myBasket.userName = basket.getUserName();
        myBasket.serviceCharge = basket.getServiceCharge();
        myBasket.vat = basket.getVat();

        List<MyOrder> orders = new ArrayList<>();
        for (Order order: basket.getOrders())
        {
            MyOrder od = new MyOrder();
            od.menuId = order.getMenuId();
            od.orderDescription = order.getOrderDescription();
            od.orderId = order.getOrderId();
            od.orderQty = order.getOrderQty();
            od.orderName = order.getOrderName();
            od.orderUnitPrice = order.getOrderUnitPrice();
            od.orderTotalPrice = order.getOrderTotalPrice();
            od.basket = myBasket;
            orders.add(od);

        }

        myBasket.setOrders(orders);

        return myBasket;
    }

    public List<MyOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MyOrder> orders) {
        this.orders = orders;
    }
}
