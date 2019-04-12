package com.example.nour.injazplante;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> list = new ArrayList<Integer>();
    GridView gridView;
    static  SysAlert sysAlert ;
    Calendar calendar ;
    static ArrayList<Plant> list_plein ;
    ArrayList<Plant> notifiedPlant = new ArrayList<>();
    ArrayList<Plant> favorisPlant = new ArrayList<>();

    private  static  MediaPlayer mediaPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        list_plein = initialise();
        int sizeNotif = 0;
        sizeNotif = preferences.getInt("sizeNotif",sizeNotif);
        Log.i("sizeNotif",Integer.valueOf(sizeNotif).toString());
        for(int i = 0 ; i < sizeNotif;i++){
            int j = 0;
            j = preferences.getInt("notif"+i,j);
            list_plein.get(j-1).setNotifie(true);
            notifiedPlant.add(list_plein.get(j-1));
        }

        int sizeFav =0;
        sizeFav = preferences.getInt("sizeFav",sizeFav);
        for(int i = 0 ; i < sizeFav;i++){
            int j = 0;
            j = preferences.getInt("fav"+i,j);
            list_plein.get(j-1).setFavoris(true);
            favorisPlant.add(list_plein.get(j-1));
        }

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,9);
        calendar.set(Calendar.MINUTE,51);
        calendar.set(Calendar.SECOND,30);

        mediaPlayer = MediaPlayer.create(this,R.raw.alarm);
        Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,/*PendingIntent.FLAG_UPDATE_CURRENT*/ PendingIntent.FLAG_ONE_SHOT    );
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        gridView = (GridView) findViewById(R.id.plant_grid);

        /*ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.plant);*/
        list.add( R.drawable.plant);
        /*imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.plant + 1);*/
        list.add(R.drawable.plant2);
        list.add(R.drawable.plant2);
        GridAdapter gridAdapter = new GridAdapter();
       /* if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=9){
            sysAlert = new SysAlert(((24-Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+9-1)*60*60*1000)
                    +(60-Calendar.getInstance().get(Calendar.MINUTE))*60*1000,1000);
        }else{
            sysAlert = new SysAlert(((9-Calendar.getInstance().get(Calendar.HOUR_OF_DAY))*60*60*1000)
                    +(60-Calendar.getInstance().get(Calendar.MINUTE))*60*1000,1000);
        }
        sysAlert.start();*/

       gridView.setAdapter(gridAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this,FullImageActivity.class);
                intent.putExtra("img",list_plein.get(position).getImage());
                /*ImageView myImage = list.get(position);
                myImage.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(myImage.getDrawingCache());
                myImage.setDrawingCacheEnabled(false);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
                intent.putExtra("img",baos.toByteArray());*/
                startActivity(intent);
           }
        });


    }
    SharedPreferences preferences ;

   /* @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("sizeNotif",notifiedPlant.size());
        editor.putInt("sizeFav",favorisPlant.size());
        for(int i = 0 ; i<notifiedPlant.size() ; i++){
            Plant plant = notifiedPlant.get(i);
            editor.putInt("notif"+i,plant.getId());
        }
        for(int i = 0 ; i<favorisPlant.size() ; i++){
            Plant plant = favorisPlant.get(i);
            editor.putInt("fav"+i,plant.getId());
        }
        editor.commit();
    }
*/
    @Override
    protected void onStop() {

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("sizeNotif",notifiedPlant.size());
        editor.putInt("sizeFav",favorisPlant.size());
        for(int i = 0 ; i<notifiedPlant.size() ; i++){
            Plant plant = notifiedPlant.get(i);
            editor.putInt("notif"+i,plant.getId());
        }
        for(int i = 0 ; i<favorisPlant.size() ; i++){
            Plant plant = favorisPlant.get(i);
            editor.putInt("fav"+i,plant.getId());
        }
        editor.commit();
        Log.i("sizeNotif",Integer.valueOf(notifiedPlant.size()).toString());
        super.onStop();


    }
    protected void onPause() {

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("sizeNotif",notifiedPlant.size());
        editor.putInt("sizeFav",favorisPlant.size());
        for(int i = 0 ; i<notifiedPlant.size() ; i++){
            Plant plant = notifiedPlant.get(i);
            editor.putInt("notif"+i,plant.getId());
        }
        for(int i = 0 ; i<favorisPlant.size() ; i++){
            Plant plant = favorisPlant.get(i);
            editor.putInt("fav"+i,plant.getId());
        }
        editor.commit();
        Log.i("sizeNotif",Integer.valueOf(notifiedPlant.size()).toString());
        super.onPause();


    }

    public static void playMusic(){mediaPlayer.start();}

    public class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list_plein.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {


                view = getLayoutInflater().inflate(R.layout.row_layout,parent,false);
                ImageView myImage = (ImageView) view.findViewById(R.id.myImage);
                TextView plantName = view.findViewById(R.id.plant_name);
            Calendar rightNow = Calendar.getInstance();
            Calendar timeNotif = Calendar.getInstance();
            timeNotif.set(2019,3,20,10,00);
               if(plantName!=null) plantName.setText(list_plein.get(position).getNom());
                       //" " + ((rightNow.getTimeInMillis()-timeNotif.getTimeInMillis())));
               //+"  rightNow "+rightNow.get(Calendar.YEAR)+"/"+rightNow.get(Calendar.MONTH) +"/"+rightNow.get(Calendar.DAY_OF_MONTH )+"  "
               // plantName.getText().toString().concat( Integer.toString(list.get(position).intValue()))
               // myImage.setImageDrawable(((ImageView)getItem(position)).getDrawable());
               myImage.setImageResource(list_plein.get(position).getImage());
            ImageView plus = view.findViewById(R.id.plus);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(MainActivity.this,PlusInfos.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });
            final ImageView imageView = view.findViewById(R.id.love);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(list_plein.get(position).getFavoris()) {
                        imageView.setImageResource(R.drawable.coeur2);
                        favorisPlant.remove(list_plein.get(position));
                    }
                    else{
                        imageView.setImageResource(R.drawable.coeur);
                        favorisPlant.add(list_plein.get(position));
                    }
                    list_plein.get(position).setFavoris(!list_plein.get(position).getFavoris());

                }
            });

            final ImageView notify = view.findViewById(R.id.notify);
            notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(list_plein.get(position).getNotifie()) {
                        notify.setImageResource(R.drawable.notify);
                        notifiedPlant.remove(list_plein.get(position));
                    }
                    else{
                        notify.setImageResource(R.drawable.notify2);
                        notifiedPlant.add(list_plein.get(position));
                    }
                    list_plein.get(position).setNotifie(!list_plein.get(position).getNotifie());

                }
            });
            if(list_plein.get(position).getFavoris()) imageView.setImageResource(R.drawable.coeur);
            else imageView.setImageResource(R.drawable.coeur2);
            if(list_plein.get(position).getNotifie()) notify.setImageResource(R.drawable.notify2);
            else notify.setImageResource(R.drawable.notify);
            return view;
        }
    }


    private ArrayList<File> imageReader(File root){
        ArrayList<File> b = new ArrayList<>();

        File[] files = root.listFiles();

        for(int i=0;i< files.length;i++){

            if (files[i].isDirectory()) {

                b.addAll(imageReader(files[i]));
            }else{

                if(files[i].getName().endsWith(".jpg")){

                    b.add(files[i]);
                }

            }
        }

        return b;
    }

    public ArrayList<Plant> initialise(){

        ArrayList<Plant> list = new ArrayList<Plant>();

        int[] jourArrosage =new int[]{0,0,0,0,0,0,0} ;
        ArrayList<String> biens =new ArrayList<String>() ;
        biens.add("يستعمل في الطب البديل");
        biens.add("التقليل من ظهور التجاعيد و آثار الشيخوخة");
        biens.add("مقوية للعضلات و زيوتها تستعمل في التدليك ");
        biens.add("مضاد للميكروبات و البكتيريا ");
        biens.add ("تخليص الجسم من السموم");
        Plant plante = new Plant(1,"ابرة الراعي",0,0,jourArrosage,0,"" ,R.drawable.ibratra3i,biens);

        list.add(plante);

        jourArrosage =new int[]{0,0,0,0,0,0,0} ;
        biens = new ArrayList<String>() ;
        biens.add("يستعمل في التدليك");
        biens.add("معالجة لسعات النحل");
        biens.add("يزيل اختناق الأنففي حالة انفلونزا");
        plante = new Plant(2,"Fusain",0,0,jourArrosage,0,"" ,R.drawable.fusain,biens);

        list.add(plante);

        jourArrosage =new int[]{0,0,0,0,0,0,0} ;
        biens = new ArrayList<String>() ;
        biens.add("ينظم مستوى السكر في الدم");
        biens.add("يساعد على خسارة الوزن ");
        biens.add("يعزز من صحة البشرة و الجلد ");
        biens.add("يعالج قرحة المعدة");
        biens.add("يدعم صحة العظام");
        plante = new Plant(3,"الصبار"
                ,0,0,jourArrosage,0,"" ,R.drawable.sabar,biens);

                list.add(plante);

        jourArrosage =new int[]{0,0,0,0,0,0,0} ;
        biens = new ArrayList<String>() ;
        biens.add("قتل الإكتئاب");
        biens.add("تعديل مستوى السكر في الدم");
        biens.add("مضاد للالتهابات");
        biens.add("جيد لتقوية القلب");
        biens.add("علاج الشيب المبكر");
        biens.add("يعزز الشعر الصحي");
        plante = new Plant(4,"المريمية "
                ,0,0,jourArrosage,0,"" ,R.drawable.merimia,biens);

                list.add(plante);


        jourArrosage =new int[]{0,0,0,0,0,0,0} ;
        biens = new ArrayList <String>();
        biens.add("تنتج مواد للقضاءعلى البكتيريا");
        biens.add("تستعمل في العلاج ضد السرطان");
        biens.add("علاج الحروق الصغيرة");
        biens .add ("علاج الطفح الجلدي ");
        plante = new Plant(5,"الالوفيرا"
                ,0,0,jourArrosage,0,"" ,R.drawable.alovira,biens);

                list.add(plante);


        jourArrosage =new int[]{0,0,0,0,0,0,0} ;
        biens = new ArrayList<String>() ;
        biens.add("مضاد للأكسدة ");
        biens.add("مقاومة التقدم في السن");
        biens.add("مقاومة السرطان و الأورام ");
        biens.add("مضاد للالتهابات");
        plante = new Plant(6,"الهليون",0,0,jourArrosage,0,"" ,R.drawable.hilyon,biens);

        list.add(plante);

        return list;
    }
    public Plant recuperer_plante(int indice){
        return list_plein.get(indice);
    }

}
