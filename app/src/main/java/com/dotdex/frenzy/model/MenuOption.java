package com.dotdex.frenzy.model;/**
 * Created by DABBY(3pleMinds) on 12-Feb-16.
 */

/**
 * DABBY(3pleMinds) 12-Feb-16 7:54 AM 2016 02
 * 12 07 54 UnnMobile
 **/
public class MenuOption {

    private final int iconId;
    private final String title;
    private final String sub;

    public MenuOption(int iconId, String title, String sub)
    {
        this.iconId  = iconId;
        this.title = title;
        this.sub = sub;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }

    public String getSub() {
        return sub;
    }
}
