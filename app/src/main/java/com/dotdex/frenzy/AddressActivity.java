package com.dotdex.frenzy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.dotdex.frenzy.model.Address;

import java.util.List;

public class AddressActivity extends AppCompatActivity implements
        NewAddressFragment.OnFragmentInteractionListener{

    private static final String ADDRESS_FRAG = "address_fragment";
    private List<Address> addressList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewAddressFragment fragment = new NewAddressFragment();
                fragment.show(getSupportFragmentManager(),ADDRESS_FRAG);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the recycler view
        RecyclerView recycler = (RecyclerView) findViewById(R.id.address_recycler);

        recycler.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        //get all the address from the date bae
        addressList = getAllAddress();

        //get the adapater
        adapter = new Adapter(addressList);

        recycler.setAdapter(adapter);

    }

    @Override
    public void onAddBtnPressed(String s) {
//        persist the string address?
        Address address = new Address();
        address.address = s;
        address.date = System.currentTimeMillis();
        address.save();

        addressList = getAllAddress();
        adapter.swapItems(addressList);


    }

    public List<Address> getAllAddress() {
        return new Select()
                .from(Address.class)
                .orderBy("date")
                .execute();
    }


    class Adapter extends RecyclerView.Adapter<AddressHolder>{

        private List<Address> addresses;

        public Adapter(List<Address> addresses) {
            this.addresses = addresses;
        }

        @Override
        public AddressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.address_line, parent, false);

            return new AddressHolder(view);
        }

        @Override
        public void onBindViewHolder(AddressHolder holder, int position) {
            holder.addressTv.setText((position+1)+" "+addresses.get(position).address+".");

        }

        @Override
        public int getItemCount() {
            return addresses.size();
        }

        public void swapItems(List<Address> addressList) {
            addresses = addressList;
            notifyDataSetChanged();

        }
    }


    class AddressHolder extends RecyclerView.ViewHolder{

        private final TextView addressTv;

        public AddressHolder(View itemView) {
            super(itemView);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
        }
    }

}
