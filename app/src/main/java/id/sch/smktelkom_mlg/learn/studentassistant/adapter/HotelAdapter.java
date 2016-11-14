package id.sch.smktelkom_mlg.learn.studentassistant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.studentassistant.R;
import id.sch.smktelkom_mlg.learn.studentassistant.model.Hotel;

/**
 * Created by Fikri on 05/11/2016.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    ArrayList<Hotel> hotelList;

    public HotelAdapter(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.tvJudul.setText(hotel.judul);
        holder.tvDeskripsi.setText(hotel.deskripsi);
    }

    @Override
    public int getItemCount() {
        if (hotelList != null)
            return hotelList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
        }
    }
}
