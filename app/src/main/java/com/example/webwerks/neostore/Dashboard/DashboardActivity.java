package com.example.webwerks.neostore.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.Login.LoginActivity;
import com.example.webwerks.neostore.MyAccount.MyAccountActivity;
import com.example.webwerks.neostore.MyAccount.MyAccountFragment;
import com.example.webwerks.neostore.ProductListing.ProductListingActivity;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DashboradView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.SliderDots)
    LinearLayout sliderDotspanel;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_chair)
    LinearLayout ll_chair;
    @BindView(R.id.ll_cupboard)
    LinearLayout ll_cupboard;
    @BindView(R.id.ll_sofa)
    LinearLayout ll_sofa;
    @BindView(R.id.ll_table)
    LinearLayout ll_table;
    int category_id;
    TextView full_name, tv_email;
    int images[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    MyCustomPagerAdapter myCustomPagerAdapter;
    private int dotscount;
    private ImageView[] dots;
    DashboardPresenter dashboardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        View headerView = navigationView.getHeaderView(0);
        full_name = (TextView) headerView.findViewById(R.id.full_name);
        tv_email = (TextView) headerView.findViewById(R.id.email);
        getdatafromSp();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getBannerImages();


    }

    @OnClick(R.id.ll_table)
    public void tableClicked() {
        category_id = 1;
        SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
        SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Table");
        openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));


    }

    @OnClick(R.id.ll_chair)
    public void chairClicked() {
        category_id = 2;
        SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
        SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Chair");
        openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));


    }

    @OnClick(R.id.ll_sofa)
    public void sofaClicked() {
        category_id = 3;
        SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
        SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Sofa");
        openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));


    }


    @OnClick(R.id.ll_cupboard)
    public void cupboardClicked() {
        category_id = 4;
        SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
        SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Cupboard");
        openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));


    }


    private void getBannerImages() {
        myCustomPagerAdapter = new MyCustomPagerAdapter(DashboardActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);
        dotscount = myCustomPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.inactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    @Override
    public void openAnothorActivity(String title) {
        Intent intent = new Intent(DashboardActivity.this, ProductListingActivity.class);
        intent.putExtra("Title", title);
        startActivity(intent);
    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            DashboardActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }


    private void getdatafromSp() {
        full_name.setText(SPManager.getInstance(this).retriveString("first_name") + " " + SPManager.getInstance(this).retriveString("last_name"));
        tv_email.setText(SPManager.getInstance(this).retriveString("email"));
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_chairs) {
            category_id = 2;
            SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
            SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Chair");
            openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));
        } else if (id == R.id.nav_logout) {
            SPManager.getInstance(this).saveBoolean("is_active", false);
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_cupboard) {
            category_id = 4;
            SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
            SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Cupboard");
            openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));

        } else if (id == R.id.nav_sofas) {
            category_id = 3;
            SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
            SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Sofa");
            openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));

        } else if (id == R.id.nav_my_orders) {
        } else if (id == R.id.nav_myAccout) {
            Intent intent = new Intent(DashboardActivity.this, MyAccountActivity.class);
            startActivity(intent);
//            MyAccountFragment myAccountFragment = new MyAccountFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.drawer_layout, myAccountFragment, "HELLO").commit();


        } else if (id == R.id.nav_storeLocators) {
        } else if (id == R.id.nav_tables) {
            category_id = 1;
            SPManager.getInstance(getApplicationContext()).saveInt("Product_category_id", category_id);
            SPManager.getInstance(getApplicationContext()).saveString("Product_category", "Table");
            openAnothorActivity(SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
