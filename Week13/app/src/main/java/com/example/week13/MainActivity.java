package com.example.week13;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;
    VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoview = (VideoView) this.findViewById(R.id.videoview);
        MediaController mc = new MediaController(this) {
            @Override
            public void show() { super.show(0); }
        };
        videoview.setMediaController(mc);
        videoview.setVideoURI(Uri.parse
                ("android:resource://" + getPackageName() + "/" + R.raw.trailer));
        videoview.start();
        videoview.requestFocus();
        mc.setEnabled(true);
        mc.show(0);
    }

    public void startResAudio(View v) {
        mp = MediaPlayer.create(this, R.raw.old_pop);
        mp.start();
        Log.i("mjc", "음악 시작");
    }

    public void stopResAudio(View v) {
        if (mp != null) {
            mp.stop();
            mp.release();
            Log.i("mjc", "음악 중지");
        }
        mp = null;
    }
}