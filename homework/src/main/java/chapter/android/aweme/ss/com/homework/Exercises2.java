package chapter.android.aweme.ss.com.homework;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private int group;
    private TextView answer;
    private RelativeLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout);
        answer = findViewById(R.id.tv_center);
        layout = findViewById(R.id.layout);
        answer.setText((getAllChildViewCount(layout)-1)+"");//layout本身也是一个view,将其减去，得出页面上可以看到的总view数
    }


    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        if(view==null)
            return 0;

        if(view instanceof ViewGroup){
            group++;
            for(int i=0;i<((ViewGroup)view).getChildCount();i++){

                View child = ((ViewGroup) view).getChildAt(i);

                if(child instanceof ViewGroup)
                    group+=getAllChildViewCount(child);
                else
                    group++;
            }
        }

        return group;
    }
}
