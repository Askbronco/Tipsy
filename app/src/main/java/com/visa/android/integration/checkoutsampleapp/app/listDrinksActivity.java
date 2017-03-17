package com.visa.android.integration.checkoutsampleapp.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;




public class listDrinksActivity extends Activity {

    public static final int VXO = 2;
    String userId,tabId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drinks);
        userId = getIntent().getExtras().getString("userID");
        tabId = getIntent().getExtras().getString("tabID");
        EditText e1 = (EditText)findViewById(R.id.seatID);
        e1.setText("Tab ID: "+tabId);

        EditText e2 = (EditText)findViewById(R.id.merchantID);
        e2.setText("System user id: "+userId);








    }



    private String getFileContent(){


        try {
            HttpURLConnection conn = null;
            URL url = new URL("http://jsonplaceholder.typicode.com/users");

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            jsonString = sb.toString();
            Log.d("Response string",jsonString);
            return jsonString;
        }catch(Exception e){
            Log.d("Exception here",e.toString());
        }
        return null;









       /* FileInputStream f;

        try {
            f = openFileInput(targetFilePath);
            String temp="";

            int c;
            while( (c = f.read()) != -1){
                temp = temp + Character.toString((char)c);

            }

            f.close();
            return temp;
        }catch (Exception e){}
        return null;*/
        /*

        try {

            FileInputStream in = openFileInput(targetFilePath);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            String message = null;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);

                sb.append("\n");
            }
            Log.d("String is",sb.toString());
            return sb.toString();
        }catch(Exception e){
            Log.d("Exception is",e.toString());
        }

        Log.d("file","Never found");
        return null;*/
    }

    public void confirmSeat(View view)
    {
        Intent intent = new Intent(this,addMerchantManually.class);
        EditText e1 = (EditText)findViewById(R.id.seatID);
        EditText e2 = (EditText)findViewById(R.id.merchantID);



        intent.putExtra("tabId",tabId);
        intent.putExtra("userId",userId);

        startActivity(intent);

    }

}


class downloadContent extends AsyncTask<String,Integer,String>
{
    protected  String doInBackground(String ...str) {
        try {
            HttpURLConnection conn = null;
            URL url = new URL("http://jsonplaceholder.typicode.com/users");

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            jsonString = sb.toString();
            Log.d("Response string",jsonString);
            return jsonString;
        }catch(Exception e){
            Log.d("Exception here",e.toString());
        }
        return null;


    }

    protected void onPostExecute(String result) {
        Log.d("String was in onPost",result);

    }
}


