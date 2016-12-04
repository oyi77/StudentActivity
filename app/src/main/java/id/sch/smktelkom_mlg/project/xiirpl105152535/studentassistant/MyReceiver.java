package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * Created by Administrator on 03/12/2016.
 */

public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);

    }
}