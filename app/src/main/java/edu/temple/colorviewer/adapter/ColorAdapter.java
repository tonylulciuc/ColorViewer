package edu.temple.colorviewer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.temple.colorviewer.R;

public class ColorAdapter extends ArrayAdapter{
    private List<ColorText> mlItems;
    private ViewGroup.OnClickListener mOCL;

    public ColorAdapter(Context _context, List<ColorText> _items, ViewGroup.OnClickListener _clickListener){
        super(_context, R.layout.fragment_palette,  _items);
        mlItems = _items;
        mOCL = _clickListener;
    }

    @Override
    public View getView(int _i, View _view, ViewGroup _viewgroup){
        View v = _view;
        TextView tv;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_palette_design, null);
        }

        tv = (TextView)v.findViewById(R.id.color_text);
        tv.setOnClickListener(mOCL);
        switch (mlItems.get(_i).color){
            case Color.RED:
                tv.setBackgroundColor(Color.RED);
                tv.setText(R.string.RED);
                break;
            case Color.GREEN:
                tv.setBackgroundColor(Color.GREEN);
                tv.setText(R.string.GREEN);
                break;
            case Color.BLUE:
                tv.setBackgroundColor(Color.BLUE);
                tv.setText(R.string.BLUE);
                break;
            case Color.CYAN:
                tv.setBackgroundColor(Color.CYAN);
                tv.setText(R.string.CYAN);
                break;
            case Color.GRAY:
                tv.setBackgroundColor(Color.GRAY);
                tv.setText(R.string.GRAY);
                break;
        }

        return (v);
    }
}