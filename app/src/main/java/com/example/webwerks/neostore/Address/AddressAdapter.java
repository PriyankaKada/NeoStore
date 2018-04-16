package com.example.webwerks.neostore.Address;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.webwerks.neostore.Address.RoomDb.Address;
import com.example.webwerks.neostore.Dashboard.DashboardActivity;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import java.util.List;

/**
 * Created by webwerks on 4/16/18.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private List<Address> addressList;
    Context context;
    private int mCheckedPosition = -1;
    private int VIEW_TYPE_FOOTER = 1;
    private int VIEW_TYPE_CELL = 0;
    Address address;
    AddressListPreseter addressListPreseter;

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        public TextView address_title, complete_address,total_price,tv_total;
        public RadioButton radioButton;
        Button order_now;
        public AddressViewHolder(View view) {
            super(view);
            address_title = (TextView) view.findViewById(R.id.address_title);
            complete_address = (TextView) view.findViewById(R.id.complete_address);
            radioButton=(RadioButton)view.findViewById(R.id.rad_address);
            total_price = (TextView) view.findViewById(R.id.total_price);
            order_now = (Button) view.findViewById(R.id.order_now);
            tv_total= (TextView) view.findViewById(R.id.tv_total);

        }
    }


    public AddressAdapter(List<Address> addressList, Context context, AddressListPreseter addressListPreseter) {
        this.addressList = addressList;
        this.context=context;
        this.addressListPreseter=addressListPreseter;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (viewType == VIEW_TYPE_CELL) {
            //Create viewholder for your default cell
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_listing, parent, false);

        } else {
            //Create viewholder for your footer view
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_listing_footer, parent, false);

        }
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, final int position) {

        if (position == addressList.size()) {
            holder.total_price.setVisibility(View.GONE);
            holder.order_now.setText("Place Order");
            holder.tv_total.setVisibility(View.GONE);
            //Order now button onclick
            holder.order_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressListPreseter.placeOrder(SPManager.getInstance(context).retriveString("access_token"), address.getAddress() + " " + address.getStreet_name() + " " + address.getCity() + " " + address.getState() + " " + address.getCountry() + " " + address.getZip_code()                            );
                }
            });


        }else {


            address = addressList.get(position);
            holder.address_title.setText(SPManager.getInstance(context).retriveString("first_name") + " " + SPManager.getInstance(context).retriveString("last_name"));
            holder.complete_address.setText(address.getAddress() + " " + address.getStreet_name() + " " + address.getCity() + " " + address.getState() + " " + address.getCountry() + " " + address.getZip_code());

            holder.radioButton.setOnCheckedChangeListener(null);
            holder.radioButton.setChecked(position == mCheckedPosition);
            holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCheckedPosition = position;
                    notifyDataSetChanged();
                }
            });
        }


    }
    @Override
    public int getItemViewType(int position) {
        return (position == addressList.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return addressList.size()+1;
    }
}