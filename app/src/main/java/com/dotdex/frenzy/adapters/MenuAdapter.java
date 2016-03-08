package com.dotdex.frenzy.adapters;/**
 * Created by DABBY(3pleMinds) on 05-Mar-16.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dotdex.frenzy.R;
import com.dotdex.frenzy.model.Menu;

import java.util.ArrayList;

/**
 * DABBY(3pleMinds) 05-Mar-16 8:58 PM 2016 03
 * 05 20 58 Frenzy
 **/
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private final Context context;
    private final ArrayList<Menu> menuList;
    private final MenuItemInteractionListener mListener;

    public interface MenuItemInteractionListener {

        void moreBtnClicked(int position, int menuId);
    }

    public MenuAdapter(Context context, ArrayList<Menu> menusList)
    {
        this.context = context;
        this.menuList = menusList;
        mListener = ((MenuItemInteractionListener) getContext());
    }


    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //get the menu Inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());

        //get the view
        View view = inflater.inflate(R.layout.menu_item,parent,false);

        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, final int position) {
        //bind the views
        holder.menuName.setText(menuList.get(position).getMenuName());
        holder.menuImage.setImageDrawable(ContextCompat.getDrawable(getContext(),menuList.get(position).getDrawableId()));
        holder.menuDesc.setText(menuList.get(position).getMenuDescription());
        holder.menuPrice.setText(String.format(getContext().getString(R.string.format_naira), menuList.get(position).getMenuPrice()));
        holder.menuQtyCount.setText(menuList.get(position).getCurrentPrice());

        holder.ratingBar.setRating((float) 2.5);
        holder.commentText.setText("0");
        holder.ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 07-Mar-16 Show the review dialog
            }
        });

        holder.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 07-Mar-16 Show the review dialog
            }
        });

        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.moreBtnClicked(position,menuList.get(position).getMenuId());
            }
        });



    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public Context getContext() {
        return context;
    }

    public void updateMenu(Menu menu, int pos)
    {
        menuList.add(pos,menu);
        notifyItemChanged(pos);
    }

    class MenuHolder extends RecyclerView.ViewHolder{

        private final RatingBar ratingBar;
        private final ImageButton reviewBtn;
        private final TextView commentText;
        private final TextView menuName;
        private final ImageView menuImage;
        private final TextView menuDesc;
        private final TextView menuPrice;
        private final TextView menuQtyCount;
        private final ImageButton moreBtn;

        public MenuHolder(View itemView) {
            super(itemView);
            //get the view and change things
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            reviewBtn = (ImageButton) itemView.findViewById(R.id.review_btn);
            commentText = (TextView) itemView.findViewById(R.id.review_count_tv);
            menuName = (TextView) itemView.findViewById(R.id.menu_name_tv);
            menuImage = (ImageView) itemView.findViewById(R.id.menu_image);
            menuDesc = (TextView) itemView.findViewById(R.id.menu_description_tv);
            menuPrice = (TextView) itemView.findViewById(R.id.amount_tv);
            menuQtyCount = (TextView) itemView.findViewById(R.id.qty_count_tv);
            moreBtn = (ImageButton) itemView.findViewById(R.id.menu_overflow);
        }
    }
}
