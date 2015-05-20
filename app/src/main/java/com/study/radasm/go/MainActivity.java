package com.study.radasm.go;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.study.radasm.go.Adapters.MainViewPagerAdapter;
import com.study.radasm.go.Fragments.SuperAwesomeCardFragment;


public class MainActivity extends ActionBarActivity {
    private static final String formatter = "%s,%s";

    private Toolbar toolbar;
    private DrawerLayout main_drawerlayout;

    private ViewPager viewPager;
    private PagerTabStrip pager_tab_strip;
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

        pager_tab_strip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);

        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        usePlette2ViewPager();

        viewPager.setAdapter(viewPagerAdapter);

    }

    private void usePlette2ViewPager() {

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                colorChange(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 使用palette去变换viewpager中每个页面的颜色
     */
    private void colorChange(int position) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), SuperAwesomeCardFragment.getBackgroundBitmapPosition(position));

        /**
         * Palette是使用builder进行产生的，并且应该总是运行在后台线程中，palette允许和bitmap之间两种关联方式：同步和异步
         */
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                int rgb = vibrantSwatch.getRgb();
                pager_tab_strip.setBackgroundColor(rgb);

            }
        });

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
