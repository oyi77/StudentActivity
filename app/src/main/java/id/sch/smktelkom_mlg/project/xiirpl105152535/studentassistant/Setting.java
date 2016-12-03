package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.adapter.HotelAdapter;
import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.model.Hotel;

public class Setting extends AppCompatActivity implements HotelAdapter.IHotelAdapter {

    public static final String HOTEL = "hotel";
    ArrayList<Hotel> mList = new ArrayList<>();
    HotelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new HotelAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);

        fillData();

        setTitle("Setting");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.places);
        String[] arDeskripsi = resources.getStringArray(R.array.place_desc);

        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new Hotel(arJudul[i], arDeskripsi[i]));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doclick(int pos) {
        Toast.makeText(Setting.this, "Data ke" + pos, Toast.LENGTH_SHORT).show();


        if (pos == 0) {
            Intent intent = new Intent(this, Notification.class);
            startActivity(intent);
        } else if (pos == 1) {
            Intent intent = new Intent(this, Integration.class);
            startActivity(intent);
        } else if (pos == 2) {
            Intent intent = new Intent(this, PopUp.class);
            startActivity(intent);
        } else if (pos == 3) {
            Intent intent = new Intent(this, acc.class);
            startActivity(intent);
        } else if (pos == 4) {
            Intent intent = new Intent(this, ProfilePage.class);
            startActivity(intent);
        }
    }
}
