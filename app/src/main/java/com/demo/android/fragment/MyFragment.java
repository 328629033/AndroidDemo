package com.demo.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.android.R;

/**
 * Created by herr.wang on 2017/7/7.
 */

public class MyFragment extends Fragment {
    public static MyFragment newInstance(Argument argument){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("argument",argument);
        myFragment.setArguments(bundle);
        return myFragment;

    }

    FragmentCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentCallback){
            callback = (FragmentCallback) context;
        }else{
            System.out.print("error.");
        }
    }

    LoadingDialog dialog;
    Argument argument;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        argument = (Argument) getArguments().getSerializable("argument");
        View contentView = inflater.inflate(R.layout.fragment_my, container, false);
        TextView view = (TextView) contentView.findViewById(R.id.tv_app);
        view.setText(argument.name);
        dialog = new LoadingDialog(getContext());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                argument.name = "fragment";
                Toast.makeText(getContext(), "toast", Toast.LENGTH_SHORT).show();
//                dialog.show();
                if(callback != null){
                    callback.onClick();
                }
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.dismiss();
//                    }
//                }, 5000);
            }
        });

        return contentView;
    }

    public void toast(){
        Toast.makeText(getContext(), argument.name, Toast.LENGTH_SHORT).show();
    }
}
