package com.dotdex.frenzy.util;/**
 * Created by DABBY(3pleMinds) on 07-Mar-16.
 */

import com.dotdex.frenzy.R;
import com.dotdex.frenzy.model.Menu;

/**
 * DABBY(3pleMinds) 07-Mar-16 12:17 PM 2016 03
 * 07 12 17 Frenzy
 **/
public class MenuBuilder {
    public static Menu build(int menuId)
    {
        switch (menuId)
        {
            case 101:
                return new Menu(101, "Fried Rice", "One Plate of fried Rice, well garnished and super delicious....sure you will bite your fingers.",
                        R.drawable.f_rice, 200);

            case 102:
                return new Menu(102, "Jollof Rice ", "One Plate of Jealoff Rice, from the best cook ever, your neighbours will salivate when they percieve the aroma. ",
                        R.drawable.jollof, 200);

            case 103:
                return new Menu(103, "Egusi Soup", "One Plate of Egusi Soup with one Sant. Trust me.. you can always order more santa to your need.",
                        R.drawable.egusi_soup, 200);

            case 104:
                return new Menu(104, "Ogbono Soup", "One Plate of Ogbono soup with Santa. You can always order more santa.. i trust you.",
                        R.drawable.ogbono_soup, 200);

            case 105:
                return new Menu(105, "Frenzy Moi Moi", "One Measure of moi super delicious and carefully prepared and well packed",
                        R.drawable.moi_moi, 50);

            case 106:
                return new Menu(106, "Frenzy Salad", "A measure of Frenzy delicious Salad. You can enjoy your Rice with frenzy salad at a cheap price",
                        R.drawable.salad, 50);

            case 107:
                return new Menu(107, "Frenzy Fish", "One Slice of fish for your frenzy order. Well fried and prepared for u. Please don't eat without",
                        R.drawable.fish, 50);

            case 108:
                return new Menu(108, "Frenzy Meat", "One slice of Frenzy meat to supplement your choice of fish still at affordable price.",
                        R.drawable.beaf, 50);

            case 109:
                return new Menu( 109,"Coke Beverages", "Enjoy your food with coke and don't forget to share with friends. Choose from Fanta, Coke, Sprite,Sweeps...",
                    R.drawable.coke, 70);

            case 201:
                return new Menu(201, "Bottled Water (1 Le)", "Water is important so make your choice of bottled water.",
                        R.drawable.water, 200);
            default:
                return null;

        }
    }
}
