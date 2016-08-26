package com.example.rohit.prime_game;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends AppCompatActivity {
    Integer num = null;
    boolean chk;
    boolean hintTaken;
    boolean answerTaken;

    public static final String CheatAns="answer";
    //  List to store all prime numbers till 1000
    int list[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    @Override
    public void onSaveInstanceState(Bundle st) {
        st.putInt("num", num);
        st.putBoolean("hintTaken",hintTaken);
        st.putBoolean("answerTaken",answerTaken);
        super.onSaveInstanceState(st);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (savedInstanceState != null) {
            num = savedInstanceState.getInt("num");
            hintTaken=savedInstanceState.getBoolean("hintTaken");
            answerTaken=savedInstanceState.getBoolean("answerTaken");
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(getResources().getString(R.string.kya) + " " + num.toString() + " " + getResources().getString(R.string.question));
            //  Log.v("Saved State", num.toString());
            if(hintTaken==true){
                Button b = (Button) findViewById(R.id.button4);
                b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.close, 0, 0, 0);
            }
            if(answerTaken==true){
                Button b = (Button) findViewById(R.id.button3);
                b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.close, 0, 0, 0);

            }

        } else {
          updateQues();
        }

    }
    public void updateQues(){
        TextView tv = (TextView) findViewById(R.id.textView);
        num = (int) (Math.random() * 1000 + 1);
        tv.setText(getResources().getString(R.string.kya) + " " + num.toString() + " " + getResources().getString(R.string.question));
        Button b = (Button) findViewById(R.id.button4);
        hintTaken=false;
        b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ok, 0, 0, 0);
        Button b2 = (Button) findViewById(R.id.button3);
        answerTaken=false;
        b2.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ok, 0, 0, 0);
    }

    public boolean check(Integer num){
        boolean res=false;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == num) {
                res = true;
                break;
            }
        }
        return  res;

    }
    // This function creates a random number to generate a new question
    public void nextQ(View v) {
    updateQues();
    }
    //  This function verifies the button clicked and updates the question if the user answered the correct answer
    public void verify(View v) {

        TextView tv = (TextView) findViewById(R.id.textView);
        chk = check(num);
        if (v.getId() == R.id.fbutton && !chk) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
           updateQues();

        } else if (v.getId() == R.id.tbutton && chk) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            updateQues();
        } else
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();

    }

    public void showAnswer(View v){
        Intent intent = new Intent(Start.this,Cheat.class);
        intent.putExtra(CheatAns,check(num));
        startActivityForResult(intent,1);
    }

    public void showHint(View v){

        Intent intent = new Intent(Start.this,Hint.class);
        startActivityForResult(intent,2);

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==1) {
            boolean res = data.getBooleanExtra(Cheat.honesty,false);
            if(res || answerTaken){
                Toast.makeText(this,R.string.badCheating,Toast.LENGTH_SHORT).show();
                answerTaken=true;
            }
            else {
                Toast.makeText(this, R.string.goodCheating, Toast.LENGTH_SHORT).show();
                answerTaken=false;
            }
        }
        if(requestCode==2){
            boolean res = data.getBooleanExtra(Hint.finalHint,false);
            if(res || hintTaken){
                Toast.makeText(this,R.string.hintGiven,Toast.LENGTH_SHORT).show();
                hintTaken=true;
            }
            else {
                Toast.makeText(this, R.string.noHintGiven, Toast.LENGTH_SHORT).show();
                hintTaken=false;
            }
        }
        if(hintTaken==true){
            Button b = (Button) findViewById(R.id.button4);
            b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.close, 0, 0, 0);
        }
        if(answerTaken==true){
            Button b = (Button) findViewById(R.id.button3);
            b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.close, 0, 0, 0);

        }

    }
}
