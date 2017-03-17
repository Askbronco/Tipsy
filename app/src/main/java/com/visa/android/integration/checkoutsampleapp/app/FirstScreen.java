package com.visa.android.integration.checkoutsampleapp.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import java.util.Random;




public class FirstScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void goToNext(View view)
    {
<<<<<<< HEAD
        Intent intent = new Intent(this,bar.class);

        Random rand = new Random();

        int  n = rand.nextInt(300) + 1;

        intent.putExtra("userID",Integer.toString(n));


        // Intent intent = new Intent(this,com.google.android.gms.samples.vision.barcodereader.MainActivity.class);
=======
        Intent intent = new Intent(this,listDrinksActivity.class);
       // Intent intent = new Intent(this,com.google.android.gms.samples.vision.barcodereader.MainActivity.class);
>>>>>>> 6a751608f7bebed5955ecaa055ab66641671b1bd

        startActivity(intent);
    }

<<<<<<< HEAD
   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this,listDrinksActivity.class);
      //  Intent intent = new Intent(this,bar.class);

        startActivity(intent);

    }*/
=======
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this,listDrinksActivity.class);
        startActivity(intent);

    }
>>>>>>> 6a751608f7bebed5955ecaa055ab66641671b1bd
}
