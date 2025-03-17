package com.example.physicsmate.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.jetbrains.annotations.NotNull;

public class NewViewPagerAdapter extends FragmentStateAdapter {

    public NewViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int i) {
        switch (i) {
            case 1:
                return new UI_Solver_Tools_Linker.Tools_frag();
            case 2:
                return new UI_Solver_Tools_Linker.ExtLinks();
            default:
                return new UI_Solver_Tools_Linker.Solver_frag();
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
