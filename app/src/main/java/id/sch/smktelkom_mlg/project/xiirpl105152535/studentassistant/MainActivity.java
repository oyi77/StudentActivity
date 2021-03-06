package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.Data.Data;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewAdapter.IRecyclerViewAdapter {
    //static final String username = "sandyfschool";
   // final static String DB_URL = "https://studassist-f6998.firebaseio.com/" + username;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView hello;
    Firebase fire;
    String Uvalue = "sandyfschool";
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();   //menyimpan isi
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> key = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    Uvalue = firebaseAuth.getCurrentUser().getEmail().toString();
                    System.out.println(Uvalue);
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }


            }
        };
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Uvalue = extras.getString("username");
        }else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }


        if (Uvalue.contains(".") || Uvalue.contains(",")) {
            Uvalue = Uvalue.replace(".", "");
            Uvalue = Uvalue.replace(",", "");
            Uvalue = Uvalue.replace("@gmail", "");
        }
        System.out.println("Ini Uvalue :"+ Uvalue);

        final String username = Uvalue;
        final String DB_URL = "https://studassist-f6998.firebaseio.com/" + username;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        fire = new Firebase(DB_URL);
        this.retrieveData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, InputJadwal.class));
                Intent intent = new Intent(MainActivity.this, inputTugas.class);
                intent.putExtra("username", username);
                startActivity(intent);
               // startActivity(new Intent(MainActivity.this, inputTugas.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are you sure to exit?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity.this, Setting.class));
        } else if (id == R.id.nav_language) {
            Intent intent = new Intent(MainActivity.this, InputJadwal.class);
            intent.putExtra("username", Uvalue);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, About.class));
        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void retrieveData() {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getUpdates(DataSnapshot ds) {
        names.clear();
        content.clear();
        date.clear();
        key.clear();

        for (DataSnapshot data : ds.getChildren()) {
            Data p = new Data();
            p.setPelajaran(data.getValue(Data.class).getPelajaran());
            p.setIsi(data.getValue(Data.class).getIsi());
            p.setDue(data.getValue(Data.class).getDue());

            key.add(data.getKey());
            names.add(p.getPelajaran());
            content.add(p.getIsi());
            date.add(p.getDue());
        }

        if (names.size() >= 0) {
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(names, date, content, this);
            RecyclerView myView = (RecyclerView) findViewById(R.id.recyclerview);
            //myView.setHasFixedSize(true);
            myView.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            myView.setLayoutManager(llm);
        } else {
            Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doClick(int pos) {
        //Toast.makeText(MainActivity.this, names.get(pos), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, DetailTugas.class);
        Bundle extras = new Bundle();
        extras.putString("judul",names.get(pos));
        extras.putString("tgl",date.get(pos));
        extras.putString("isi",content.get(pos));
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void doDone(int pos) {

        Toast.makeText(MainActivity.this,"Tugas Selesai", Toast.LENGTH_SHORT).show();
        fire.child("/Task/"+key.get(pos)).removeValue();
    }
}
