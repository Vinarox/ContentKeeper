package com.effectivesoft.contentkeeper.mainPage;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.effectivesoft.contentkeeper.R;
import com.effectivesoft.contentkeeper.mainPage.fragments.MusicFragment;
import com.effectivesoft.contentkeeper.mainPage.fragments.PhotoFragment;
import com.effectivesoft.contentkeeper.mainPage.fragments.VideoFragment;

public class MainActivity extends FragmentActivity
        implements VideoFragment.OnVideoFragmentInteractionListener,
        MusicFragment.OnFragmentInteractionListener,
        PhotoFragment.OnPhotoFragmentInteractionListener {

    private final String FRAGMENT_VIDEO = "Video";
    private final String FRAGMENT_MUSIC = "Music";
    private final String FRAGMENT_PHOTO = "Photo";

    private FragmentManager transaction;

    private Fragment fragmentVideo;
    private Fragment fragmentMusic;
    private Fragment fragmentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentVideo = VideoFragment.newInstance();
        fragmentMusic = MusicFragment.newInstance();
        fragmentPhoto = PhotoFragment.newInstance();

        ViewPager viewPager = findViewById(R.id.pager);
        //viewPager.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager()));

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec(FRAGMENT_VIDEO);
        spec.setContent(R.id.fragment_video);
        //spec.setContent((TabHost.TabContentFactory) fragmentVideo);
        spec.setIndicator(
                getResources().getText(R.string.fragment_video),
                getResources().getDrawable(R.drawable.ic_video_library_black_16dp)
        );
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec(FRAGMENT_MUSIC);
        spec.setContent(R.id.fragment_music);
        //spec.setContent((TabHost.TabContentFactory) fragmentMusic);
        spec.setIndicator(
                getResources().getText(R.string.fragment_music),
                getResources().getDrawable(R.drawable.ic_library_music_black_16dp)
        );
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec(FRAGMENT_PHOTO);
        spec.setContent(R.id.fragment_photo);
        //spec.setContent((TabHost.TabContentFactory) fragmentPhoto);
        spec.setIndicator(
                getResources().getText(R.string.fragment_photo),
                getResources().getDrawable(R.drawable.ic_photo_library_black_16dp)
        );
        tabHost.addTab(spec);

        transaction = getSupportFragmentManager();
        transaction.beginTransaction()
                .add(R.id.change_frame, fragmentVideo)
                .commit();

        TabListener tabListener = new TabListener();
        tabHost.setOnTabChangedListener(tabListener);
        //viewPager.addOnPageChangeListener(tabListener);

        testContentProvider();
    }


    void testContentProvider(){
        String[] proj = { MediaStore.Images.Media.DATA };
        //MediaStore.Audio.Media mediaStore;
    }

    class TabListener implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
        public void onTabChanged(String tabId) {
            transaction = getSupportFragmentManager();
            switch (tabId) {
                case FRAGMENT_VIDEO:
                    transaction.beginTransaction()
                            .replace(R.id.change_frame, fragmentVideo)
                            .commit();
                    break;
                case FRAGMENT_MUSIC:
                    transaction.beginTransaction()
                            .replace(R.id.change_frame, fragmentMusic)
                            .commit();
                    break;
                case FRAGMENT_PHOTO:
                    transaction.beginTransaction()
                            .replace(R.id.change_frame, fragmentPhoto)
                            .commit();
                    break;
            }
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {
            System.out.println();
        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }


    public class MyFragmentPageAdapter extends FragmentPagerAdapter {
        private final int count = 3;

        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentVideo;
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
