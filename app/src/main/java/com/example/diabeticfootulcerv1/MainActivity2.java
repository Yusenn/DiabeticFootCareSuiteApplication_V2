package com.example.diabeticfootulcerv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    ArrayList<HistoryModel> historyModels = new ArrayList<>();

    private RecyclerView mRecycleView;
    private List<Object> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnRefresh;
    private static final String TAG = "MainActivity2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //text = findViewById(R.id.textView2);
        mRecycleView = (RecyclerView) findViewById(R.id.mRecycleHistory);
        //WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        //String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

        //String url = String.format("http://%s/Myproject/getHistory.php",ipAddress);

        //Toast.makeText(getApplicationContext(),ipAddress,Toast.LENGTH_SHORT).show();

        String url1 = "http://172.20.10.7/Myproject/getHistory.php";
        getJSON(url1);


        mRecycleView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);

        mAdapter = new HistoryAdapter(this,viewItems);
        mRecycleView.setAdapter(mAdapter);



    }



    private void getJSON(final String urlWebService)
    {
        class GetJSON extends AsyncTask<Void,Void,String>
        {
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                try{
                    loadIntoListView(s);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try{
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;

                    while((json = bufferedReader.readLine())!=null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();
                }
                catch (Exception e)
                {
                    return null;
                }
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


    private void loadIntoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);


        for (int i = 0; i<jsonArray.length();i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            String feetId = obj.getString("FeetsID");
            String accuracy = obj.getString("Accuracy");
            String condition = obj.getString("Conditions");
            String datetime = obj.getString("Datetime");
            String img64 = obj.getString("Img64");
            HistoryModel historyModel = new HistoryModel(condition,accuracy,feetId,img64,datetime);
            viewItems.add(historyModel);
        }
        mAdapter = new HistoryAdapter(this,viewItems);
        mRecycleView.setAdapter(mAdapter);



    }




}