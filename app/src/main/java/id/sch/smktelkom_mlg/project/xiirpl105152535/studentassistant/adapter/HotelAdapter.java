package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.R;
import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.model.Hotel;

/**
 * Created by Fikri on 05/11/2016.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    ArrayList<Hotel> hotelList;
    IHotelAdapter mIHotelAdapter;

    public HotelAdapter(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public HotelAdapter(Context context, ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
        mIHotelAdapter = (IHotelAdapter) context;
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

    public interface IHotelAdapter {
        void doclick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIHotelAdapter.doclick(getAdapterPosition());
                }
            });
        }

    }
}
