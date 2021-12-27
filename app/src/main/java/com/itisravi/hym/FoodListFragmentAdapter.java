package com.itisravi.hym;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FoodListFragmentAdapter extends FragmentStateAdapter {


    public FoodListFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {

        if (position == 1) {
            return new UserFoodList();
        }
        return new DefaultFoodList();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
