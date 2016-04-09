package org.me.artistprofile.UI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;

import org.me.artistprofile.R;
/**
 * Created by caoyi on 16/3/25.
 */
public class WallPaperFragment extends Fragment implements View.OnClickListener {
    ImageView image;
    int drawableId;
    private static final int[] images=new int[]     {R.drawable.ladygaga1,R.drawable.ladygaga2,R.drawable.ladygaga3,R.drawable.ladygaga4};
    int len=images.length;
    static int curr=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_wallpaper,container,false);
         image=(ImageView)view.findViewById(R.id.imageView);
        Button button=(Button)view.findViewById(R.id.next_button);
        button.setOnClickListener(this);
        image.setImageResource(R.drawable.ladygaga1);
        return view;
    }

    @Override
    public void onClick(View v) {
        curr++;
        if(curr<len){
            image.setImageResource(images[curr]);
        }else
        {
            curr=-1;
        }

    }

}
