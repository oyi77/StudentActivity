package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class TampilJadwal extends AppCompatActivity {
    Firebase fire;
    String Uvalue = "sandyfschool";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_jadwal);

        final String username = Uvalue;
        final String DB_URL = "https://studassist-f6998.firebaseio.com/" + username;
        Firebase.setAndroidContext(this);
        fire = new Firebase(DB_URL);
        this.retrieveDataJadwal();
    }

    private void retrieveDataJadwal() {

    }
}
