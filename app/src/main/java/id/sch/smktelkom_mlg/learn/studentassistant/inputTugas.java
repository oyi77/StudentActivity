package id.sch.smktelkom_mlg.learn.studentassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import id.sch.smktelkom_mlg.learn.studentassistant.Data.Data;

public class inputTugas extends AppCompatActivity {

    EditText pelajaran;
    EditText isi;
    DatePicker due;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tugas);

        pelajaran = (EditText) findViewById(R.id.editTextPelajaran);
        isi = (EditText) findViewById(R.id.editTextIsi);
        due = (DatePicker) findViewById(R.id.datePicker);
        btnSave = (Button) findViewById(R.id.buttonSave);
        final String username = "sandyfschool";

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase ref = new Firebase("https://studassist-f6998.firebaseio.com/");
                //Getting values to store

                String pelajarana = pelajaran.getText().toString().trim();
                String isia = isi.getText().toString().trim();
                int dueadate = due.getDayOfMonth();
                int dueamonth = due.getMonth();
                int dueayear = due.getYear();
                String duea = String.valueOf(dueadate) + "-" + String.valueOf(dueamonth) + "-" + String.valueOf(dueayear);

                //Creating Person object
                //Person person = new Person();
                Data data = new Data();

                //Adding values
//                person.setName(name);
//                person.setAddress(address);
                data.setPelajaran(pelajarana);
                data.setIsi(isia);
                data.setDue(duea);
                Firebase newRef = ref.child(username).child("Task").push();
                newRef.setValue(data);

                Toast.makeText(inputTugas.this, duea, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
