package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailTugas extends AppCompatActivity {
    TextView tvJdl, tvTgl, tvIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tugas);

        tvJdl = (TextView)findViewById(R.id.textViewJudul);
        tvIsi = (TextView)findViewById(R.id.textViewIsi);
        tvTgl = (TextView)findViewById(R.id.textViewTgl);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String judul = extras.getString("judul");
        String tgl = extras.getString("tgl");
        String isi = extras.getString("isi");

        tvJdl.setText(judul);
        tvIsi.setText(isi);
        tvTgl.setText(tgl);
    }
}
