package com.study.radasm.go;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.study.radasm.go.Adapters.MainViewPagerAdapter;


public class MainActivity extends ActionBarActivity {
    private static final String formatter = "%s,%s";

    private Toolbar toolbar;
    private DrawerLayout main_drawerlayout;

    private ViewPager viewPager;
    private MainViewPagerAdapter viewPagerAdapter;
    private ActionBar supportActionBar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDrawerLayout();

        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);

    }

    /**
     * 初始化侧边栏
     */
    private void initDrawerLayout() {
        supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        main_drawerlayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        drawerToggle = new ActionBarDrawerToggle(this, main_drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        main_drawerlayout.setDrawerListener(drawerToggle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
