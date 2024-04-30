package com.example.bccrssv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bccrssv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_HOME = R.id.homeMenu;
    private static final int MENU_LOCATION = R.id.locationMenu;
    private static final int MENU_SETTINGS = R.id.settingsMenu;
    ActivityMainBinding binding;

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        replaceFragment(new MapsFragment());
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == MENU_HOME) {
              //  replaceFragment(new HomeFragment());
                replaceFragment(new MapsFragment());
            } else if (itemId == MENU_LOCATION) {
                replaceFragment(new LocationFragment());
            } else if (itemId == MENU_SETTINGS) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }
}