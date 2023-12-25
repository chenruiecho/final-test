package com.example.musicplayer;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
public class MyService extends Service {
    MediaPlayer mediaPlayer;
    int musicindex=0;
    int[] musics={ R.raw.music1,R.raw.music2,R.raw.music3};
    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化player
        Log.d("cr","MyService:onCreate..");
        mediaPlayer=MediaPlayer.create(this,musics[0]);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("cr","MyService:onStartCommand..");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        Log.d("cr","MyService:onDestroy..");
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("cr","MyService:onBind..");
        MyBinder myBinder=new MyBinder();
        return myBinder;
    }

    public void playpre(){
        if(musicindex>0)
            musicindex--;
            // 如果是第一首歌，切换到最后一首歌
        else
            musicindex = musics.length - 1;
        mediaPlayer.stop();//停止并释放正在播放的歌从而播前一首
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this,musics[musicindex]);
        mediaPlayer.start();
    }

    public void playnext(){
        if (musicindex < musics.length - 1) {
            musicindex++;
        } else {
            musicindex = 0;
        }
        mediaPlayer.stop();//停止并释放正在播放的歌从而播后一首
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this,musics[musicindex]);
        mediaPlayer.start();
    }
    class MyBinder extends Binder{
        public MyBinder(){

        }

        public void playpremusic(){
            playpre();
            Log.d("cr","MyService:playpremusic..");
        }
        public void playnextmusic(){
            playnext();
            Log.d("cr","MyService:playnextmusic..");
        }

        public void Todo(){
            mediaPlayer.start();
            Log.d("cr","MyService:Todo..");
        }
    }
}