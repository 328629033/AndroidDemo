package com.demo.android.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/3/14.
 */

public class ListFragment extends Fragment {
    ListView listView;
    private List<String> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fg_list, container, false);
        listView = (ListView) contentView.findViewById(R.id.lv_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        list = getList();
        listView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list));
        return contentView;
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("first item");
        list.add("second item");
        list.add("third item");
        list.add("forth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        list.add("fifth item");
        return list;
    }
}
