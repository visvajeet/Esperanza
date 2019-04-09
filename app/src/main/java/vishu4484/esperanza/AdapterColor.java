package vishu4484.esperanza;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import static vishu4484.esperanza.MainActivity.height_color_pick;

/**
 * Created by Vishu on 2/9/2017.
 */

public class AdapterColor extends BaseAdapter {
    private String color[];
    private Context context;
    private LayoutInflater inflater;

    public AdapterColor(Context context, String color[])
    {
        this.context=context;
        this.color=color;
    }

    @Override
    public int getCount() {
        return color.length;
    }

    @Override
    public Object getItem(int position) {
        return color[position];
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        if(convertView==null)
        {
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView=inflater.inflate(R.layout.model_gvcp,parent,false);

        }
        else{
            gridView=convertView;
        }
        gridView.getLayoutParams().height=height_color_pick;

        gridView.setBackgroundColor(Color.parseColor(color[position]));



        return gridView;
    }
}
