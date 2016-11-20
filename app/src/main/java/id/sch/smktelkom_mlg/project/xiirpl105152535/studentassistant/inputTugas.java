package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;

import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.Data.Data;

public class inputTugas extends AppCompatActivity {

    final static int RQS_1 = 1;
    EditText pelajaran;
    EditText isi;
    DatePicker due;
    Button btnSave;
    int dueadate, dueamonth, dueayear;

    Calendar cal = Calendar.getInstance();
    Calendar current = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tugas);

        String UValue = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UValue = extras.getString("username");
        }

        final String  username = UValue;

        pelajaran = (EditText) findViewById(R.id.editTextPelajaran);
        isi = (EditText) findViewById(R.id.editTextIsi);
        due = (DatePicker) findViewById(R.id.datePicker);
        btnSave = (Button) findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dueayear < current.get(Calendar.YEAR) && dueamonth < current.get(Calendar.MONTH) || dueayear < current.get(Calendar.YEAR) && dueamonth < current.get(Calendar.DATE)) {

                    Firebase ref = new Firebase("https://studassist-f6998.firebaseio.com/");
                    //Getting values to store

                    String pelajarana = pelajaran.getText().toString().trim();
                    String isia = isi.getText().toString().trim();
                    dueadate = due.getDayOfMonth();
                    dueamonth = due.getMonth();
                    dueayear = due.getYear();
                    String duea = String.valueOf(dueadate) + "-" + String.valueOf(dueamonth) + "-" + String.valueOf(dueayear);

                    int dueahour = current.get(Calendar.HOUR_OF_DAY);
                    int dueaminute = current.get(Calendar.MINUTE);
                    cal.set(dueayear, dueamonth, dueadate - 1, dueahour, dueaminute);
                    String jam = String.valueOf(dueahour) + " " + String.valueOf(dueaminute);


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


                    if (cal.compareTo(current) <= 0) {
                        //The set Date/Time already passed
                        Toast.makeText(getApplicationContext(),
                                "Invalid Date/Time",
                                Toast.LENGTH_LONG).show();
                    } else {
                        setAlarm(cal);
                    }

                    Toast.makeText(inputTugas.this, "DONE", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),
                            jam,
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(inputTugas.this, MainActivity.class));
                    finish();

                } else {
                    Toast.makeText(inputTugas.this, "Tanggal sudah lewat", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void setAlarm(Calendar targetCal) {
        Intent intent = new Intent(getBaseContext(), Notification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }


}


