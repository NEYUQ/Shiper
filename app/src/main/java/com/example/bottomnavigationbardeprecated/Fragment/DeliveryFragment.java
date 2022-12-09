package com.example.bottomnavigationbardeprecated.Fragment;

import android.content.DialogInterface;
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

import com.example.bottomnavigationbardeprecated.Model.Order;
import com.example.bottomnavigationbardeprecated.R;
import com.example.bottomnavigationbardeprecated.Storage.DataSharedPreferences;
import com.example.bottomnavigationbardeprecated.adapter.DeliveryAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class DeliveryFragment extends Fragment {
    private View v;
    private RecyclerView rcv_delivery;
    private List<Order> mList;
    private DeliveryAdapter adapter;
    private DatabaseReference reference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_favorite,container,false);
        reference = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("ORDER");
        rcv_delivery = v.findViewById(R.id.rcv_delivery);
        mList = new ArrayList<>();
        adapter = new DeliveryAdapter(mList, new DeliveryAdapter.IClickReceive() {
            @Override
            public void ClickReceive(Order order) {
                new AlertDialog.Builder(getContext()).setTitle("Warring!!!")
                        .setMessage("Chắc chắn rằng bạn đã giao hàng?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reference.child(order.getId()).child("check").setValue(2, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(getContext(), "Giao hàng thành công",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).setNegativeButton("Cancel",null).show();
            }

            @Override
            public void ClickOpen(Order order) {
                new AlertDialog.Builder(getContext()).setMessage(order.getInformation()).show();
            }

            @Override
            public void ClickCancel(Order order) {
                new AlertDialog.Builder(getContext()).setTitle("Warring!!!")
                        .setMessage("Chắc chắn rằng bạn hủy đơn hàng không?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reference.child(order.getId()).child("check").setValue(3, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(getContext(), "Hủy thành công",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).setNegativeButton("Cancel",null).show();
            }

        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcv_delivery.setLayoutManager(linearLayoutManager);
        rcv_delivery.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    if (order.getPhoneNumBerDriver().equals(DataSharedPreferences.getUser(getContext(),"G_KEY_TAIXE").getSoDienThoai()) && order.getCheck() == 1)
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
