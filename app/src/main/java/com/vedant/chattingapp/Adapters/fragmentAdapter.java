package com.vedant.chattingapp.Adapters;

import com.vedant.chattingapp.tab_fragments.Status_Fragment;
import com.vedant.chattingapp.tab_fragments.call_Fragment;
import com.vedant.chattingapp.tab_fragments.chat_Fragment;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class fragmentAdapter extends FragmentStatePagerAdapter {

    public fragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new chat_Fragment();

            case 1:
                return new Status_Fragment();

            case 2:
                return new call_Fragment();

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;

        if(position == 0)
        {
            title="CHATS";
        } if(position == 1)
        {
            title="STATUS";
        } if(position == 2)
        {
            title="CALL";
        }

        return title;
    }
}
