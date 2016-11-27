package com.example.chiara.test;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {
    private GoogleApiClient client;
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = (Button) this.findViewById(R.id.btn_1);
        Button two = (Button) this.findViewById(R.id.btn_2);
        Button three = (Button) this.findViewById(R.id.btn_3);
        Button four = (Button) this.findViewById(R.id.btn_4);

        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.george);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.jamesbrown);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.snap);
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.mchammer);

        one.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if ( counter == 0 ) { mp1.start(); counter = 1;}
                else {mp1.pause(); mp1.seekTo(0); counter = 0;}
            }
        });

        two.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                if ( counter == 0 ) { mp2.start(); counter = 1;}
                else {mp1.pause();  mp2.seekTo(0); counter = 0;}
            }
        });

        three.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if ( counter == 0 ) { mp3.start(); counter = 1;}
                else { mp1.pause();  mp3.seekTo(0); counter = 0;}
            }
        });

        four.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if ( counter == 0 ) { mp4.start(); counter = 1;}
                else { mp1.pause();  mp4.seekTo(0); counter = 0;}
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}