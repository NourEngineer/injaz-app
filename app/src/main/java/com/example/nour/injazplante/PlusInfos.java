package com.example.nour.injazplante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.ShareActionProvider;

public class PlusInfos extends AppCompatActivity {

    private ShareActionProvider mSharedActionProvider;

    String article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_infos);

        int position = getIntent().getExtras().getInt("position");
        article = "";
        for(String s : MainActivity.list_plein.get(position).getDescription()){
            article+=("- "+s.concat("\n"));
        }
        TextView plantName = findViewById(R.id.plantName);
        plantName.setText(MainActivity.list_plein.get(position).getNom());
        TextView description = findViewById(R.id.description);
        description.setText(article);
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(MainActivity.list_plein.get(position).getImage());
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.mShare :
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,article);
                startActivity(Intent.createChooser(
                        i,
                        "Share via"
                ));
                break;
        }
        Toast.makeText(getApplicationContext(),"Share via",Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
