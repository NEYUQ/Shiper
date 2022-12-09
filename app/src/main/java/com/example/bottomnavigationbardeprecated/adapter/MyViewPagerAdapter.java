package com.example.bottomnavigationbardeprecated.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bottomnavigationbardeprecated.Fragment.DeliveryFragment;
import com.example.bottomnavigationbardeprecated.Fragment.UserFragment;
import com.example.bottomnavigationbardeprecated.Fragment.NotiFragment;

import org.jetbrains.annotations.NotNull;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new NotiFragment();
            case 1:
                return new DeliveryFragment();
            case 2:
                return new UserFragment();
            default:
                return new NotiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
