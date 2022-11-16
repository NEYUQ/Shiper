package com.example.bottomnavigationbardeprecated.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigationbardeprecated.LoginActivity;
import com.example.bottomnavigationbardeprecated.MainActivity;
import com.example.bottomnavigationbardeprecated.Model.Food;
import com.example.bottomnavigationbardeprecated.Model.Order;
import com.example.bottomnavigationbardeprecated.Model.TaiXe;
import com.example.bottomnavigationbardeprecated.R;
import com.example.bottomnavigationbardeprecated.Storage.DataSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private View v;
    private ImageView img_photo_user;
    private TextView tv_name_user,tv_phone_user,tv_id_user;
    private Button btn_logout;
    private MainActivity mMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history,container,false);
        img_photo_user = v.findViewById(R.id.img_photo_user);
        tv_name_user = v.findViewById(R.id.tv_name_user);
        tv_phone_user = v.findViewById(R.id.tv_phone_user);
        tv_id_user = v.findViewById(R.id.tv_id_user);
        btn_logout = v.findViewById(R.id.btn_logout);
        mMain = (MainActivity)getActivity();
        TaiXe t = DataSharedPreferences.getUser(getContext(),"G_KEY_TAIXE");
        tv_id_user.setText(t.getDiaChi());
        tv_phone_user.setText(t.getSoDienThoai());
        tv_name_user.setText(t.getTenNhanVien());
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                DataSharedPreferences.setUser(getContext(),null,"G_KEY_TAIXE");
                mMain.finishAffinity();
            }
        });

        return v;
    }
}
