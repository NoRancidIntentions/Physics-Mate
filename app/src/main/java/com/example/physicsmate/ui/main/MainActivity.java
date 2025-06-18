package com.example.physicsmate.ui.main;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.example.physicsmate.Custom_methods;
import com.example.physicsmate.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    NewViewPagerAdapter newViewPagerAdapter;
    NavigationView nav_view;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ImageView menuButton = findViewById(R.id.menuButton);
        NavigationView nav_view = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        menuButton.setOnClickListener(v -> {
            //open the drawer
            drawerLayout.openDrawer(GravityCompat.START);

        });

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.post(() -> new Thread(() -> {
            UI_Solver_Tools_Linker.LoggerFix.fix();
            Custom_methods.calculateNthDerivative("3x", "x", 1);
        }).start());


        viewPager2 = findViewById(R.id.viewPager2);
        newViewPagerAdapter = new NewViewPagerAdapter(this);

        viewPager2.setAdapter(newViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }


}