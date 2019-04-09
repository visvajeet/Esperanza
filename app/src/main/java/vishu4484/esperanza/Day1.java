package vishu4484.esperanza;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by Vishu on 2/10/2017.
 */

public class Day1 extends Fragment {
    String[] time={"09:30–10:15","10:30–12:30","01:00-02:00","10:30–04:30 ","11:30–01:30 ","10:30–04:00 ","11:30–02:15 ","02:15–04:00 ","02:00 – 03:30 ","11:45–01:00","01:00–04:00 "};
    String[] event_name={"Inauguration","Mind Sweeper","Conferral","Backyard Cricket","Impromptu","Pictogram","FIFA","Counter Strike ","Ideathon","The Overseer ","Just Code It "};
    ListView lv;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.day1,container,false);
        lv = (ListView) view.findViewById(R.id.list_view_day1);
        Adapter_listview adapter = new Adapter_listview(this.getActivity(),time,event_name);
        lv.setAdapter(adapter);
        return view;
    }
}
