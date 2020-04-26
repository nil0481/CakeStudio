package com.example.cakestudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
//    TextView demotext;
    ArrayList<Cake> cakes;
    private static String json_url="http://kekizadmin.com/kekiz_api/actions.php?action=get_cakes&category=27";
//    private static String json_url="http://starlord.hackerearth.com/studio";
    Adapter adapter;
    String halfImg="http://kekizadmin.com/uploads/catrgories/";
    Button cartbutton,selectbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.cakelist);
        cakes=new ArrayList<>();
        cartbutton=findViewById(R.id.cartbutton);
        selectbutton=findViewById(R.id.selectbutton);

        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CakeOpen.class);
                startActivity(intent);


            }
        });
        selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,SelectOpen.class);
                startActivity(intent2);
            }
        });
        extractCakes();

    }

    private void extractCakes(){
//        final TextView demotext=(TextView)findViewById(R.id.demotext);
        RequestQueue queue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("data");


                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject cakeObject=jsonArray.getJSONObject(i);
                                String cakeName=cakeObject.getString("cake_name");

                                JSONArray wlplist = cakeObject.getJSONArray("w_l_p");
                                String weight="";
                                String price="";
                                String[] ImgUrl={};
                                String fimalurl="";


                                for(int j = 0; j < wlplist.length();j++) {
                                    JSONObject wlpElement = wlplist.getJSONObject(j);
                                    weight=wlpElement.getString("weight");
                                    price="RS."+wlpElement.getString("price");
                                    ImgUrl=(wlpElement.getString("pictures")).split("file_name\":\"");

                                    fimalurl=halfImg+((ImgUrl[1]).split("\"")[0]);
//                                    Log.d("tag1", cakeName);
//                                    Log.d("tag", fimalurl);
                                    break;
                                }
                                Cake cake = new Cake();
                                cake.setCake_name(cakeName);
                                cake.setWeight(weight);
                                cake.setPrice(price);
                                cake.setImgUrl(fimalurl);
                                cakes.add(cake);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                adapter= new Adapter(getApplicationContext(),cakes);
                                recyclerView.setAdapter(adapter);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("tag", "onErrorResponse: "+error.getMessage());

                    }
                });
//
        queue.add(jsonObjectRequest);
    }

}
