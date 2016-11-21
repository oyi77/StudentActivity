package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.Data.Jadwal;


public class InputJadwal extends AppCompatActivity {
    EditText mapel;
    EditText jam;
    EditText hari;
    EditText guru;
    Button btnSaveJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jadwal);

        mapel = (EditText) findViewById(R.id.editTextMapel);
        jam = (EditText) findViewById(R.id.editTextJam);
        hari = (EditText) findViewById(R.id.editTextHari);
        guru = (EditText) findViewById(R.id.editTextGuru);
        btnSaveJadwal = (Button) findViewById(R.id.buttonSaveJadwal);

        String UValue = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UValue = extras.getString("username");
        }
        if (UValue.contains(".") || UValue.contains(",")) {
            UValue = UValue.replace(".", "");
            UValue = UValue.replace(",", "");
            UValue = UValue.replace("@gmail", "");
        }

        final String  username = UValue;

        btnSaveJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase ref = new Firebase("https://studassist-f6998.firebaseio.com/");

                String mapela = mapel.getText().toString().trim();
                String jama = jam.getText().toString().trim();
                String haria = hari.getText().toString().trim();
                String gurua = guru.getText().toString().trim();

                Jadwal jadwal = new Jadwal();

                jadwal.setMapel(mapela);
                jadwal.setJam(jama);
                jadwal.setHari(haria);
                jadwal.setGuru(gurua);

                Firebase newRef = ref.child(username).child("Jadwal").push();
                newRef.setValue(jadwal);

                Toast.makeText(InputJadwal.this, "Jadwal Ditambahkan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InputJadwal.this, MainActivity.class));
            }
        });
    }
}
