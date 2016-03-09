package com.dotdex.frenzy.adapters;/**
 * Created by DABBY(3pleMinds) on 07-Mar-16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dotdex.frenzy.R;
import com.dotdex.frenzy.model.Order;

import java.util.List;

/**
 * DABBY(3pleMinds) 07-Mar-16 11:27 PM 2016 03
 * 07 23 27 Frenzy
 **/
public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.Item> {

    private final List<Order> orders;
    private Context context;
    public OrderItemsAdapter(Context context, List<Order> orders)
    {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        //get the inflater
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.order_item,parent,false);
        return new Item(view);
    }

    @Override
    public void onBindViewHolder(Item holder, int position) {

        holder.leftTv.setText(orders.get(position).toFormattedString(getContext()));
        holder.rightTv.setText(orders.get(position).totalToString(getContext()));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public Context getContext() {
        return context;
    }

    class Item extends RecyclerView.ViewHolder{

        private final TextView leftTv;
        private final TextView rightTv;

        public Item(View itemView) {
            super(itemView);

            rightTv = (TextView) itemView.findViewById(R.id.total_price);
            leftTv = (TextView) itemView.findViewById(R.id.item_count);
        }
    }
}
