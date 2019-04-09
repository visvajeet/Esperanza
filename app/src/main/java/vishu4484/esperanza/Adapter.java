package vishu4484.esperanza;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static vishu4484.esperanza.MainActivity.theme_color;

/**
 * Created by Vishu on 2/9/2017.
 */

public class Adapter extends BaseAdapter {
    private String name[];
    private int img[];
    private Context context;
    private LayoutInflater inflater;

    public Adapter(Context context,int img[],String name[])
    {
        this.context=context;
        this.name=name;
        this.img=img;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
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
            gridView=inflater.inflate(R.layout.model,null);
        }
        else{
            gridView=convertView;
        }
        ImageView imgg=(ImageView)gridView.findViewById(R.id.imageView22);
        TextView tv=(TextView)gridView.findViewById(R.id.textView33);
        tv.setTextColor(Color.parseColor(theme_color));
        imgg.setImageResource(img[position]);
        tv.setText(name[position]);
        return gridView;
    }
}
