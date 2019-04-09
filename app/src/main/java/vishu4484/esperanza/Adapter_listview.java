package vishu4484.esperanza;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static vishu4484.esperanza.MainActivity.height_schedule;
import static vishu4484.esperanza.MainActivity.theme_color;

/**
 * Created by Vishu on 2/9/2017.
 */

public class Adapter_listview extends BaseAdapter {
    private String time[];
    private String event_name[];
   FragmentActivity fa;
    private LayoutInflater inflater;



    public Adapter_listview(FragmentActivity fa, String time[],String event_name[])
    {
        this.fa=fa;
        this.event_name=event_name;
        this.time=time;
    }

    @Override
    public int getCount() {
        return time.length;
    }

    @Override
    public Object getItem(int position) {
        return time[position];
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listview;
        if(convertView==null)
        {
            inflater=(LayoutInflater) fa.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             listview=inflater.inflate(R.layout.model_schedule,parent,false);
           // listview.getLayoutParams().height=100;

        }
        else{
            listview=convertView;
            //listview.getLayoutParams().height=100;
        }

        listview.getLayoutParams().height=height_schedule;
        TextView tv=(TextView)listview.findViewById(R.id.time);
        TextView tv2=(TextView)listview.findViewById(R.id.event_name);
        tv.setTextColor(Color.parseColor(theme_color));
        tv.setText(time[position]);
        tv2.setText(event_name[position]);
        return listview;
    }
}
