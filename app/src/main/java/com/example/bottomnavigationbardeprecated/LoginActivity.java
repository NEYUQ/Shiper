package com.example.bottomnavigationbardeprecated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bottomnavigationbardeprecated.Model.Order;
import com.example.bottomnavigationbardeprecated.Model.TaiXe;
import com.example.bottomnavigationbardeprecated.Storage.DataSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_username,edt_password;
    private Button btn_login;
    private DatabaseReference reference;
    private CheckBox cb;
    private ProgressBar pb;
    private TaiXe taixe = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_username = findViewById(R.id.username);
        edt_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btnlogin);
        if (DataSharedPreferences.getUser(LoginActivity.this,"G_KEY_TAIXE")!=null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        reference = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("AccountEmployee");
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                CheckTaiKhoan(username, password);

            }
        });


    }

    private void CheckTaiKhoan(String username, String password) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean check = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    taixe = dataSnapshot.getValue(TaiXe.class);
                    if((taixe.getTaiKhoan().equals(username) || taixe.getSoDienThoai().equals(username)) && taixe.getTaiKhoan().equals(password)){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        DataSharedPreferences.setUser(LoginActivity.this,taixe,"G_KEY_TAIXE");
                        check = true;
                        break;
                    }
                }
                if(!check)
                    Toast.makeText(LoginActivity.this,"Không đúng tài khoản",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}