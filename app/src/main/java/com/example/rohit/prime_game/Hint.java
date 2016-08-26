package com.example.rohit.prime_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hint extends AppCompatActivity {

    private boolean hinted;
    public static final String finalHint="finalHint";

    private void setAnswer(boolean ans){
        hinted =ans;
        Intent data = new Intent();
        data.putExtra(finalHint,hinted);
        setResult(RESULT_OK,data);
    }
    @Override
    public void onSaveInstanceState(Bundle st) {
        st.putBoolean("hinted", hinted);
        super.onSaveInstanceState(st);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        if (savedInstanceState != null) {
            hinted = savedInstanceState.getBoolean("hinted", false);
            setAnswer(hinted);
            TextView tv = (TextView)findViewById(R.id.hintView);

            if(hinted){
                tv.setText(R.string.hint);
            }
            else
                tv.setText(R.string.wantHint);
        }
        else {
            hinted = false;
            setAnswer(false);

        }
    }


    public  void  giveHint(View v){
        TextView tv = (TextView)findViewById(R.id.hintView);
        tv.setText(R.string.hint);
        hinted = true;
        setAnswer(true);
    }
}
