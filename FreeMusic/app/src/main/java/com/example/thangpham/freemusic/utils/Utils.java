package com.example.thangpham.freemusic.utils;

;import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by ThangPham on 11/22/2017.
 */

public class Utils {
    public static void openFragment(FragmentManager fragmentManagert, int layoutId, Fragment fragment) {
        // quản lý việc thêm, sửa, xóa or thay thế của Fragment
        FragmentTransaction fragmentTransaction = fragmentManagert.beginTransaction();
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void rotateImage(ImageView imageView, boolean isPlaying) {

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator()); // quay đều
        rotateAnimation.setDuration(10000);
        // nếu quay rồi thì k gọi lại hàm startAnimation nữa
        // nhạc đang chạy
        if (isPlaying) {
            if (imageView.getAnimation() == null) {
                imageView.startAnimation(rotateAnimation);
            }
        }// nhạc chưa chạy
        else {
            imageView.setAnimation(null);
        }

    }
    public static String convertTime(int time) //time: miniseconds
    {
        int min = time / (60000);
        int sec = (time- min*60000)/1000;

        return String.format("%02d:%02d",min,sec);
    }
}
