package edu.temple.colorviewer.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.temple.colorviewer.R;
import edu.temple.colorviewer.adapter.ColorAdapter;
import edu.temple.colorviewer.adapter.ColorText;

public class PaletteFragment extends Fragment {
    private List<ColorText> mOolors;
    private ListView mListView;
    private OnColorSelect mListener;
    private ColorAdapter mColorAdapter;
    private Context mContext;
    private View mSelf;
    /**
     * Used to listen for ColorAdapter events
     */
    private ViewGroup.OnClickListener mOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String colorName = ((TextView)v).getText().toString();
            int identifier = mContext.getResources().getIdentifier(colorName, "string", mContext.getPackageName());
            int color = 0;

            // Determine color via string id
            switch (identifier){
                case R.string.GREEN:
                    color = Color.GREEN;
                    break;
                case R.string.BLUE:
                    color = Color.BLUE;
                    break;
                case R.string.RED:
                    color = Color.RED;
                    break;
                case R.string.CYAN:
                    color = Color.CYAN;
                    break;
                case R.string.GRAY:
                    color = Color.GRAY;
                    break;
            }

            // invoke on color select event
            mListener.onColorSelect(color);
        }
    };


    public PaletteFragment() {
        // Required empty public constructor
    }

    /**
     * Creates fragment and initializes ColorAdapter
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSelf     = inflater.inflate(R.layout.fragment_palette, container, false);
        mOolors   = new ArrayList<>();
        mListView = (ListView)mSelf.findViewById(R.id.color_list_view);

        mOolors.add(new ColorText(Color.RED));
        mOolors.add(new ColorText(Color.GREEN));
        mOolors.add(new ColorText(Color.BLUE));
        mOolors.add(new ColorText(Color.CYAN));
        mOolors.add(new ColorText(Color.GRAY));
        mListView.setAdapter(mColorAdapter = new ColorAdapter(mContext = mSelf.getContext(), mOolors, mOCL));
        return (mSelf);
    }

    /**
     * Ensure class implements onColorSelect interface
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnColorSelect) {
            mListener = (OnColorSelect) context;
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

    /**
     * Link to activity
     */
    public interface OnColorSelect {
        // TODO: Update argument type and name
        void onColorSelect(int _color);
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
