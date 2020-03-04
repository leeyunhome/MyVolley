package com.egloos.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;

    RecyclerView recyclerView;
    SchoolZoneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SchoolZoneAdapter();
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = editText.getText().toString();
                request(urlStr);
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public void request(String urlStr){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄");
    }

    public void processResponse(String response){
        Gson gson = new Gson();

        SchoolZoneList schoolZoneList = gson.fromJson(response, SchoolZoneList.class);
        int num = schoolZoneList.gangnamChildSaveInfo.row.size();
        println("스쿨존 정보의 수 : " + schoolZoneList.gangnamChildSaveInfo.list_total_count);
        println("정보의 수 : " + schoolZoneList.gangnamChildSaveInfo.row.size());
        for (int i = 0; i < num; ++i){
//            println("cctv 수 : " + schoolZoneList.gangnamChildSaveInfo.row.get(i).manage_nm);
            SchoolZone schoolZone = schoolZoneList.gangnamChildSaveInfo.row.get(i);
            adapter.addItem(schoolZone);
        }

        adapter.notifyDataSetChanged();
    }
    public void println(String data){
        textView.append(data + "\n");
    }
}
