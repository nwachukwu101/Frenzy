package com.dotdex.frenzy.data.model;/**
 * Created by DABBY(3pleMinds) on 12-Mar-16.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * DABBY(3pleMinds) 12-Mar-16 9:08 PM 2016 03
 * 12 21 08 Frenzy
 **/
@Table(name = "Orders")
public class MyOrder extends Model {
    @Column
    public int orderId;
    @Column
    public int menuId;
    @Column
    public String orderName;
    @Column
    public String orderDescription;
    @Column
    public double orderUnitPrice;
    @Column
    public int orderQty;
    @Column
    public double orderTotalPrice;
    @Column
    public MyBasket basket;
}
