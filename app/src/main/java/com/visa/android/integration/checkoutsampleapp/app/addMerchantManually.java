package com.visa.android.integration.checkoutsampleapp.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.SimpleAdapter;
import android.widget.Toast;




public class addMerchantManually extends Activity {
    public static final int VXO = 2;
    ArrayList<HashMap<String, String>> drinkList;
    private ListView lv;
    Integer totalAmount = 0;
    String userId,tabId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drinkList = new ArrayList<>();


        setContentView(R.layout.activity_add_merchant_manually);
        lv = (ListView) findViewById(R.id.list);
        userId = getIntent().getExtras().getString("userID");
        tabId = getIntent().getExtras().getString("tabId");

    }

   /* public void addDrinks(View view)
    {


        EditText e1 = (EditText)findViewById(R.id.editText);
        EditText e2= (EditText)findViewById(R.id.editText2);
        EditText e3= (EditText)findViewById(R.id.editText3);


        String merchantid = e1.getText().toString();
        String name =  e2.getText().toString();
        String cost = e3.getText().toString();
        String finalList = merchantid.concat(":").concat(name).concat(":").concat(cost).concat("\n\r");
        Log.d("Added string is",finalList);




        String filename = "drinkList.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(finalList.getBytes());

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, PaymentStartActivity.class);
        intent.putExtra("buttonType", VXO);
        startActivity(intent);




    }*/

    public void payListener(View view) {
        Intent intent = new Intent(this, PaymentStartActivity.class);
        intent.putExtra("buttonType", VXO);
        intent.putExtra("totalAmount",totalAmount.toString());
        Log.d("Total was",totalAmount.toString());
        startActivity(intent);

    }

    public void getDrinks(View view) {
        downloadContentJSON download = new downloadContentJSON();
        download.execute(tabId);


    }


    private class downloadContentJSON extends AsyncTask<String, Void, Void> {

        String jsonString = new String();

        protected Void doInBackground(String... arg0) {
            try {
                Log.d("status1", "I was here");

                HttpURLConnection conn = null;
                try {
                    URL url = new URL(" http://ec2-54-193-127-244.us-west-1.compute.amazonaws.com/tab/"+arg0[0]+"/drinks");
                    Log.d("URL I will try is",url.toString());

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setDoOutput(true);

                    conn.connect();


                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                    char[] buffer = new char[1024];


                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    jsonString = sb.toString();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Something went wrong!Please try again", Toast.LENGTH_LONG).show();

                    Log.d("Log",e.toString());}
                Log.d("Response string", jsonString);
            } catch (Exception e) {
                Log.d("Exception here", e.toString());
            }
            return null;



        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //Log.d("String was in onPost", result.toString());

            try {

                JSONObject jsonObj = new JSONObject(jsonString);
                JSONArray drinks = jsonObj.getJSONArray("drinks");
                if(drinks.length() == 0)
                    Toast.makeText(getApplicationContext(), "No drinks are billed yet,Life is too short to wait", Toast.LENGTH_LONG).show();


                for (int i = 0; i < drinks.length(); i++) {
                    JSONObject c = drinks.getJSONObject(i);
                    String id = c.getString("name");
                    String price = c.getString("price");
                    String quantity = c.getString("quantity");
                    totalAmount +=  Integer.parseInt(price)*Integer.parseInt(quantity);
                    HashMap<String, String> drinksmap = new HashMap<>();
                    drinksmap.put("id", "Drink:"+id);
                    drinksmap.put("price", "Price:"+price);
                    drinksmap.put("quantity", "Quantity:"+quantity);
                    drinkList.add(drinksmap);
                }

                ListAdapter adapter = new SimpleAdapter(addMerchantManually.this, drinkList,
                        R.layout.list_item, new String[]{ "id","price","quantity"},
                        new int[]{R.id.id, R.id.price,R.id.quantity});

              lv.setAdapter(adapter);
            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }



        }

        // build hash set for list view
        public void ListDrwaer() {

        }

    }
}



