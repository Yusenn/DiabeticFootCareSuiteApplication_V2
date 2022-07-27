package com.example.diabeticfootulcerv1;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.net.Uri;
import android.os.Bundle;

import org.videolan.libvlc.media.MediaPlayer;


public class MainActivity4 extends AppCompatActivity {

    private static final String url = "rtsp://192.168.1.10:8080/";

    EditText addrField;
    Button btnConnect;
    VideoView streamView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);



        addrField = (EditText)findViewById(R.id.addr);
        btnConnect = (Button)findViewById(R.id.connect);
        streamView = (VideoView)findViewById(R.id.streamview);

        btnConnect.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                String s = addrField.getEditableText().toString();
                playStream(s);
            }});

    }

    private void playStream(String src){
        Uri UriSrc = Uri.parse(src);
        if(UriSrc == null){
            Toast.makeText(MainActivity4.this,
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        }else {
            MediaController mediaController = new MediaController(this);

            mediaController.setAnchorView(streamView);
            Uri uri = Uri.parse(src);
            streamView.setVideoURI(uri);
            streamView.setMediaController(mediaController);
            streamView.requestFocus();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //streamView.stopPlayback();

    }

}