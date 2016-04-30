package com.dotdex.frenzy.data.model;/**
 * Created by DABBY(3pleMinds) on 09-Mar-16.
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * DABBY(3pleMinds) 09-Mar-16 1:44 PM 2016 03
 * 09 13 44 Frenzy
 **/
@Table(name = "Address")
public class Address extends Model {
    @Column(name = "address")
    public String address;
    @Column(name = "phone")
    public String phone;
    @Column(name = "date")
    public long date;

    public Address()
    {
        super();
    }

}
