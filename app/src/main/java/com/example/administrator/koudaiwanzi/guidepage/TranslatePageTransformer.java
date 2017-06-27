package com.example.administrator.koudaiwanzi.guidepage;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.koudaiwanzi.R;


public class TranslatePageTransformer implements PageTransformer {

    /**
     * 褰撴垜浠殑ViewPager婊戝姩鐨勬椂鍊欙紝姣忎竴涓〉闈㈤兘浼氬洖璋冭鏂规硶
     * position:褰撳墠绗嚑涓〉闈?
     * view:鏌愪釜椤甸潰瀵瑰簲鐨勮鍥? --- 甯冨眬鐨勮鍥?
     */
    @Override
    public void transformPage(View view, float position) {
        // 娓愬彉鏁堟灉锛屽垽鏂尯闂达紙-1锛?1锛?
        if (position < 1 && position > -1) {
            // 瑙嗗樊鍔犻?熸晥鏋滐紝璁╅噷闈㈢殑鎵?鏈夊瓙绌洪棿閮界粰涓?涓姞閫熷亸绉婚噺
            ViewGroup rl = (ViewGroup) view.findViewById(R.id.rl);
            for (int i = 0; i < rl.getChildCount(); i++) {
                View child = rl.getChildAt(i);
                float factoe = (float) (Math.random() * 2);
                if (child.getTag() == null) {
                    child.setTag(factoe);
                } else {
                    factoe = (Float) child.getTag();
                }
                // 鍔犻?熷亸绉婚噺(鍦╟hild鍘熸潵鐨勪綅缃啀鍔犱竴涓亸绉诲??)
                child.setTranslationX(-position * 200 * factoe);
                child.setTranslationY(position * 100 * factoe);
            }
            // 缂╂斁鏁堟灉
            // 缂╂斁鐨勮寖鍥达細0-1
            rl.setScaleX(Math.max(0.8f, 1 - Math.abs(position)));
            rl.setScaleY(Math.max(0.8f, 1 - Math.abs(position)));

            // 3D缈昏浆鍔ㄧ敾 寰?澶栫炕杞?
            rl.setPivotX(position < 0f ? rl.getWidth() : 0f);
            rl.setPivotY(rl.getHeight() * 0.5f);
            rl.setRotationY(position * 90);

            // 3D缈昏浆鍔ㄧ敾 寰?鍐呯炕杞?
//			rl.setPivotX(position<0f?rl.getWidth():0f);
//			rl.setPivotY(rl.getHeight()*0.5f);
//			rl.setRotationY(-position*90);

            // 缇婅倝涓叉晥鏋?
//			rl.setPivotX(rl.getHeight()*0.5f);
//			rl.setPivotY(rl.getHeight()*0.5f);
//			rl.setRotationY(-position*90);
        }
    }

}
