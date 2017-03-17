package com.vn.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vn.company.api.Urls;
import com.vn.company.fragment.FrgCity;
import com.vn.company.fragment.FrgCompany;
import com.vn.company.fragment.FrgIndustry;
import com.vn.company.fragment.FrgInfo;
import com.vn.company.fragment.FrgSliddingMST;
import com.vn.company.utils.Utils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        getFirebaseDB();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        changeMenuCompany();

    }

    public void changeMenuCompany() {
        FrgCompany frgCompany = new FrgCompany();
        frgCompany.setApiUrl(Urls.GET_COMPANY);
        frgCompany.setTitle(getString(R.string.congtymoi));
        changeMenu(frgCompany);
    }

    @Override
    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1)
                super.onBackPressed();
            else finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    onBackPressed();
                } else
                    drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_rate:
                String url = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
                Utils.startActivityWithUrl(this, url);
                break;
            case R.id.menu_more_app:
                Utils.startActivityWithUrl(this, Define.DEV_APPS);
                break;
            case R.id.menu_share:
                Utils.shareLink(this, Define.LINK_APP + BuildConfig.APPLICATION_ID);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                changeMenuCompany();
                break;
            case R.id.nav_city:
                FrgCity frgCity = new FrgCity();
                frgCity.setTitle(getString(R.string.tinhthanh));
                changeMenu(frgCity);
                break;
            case R.id.nav_business:
                FrgIndustry frgIndustry = new FrgIndustry();
                changeMenu(frgIndustry);
                break;
            case R.id.nav_mst:
                FrgSliddingMST frgSliddingMST = new FrgSliddingMST();
                changeMenu(frgSliddingMST);
                break;
            case R.id.nav_info:
                changeMenu(new FrgInfo());
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    private void getFirebaseDB() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("VnBusiness");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean showFull = (Boolean) dataSnapshot.child("showFull").getValue();
                MyApplication.getInstance().setShowFull(showFull);
                //Toast.makeText(MainActivity.this, "showFull:" + showFull + ";showBanner:" + showFull, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
