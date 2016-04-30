package com.dotdex.frenzy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.dotdex.frenzy.adapters.OverFlowAdapter;
import com.dotdex.frenzy.data.model.Address;
import com.dotdex.frenzy.model.MenuOption;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements
        NewAddressFragment.OnFragmentInteractionListener{

    private static final String ADDRESS_FRAG = "address_fragment";
    private List<Address> addressList;
    private Adapter adapter;
    private ArrayList<MenuOption> options;

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
                fragment.show(getSupportFragmentManager(), ADDRESS_FRAG);
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

        //the array
        options = new ArrayList<>();
        options.add(new MenuOption(R.drawable.ic_check, getString(R.string.select), getString(R.string.select_sub)));
        options.add(new MenuOption(R.drawable.ic_delete, getString(R.string.delete), getString(R.string.del_sub)));
        options.add(new MenuOption(R.drawable.ic_mode_edit, getString(R.string.edit), getString(R.string.edit_sub)));

    }

    @Override
    public void onAddBtnPressed(String str, String phone) {
//        persist the string address?
        Address address = new Address();
        address.address = str;
        address.phone = phone;
        address.date = System.currentTimeMillis();
        address.save();
        refreshList();
    }

    public List<Address> getAllAddress() {
        return new Select()
                .from(Address.class)
                .orderBy("date")
                .execute();
    }


    private void refreshList()
    {
        addressList = getAllAddress();
        adapter.swapItems(addressList);

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
        public void onBindViewHolder(AddressHolder holder, final int position) {
            holder.addressTv.setText(addresses.get(position).address);
            holder.phoneTv.setText(addresses.get(position).phone);
//            Log.e("DB", addresses.get(position).address + " : " + addresses.get(position).phone);
            holder.actionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogForMore(addresses.get(position));
                }
            });

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

    private void showDialogForMore(final Address address) {

        //show the more menu dialog.
        //here the overflow button is clicked
        OverFlowAdapter adapter = new OverFlowAdapter(this, options);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("");
//            builder.setCancelable(false);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //what will happen here
                switch (i)
                {
                    case 0:
                        Intent rIntent = new Intent();
                        Bundle b = new Bundle();
                        b.putString("address",address.address);
                        b.putString("phone",address.phone);
                        rIntent.putExtras(b);
                        setResult(Activity.RESULT_OK, rIntent);
                        finish();
                        break;
                    case 1:
                        address.delete();
                        refreshList();
                        break;
                    case 2:
//                        NewAddressFragment fragment = NewAddressFragment.newInstance();
//                        fragment.show(getSupportFragmentManager(), ADDRESS_FRAG);
                        // TODO: 11-Mar-16 Here i shall substring the old address
                        break;
                }

            }
        });
        builder.create();
        builder.show();
    }


    class AddressHolder extends RecyclerView.ViewHolder{

        private final TextView addressTv;
        private final ImageButton actionBtn;
        private final TextView phoneTv;

        public AddressHolder(View itemView) {
            super(itemView);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            actionBtn = (ImageButton) itemView.findViewById(R.id.address_btn);
            phoneTv = (TextView) itemView.findViewById(R.id.number_tv);
        }
    }

}
