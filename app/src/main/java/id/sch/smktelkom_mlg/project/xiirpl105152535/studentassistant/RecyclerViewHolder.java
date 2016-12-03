package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.Data.Data;

/**
 * Created by Administrator on 20/11/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = RecyclerViewHolder.class.getSimpleName();
    public ImageButton markIcon;
    public TextView categoryTitle;
    private ArrayList<Data> taskObject;

    public RecyclerViewHolder(final View itemView, final ArrayList<Data> taskObject) {
        super(itemView);
        this.taskObject = taskObject;
        categoryTitle = (TextView) itemView.findViewById(R.id.text_cardview);
        markIcon = (ImageButton) itemView.findViewById(R.id.imageButton);

        markIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete icon has been clicked", Toast.LENGTH_LONG).show();
                String taskTitle = taskObject.get(getAdapterPosition()).getPelajaran();
                Log.d(TAG, "Task Title " + taskTitle);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.orderByChild("Task").equalTo(taskTitle);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }
}
