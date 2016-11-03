package edu.temple.colorviewer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import edu.temple.colorviewer.fragments.PaletteFragment;
import edu.temple.colorviewer.fragments.CanvasFragment;

public class ColorActivity extends AppCompatActivity implements CanvasFragment.OnColorChange,
        PaletteFragment.OnColorSelect{
    private CanvasFragment mCanvasFragment;
    private PaletteFragment mPaletteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivitiy_color_viewer);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Handler handler        = new Handler();
        Fragment frag;

        // Does the fragment already exist
        if ((frag = getFragmentManager().findFragmentByTag("color_view")) != null) {
            mCanvasFragment = (CanvasFragment)frag;
            mPaletteFragment = (PaletteFragment)getFragmentManager().findFragmentByTag("color_list");
            ft.replace(R.id.color_frag, mCanvasFragment, "color_view");
            ft.replace(R.id.list_frag, mPaletteFragment, "color_list");
            System.gc();
        }else{
            mCanvasFragment = new CanvasFragment();
            mPaletteFragment = new PaletteFragment();
            ft.add(R.id.color_frag, mCanvasFragment, "color_view");
            ft.add(R.id.list_frag, mPaletteFragment, "color_list");
        }

        ft.commit();

        // set fragment size based on orientation
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setSize(getResources().getConfiguration().orientation);
            }
        }, 500);
    }

    // Not really needed
    @Override
    public void onColorChange(int _color){
        mCanvasFragment.setBackgroundColor(_color);
    }

    // When method is invoked it will change second fragment color
    @Override
    public void onColorSelect(int _color){
        onColorChange(_color);
    }

    // Handle orientation change
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setSize(newConfig.orientation);
    }

    /**
     * Set fragment size base on orientation
     * @param _orientation [in] orientation
     */
    private void setSize(int _orientation){
        ViewGroup.LayoutParams lpColorView = mCanvasFragment.getLayoutParams();
        ViewGroup.LayoutParams lpColorList = mPaletteFragment.getLayoutParams();
        WindowManager wm = getWindowManager();
        Point size = new Point();

        wm.getDefaultDisplay().getSize(size);

        // Checks the orientation of the screen
        if (_orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lpColorView.height = (int) ((double) size.x * 0.75);
            lpColorList.height = (int) ((double) size.x * 0.25);
        } else if (_orientation == Configuration.ORIENTATION_PORTRAIT) {
            lpColorView.height = (int) ((double) size.y * 0.75);
            lpColorList.height = (int) ((double) size.y * 0.25);
        }

        mCanvasFragment.setParams(lpColorView);
        mPaletteFragment.setParams(lpColorList);
    }
}
