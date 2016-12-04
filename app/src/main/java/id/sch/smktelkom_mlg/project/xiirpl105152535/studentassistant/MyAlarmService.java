package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

/**
 * Created by Administrator on 03/12/2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


public class MyAlarmService extends Service
{

    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TDO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TOO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);

        //Notification notification = new Notification(R.drawable.ic_launcher,"This is a test message!", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Alarm Tugas")
                .setContentText("Cek aplikasi untuk lihat tugas")
                .setSmallIcon(R.drawable.ic_menu_about)
                .build();

        mManager.notify(0, notification);
    }

    @Override
    public void onDestroy()
    {
         //TOD uto-generated method stub
        super.onDestroy();
    }

}