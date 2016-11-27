package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;

import java.util.ArrayList;


/**
 * Created by Administrator on 09/11/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public ArrayList<String> myValues;
    public ArrayList<String> myValuesd;
    public ArrayList<String> myValuesc;

    IRecyclerViewAdapter mIRecyclerViewAdapter;

    public interface IRecyclerViewAdapter
    {
        void doClick(int pos);
        void doDone(int pos);
    }
    public void setIRecyclerViewAdapter(final IRecyclerViewAdapter iRecyclerViewAdapter){
        this.mIRecyclerViewAdapter = iRecyclerViewAdapter;
    }

    public RecyclerViewAdapter(ArrayList<String> myValues, ArrayList<String> myValuesd, ArrayList<String> myValuesc, Context context) {
        this.myValues = myValues;
        this.myValuesd = myValuesd;
        this.myValuesc = myValuesc;
        mIRecyclerViewAdapter = (IRecyclerViewAdapter) context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText(myValues.get(position));
        holder.myTextViewc.setText(myValuesc.get(position));
        holder.myTextViewd.setText(myValuesd.get(position));
    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        private TextView myTextViewd;
        private TextView myTextViewc;
        ImageButton done;

        public MyViewHolder(View itemView) {
            super(itemView);
            done = (ImageButton) itemView.findViewById(R.id.imageButton);
            myTextView = (TextView) itemView.findViewById(R.id.text_cardview);
            myTextViewc = (TextView) itemView.findViewById(R.id.text_isi);
            myTextViewd = (TextView) itemView.findViewById(R.id.text_tgl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mIRecyclerViewAdapter!=null) {
                        mIRecyclerViewAdapter.doClick(getAdapterPosition());
                    }
                }
            });
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIRecyclerViewAdapter.doDone(getAdapterPosition());
                }
            });

        }
    }
}