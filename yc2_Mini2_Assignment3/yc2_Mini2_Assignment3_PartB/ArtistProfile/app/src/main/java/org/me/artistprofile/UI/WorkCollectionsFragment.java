package org.me.artistprofile.UI;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.VideoView;
import org.me.artistprofile.Exception.NotFoundException;
import org.me.artistprofile.Exception.fixException;
import org.me.artistprofile.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by caoyi on 16/3/24.
 */
public class WorkCollectionsFragment extends Fragment implements View.OnClickListener {
    MediaPlayer song1play;
    MediaPlayer song2play;
    VideoView video1play;
    VideoView video2play;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_work_collections,container,false);
        Button playButton1 = (Button)view.findViewById(R.id.playButton1);
        Button pauseButton1 = (Button)view.findViewById(R.id.pauseButton1);
        Button stopButton1 = (Button) view.findViewById(R.id.stopButton1);

        Button playButton2 = (Button)view.findViewById(R.id.playButton2);
        Button pauseButton2 = (Button)view.findViewById(R.id.pauseButton2);
        Button stopButton2 = (Button) view.findViewById(R.id.stopButton2);

        playButton1.setOnClickListener(this);
        pauseButton1.setOnClickListener(this);
        stopButton1.setOnClickListener(this);

        playButton2.setOnClickListener(this);
        pauseButton2.setOnClickListener(this);
        stopButton2.setOnClickListener(this);
        FileOutputStream fos=null ;
        try {
            fos = getContext().openFileOutput("log.txt", Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            AssetFileDescriptor afd1 = getResources().getAssets().openFd("song1.mov");
//            song1play.setDataSource(afd1.getFileDescriptor());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//       try{
//           AssetFileDescriptor afd = getResources().getAssets().openFd("song2.mov");
//           song2play.setDataSource(afd.getFileDescriptor());
//       }catch (Exception e){
//
//       }
        song1play=MediaPlayer.create(getActivity(), R.raw.ladygaga);
        song2play=MediaPlayer.create(getActivity(),R.raw.ladygaga2);



        Button playVideoButton1 = (Button) view.findViewById(R.id.playVideoButton1);
        Button playVideoButton2 = (Button) view.findViewById(R.id.playVideoButton2);

        Button pauseVideoButton1 = (Button)view.findViewById(R.id.pauseVideoButton1);
        Button pauseVideoButton2 = (Button)view.findViewById(R.id.pauseVideoButton2);

        Button stopVideoButton1 = (Button)view.findViewById(R.id.replayVideoButton1);
        Button stopVideoButton2 = (Button)view.findViewById(R.id.replayVideoButton2);

        playVideoButton1.setOnClickListener(this);
        playVideoButton2.setOnClickListener(this);

        pauseVideoButton1.setOnClickListener(this);
        pauseVideoButton2.setOnClickListener(this);

        stopVideoButton1.setOnClickListener(this);
        stopVideoButton2.setOnClickListener(this);

        video1play=(VideoView) view.findViewById(R.id.videoView1);
        video2play=(VideoView) view.findViewById(R.id.videoView2);
        String mypackage="org.me.artistprofile";
        String myVideoName1="samplevideo1";
        String myVideoName2="samplevideo2";
        // try{
        //     if(!myVideoName1.equals("samplevideo1")){

        //         throw new NotFoundException("Can not find the media file!");
        //     }
        // } catch (NotFoundException ex){
        //     try {
        //         fos.write(ex.getmessage().getBytes());
        //     } catch (IOException e1) {
        //         e1.printStackTrace();
        //     }
        //     fixException fixException=new fixException();
        //     myVideoName1=fixException.fix();
        // }

        Uri video1uri=Uri.parse("android.resource://"+mypackage+"/raw/"+myVideoName1);
        Uri video2uri=Uri.parse("android.resource://"+mypackage+"/raw/"+myVideoName2);

        video1play.setVideoURI(video1uri);
        video2play.setVideoURI(video2uri);
        /**play video from asset*/
        return  view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.playVideoButton1 :
                if(!video1play.isPlaying()) {
                        video1play.start();
                }
                break;
            case R.id.pauseVideoButton1:
                if(video1play.isPlaying()) {

                    video1play.pause();
                }
                break;
            case R.id.replayVideoButton1:
                if(video1play.isPlaying()) {
                    video1play.resume();
                }
                break;

            case R.id.playVideoButton2 :
                if(!video2play.isPlaying()) {
                    video2play.start();
                }
                break;
            case R.id.pauseVideoButton2:
                if(video2play.isPlaying()) {
                    video2play.pause();
                }
                break;
            case R.id.replayVideoButton2:
                if(video2play.isPlaying()) {
                    video2play.resume();
                }
                break;



            case R.id.playButton1 :
                if(!song1play.isPlaying()) {
//                    Log.d("playsongs", video1play.getTransitionName());
                    song1play.start();
                }
                break;
            case R.id.pauseButton1:
                if(song1play.isPlaying()) {
                    song1play.pause();
                }
                break;
            case R.id.stopButton1:
                if(song1play.isPlaying()) {
                    song1play.reset();
                }
                break;

            case R.id.playButton2 :
                if(!song2play.isPlaying()) {
                    song2play.start();
                }
                break;
            case R.id.pauseButton2:
                if(song2play.isPlaying()) {
                    song2play.pause();
                }
                break;
            case R.id.stopButton2:
                if(song2play.isPlaying()) {
                    song2play.reset();
                }
                break;
            default:
                break;
        }

    }
}
