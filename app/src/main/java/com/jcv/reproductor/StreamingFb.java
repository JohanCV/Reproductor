package com.jcv.reproductor;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;;

public class StreamingFb extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mMediaplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming_fb);

        mMediaplayer = new MediaPlayer();
        mMediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        fetchAudioUrlFromFirebase();
    }


    private void fetchAudioUrlFromFirebase() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/reproductor-258c8.appspot.com/o/Paulo%20Londra%20-%20Adan%20y%20Eva%20(Official%20Video).mp3?alt=media&token=808461ea-2377-48e4-9c64-8580842db922");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mMediaplayer.setDataSource(url);
                    // wait for media player to get prepare
                    mMediaplayer.setOnPreparedListener(StreamingFb.this);
                    mMediaplayer.prepareAsync();
                    mMediaplayer.setOnCompletionListener(StreamingFb.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("TAG", e.getMessage());
                    }
                });

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mMediaplayer.pause();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        mMediaplayer.pause();
    }
}
