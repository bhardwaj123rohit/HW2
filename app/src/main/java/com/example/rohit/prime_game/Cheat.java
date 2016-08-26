package com.example.rohit.prime_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cheat extends AppCompatActivity {
    boolean ans;
    private TextView ansView;
    boolean cheated=false;
    public static final String honesty="isHonest";
    Button letsCheat;


    public void setAnswer(boolean ans){
        Intent data = new Intent();
        data.putExtra(honesty,cheated);
        setResult(RESULT_OK,data);
    }
    @Override
    public void onSaveInstanceState(Bundle st) {
        st.putBoolean("cheated", cheated);
        st.putBoolean("ans",ans);

        super.onSaveInstanceState(st);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        // to make sure that user haven't cheated utill button is clicked

        ansView=(TextView)findViewById(R.id.cheatView);
        if (savedInstanceState != null) {
            cheated = savedInstanceState.getBoolean("cheated", false);
            ans = savedInstanceState.getBoolean("ans", false);
            if(cheated) {
                ansView.setText(ans ? R.string.ansT : R.string.ansF);
            }

            else
                ansView.setText(R.string.startCheat);
        }
        else {
              ans = getIntent().getBooleanExtra(Start.CheatAns, false);
              cheated = false;
              ansView.setText(R.string.startCheat);
        }




        letsCheat=(Button)findViewById(R.id.letsCheat);

        setAnswer(cheated);
        letsCheat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(ans)
                    ansView.setText(R.string.ansT);

                else
                    ansView.setText(R.string.ansF);

                cheated = true;

                setAnswer(cheated);
            }


        });
    }
}
