package vishu4484.esperanza;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Vishu on 2/10/2017.
 */
public class Day2 extends Fragment {
    String[] time={"09:15–11:15 ","01:00–02:00 ","09:00–02:30 ","12:15–02:15 ","09:00–11:00 ","09:30–12:30 ","09:30–12:30 ","02:15–03:15 ","11:15–02:15 ","03:15–04:15 "};
    String[] event_name={"Silent Speak ","Conferral","Backyard Cricket ","Mock CID ","Pictogram","FIFA","Counter Strike ","The Overseer ","Webphoria","Valedictory "};
    ListView lv;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.day2,container,false);
        lv = (ListView) view.findViewById(R.id.list_view_day2);
        Adapter_listview adapter = new Adapter_listview(this.getActivity(), time,event_name);
        lv.setAdapter(adapter);
        return view;

    }
}
