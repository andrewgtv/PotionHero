package andrii.goncharenko.potionhero.Activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import andrii.goncharenko.potionhero.R;
import andrii.goncharenko.potionhero.Services.GameSettingsService;

public class BaseActivity  extends FragmentActivity {

    /**Members**/

    protected MediaPlayer backgroundMusic;

    /**Virtual methods**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        startMusic();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic();
    }

    /**Public methods**/

    public void initComponents() {
        if (GameSettingsService.isSoundsOn)
            initSounds();
        if (GameSettingsService.isMusicOn)
            initMusic();
    }

    public void initSounds() {}

    public void initMusic() {}

    public void startMusic() {
        if (backgroundMusic == null || backgroundMusic.isPlaying() || !GameSettingsService.isMusicOn)
            return;
        backgroundMusic.start();
    }

    public void stopMusic() {
        if (backgroundMusic == null || !backgroundMusic.isPlaying() || !GameSettingsService.isMusicOn)
            return;
        backgroundMusic.reset();
    }

    public void playSound(MediaPlayer sound) {
        if (!GameSettingsService.isSoundsOn || sound == null)
            return;
        sound.start();
    }

}
