package com.family.callson;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener {


    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;
    String myCity;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (android.support.v7.widget.Toolbar)this.findViewById(R.id.toolbar);
        ActionBar actionbar = getActionBar();
        //this.setSupportActionBar(toolbar);

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager );
        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, 1);
            //startActivity(intent);
            return true;
        } else if (id == R.id.location_setting) {
            Intent intent = new Intent(MainActivity.this, CityChoose.class);
            intent.putExtra("myCity", myCity);

            startActivityForResult(intent, 2);
        }

        return false;
        //return super.onOptionsItemSelected(item);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        Activity context;

        public ViewPagerAdapter(FragmentManager fm, Activity context) {
            super(fm);
            this.context = context;
        }

        public Fragment getItem(int num) {

            Fragment fragment = new MainFragment(context, num);
            return fragment;

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return getResources().getString(R.string.menu);
                case 1:
                    return getResources().getString(R.string.history);
                case 2:
                    return getResources().getString(R.string.feedback);
                default:
                    return "";
            }
        }

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){

        } else if (requestCode == 2){
            myCity = data.getExtras().getString("myCity");
            MenuItem item = menu.findItem(R.id.location_setting);
            item.setTitle(myCity);
        }
    }
}
