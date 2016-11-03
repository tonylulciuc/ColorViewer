package edu.temple.colorviewer.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.temple.colorviewer.R;

public class CanvasFragment extends Fragment {
    private OnColorChange mListener;
    private View mSelf; // Fragment reference

    public CanvasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Handler handler = new Handler(Looper.getMainLooper());

        // Inflate the layout for this fragment
        mSelf           = inflater.inflate(R.layout.fragment_canvas, container, false);

        // Delay to allow fragment to initialize
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setBackgroundColor(Color.BLACK);
            }
        }, 500);

        return (mSelf);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnColorChange) {
            mListener = (OnColorChange) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnColorChange");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnColorChange {
        // TODO: Update argument type and name
        void onColorChange(int _color);
    }

    public void setBackgroundColor(int _color){
        mSelf.setBackgroundColor(_color);
    }

    /**
     * Set fragment params
     * @param _lparams [in] new params
     */
    public void setParams(ViewGroup.LayoutParams _lparams){
        mSelf.setLayoutParams(_lparams);
    }

    /**
     * Get fragment params
     * @return fragment params
     */
    public ViewGroup.LayoutParams getLayoutParams(){
        return (mSelf.getLayoutParams());
    }
}
