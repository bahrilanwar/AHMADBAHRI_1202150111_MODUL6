package com.reals.ahmadbahri_1202150111_modul6;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecentFragment extends Fragment {

    private RecyclerView recyclerView;
    //private List<PhotoModel> datas;

    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadData();
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView)view.findViewById(R.id.listRecentRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

    }




    private void loadData(){
        final ProgressDialog loading=new ProgressDialog(getContext());
        loading.setMessage("Getting Data ...");
        loading.setCancelable(false);
        loading.show();

        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference mDatabase;
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabase=mFirebaseDatabase.getReference("photos");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PhotoModel> list = new ArrayList<>();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PhotoModel item = postSnapshot.getValue(PhotoModel.class);
                    list.add(item);
                }
                Log.d("FIREBASE::MDATA","num:"+list.size());

                PhotoAdapter dataAdapter = new PhotoAdapter(list);
                recyclerView.setAdapter(dataAdapter);

                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                loading.dismiss();
            }
        });

    }

}
