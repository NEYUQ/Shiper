package com.example.bottomnavigationbardeprecated.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationbardeprecated.MainActivity;
import com.example.bottomnavigationbardeprecated.Model.Order;
import com.example.bottomnavigationbardeprecated.Model.TaiXe;
import com.example.bottomnavigationbardeprecated.R;
import com.example.bottomnavigationbardeprecated.Storage.DataSharedPreferences;
import com.example.bottomnavigationbardeprecated.adapter.NotiAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotiFragment extends Fragment {
    private View v;
    private RecyclerView rcv_notification;
    private List<Order> mList;
    private NotiAdapter adapter ;
    private MainActivity mMainActivity;
    private DatabaseReference reference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false);
        mMainActivity = (MainActivity) getActivity();
        rcv_notification = v.findViewById(R.id.rcv_notification);
        reference = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("ORDER");
        mList = new ArrayList<>();
        adapter = new NotiAdapter(mList, new NotiAdapter.IClickReceive() {
            @Override
            public void ClickReceive(Order order) {
                TaiXe t = DataSharedPreferences.getUser(getContext(),"G_KEY_TAIXE");
                order.setCheck(1);
                order.setNameDriver(t.getTenNhanVien());
                order.setPhoneNumBerDriver(t.getSoDienThoai());
                reference.child(order.getId()).setValue(order, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(getContext(),"Nhận đơn thành công",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void ClickOpen(Order order) {
                new AlertDialog.Builder(mMainActivity).setMessage(order.getInformation()).show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity,LinearLayoutManager.VERTICAL,false);
        rcv_notification.setLayoutManager(linearLayoutManager);
        rcv_notification.setAdapter(adapter);
        ///////////////

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    if((order.getNameDriver().equals(" ") && order.getPhoneNumber().equals(" ")) || order.getCheck()==0)
                        mList.add(order);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }

}
