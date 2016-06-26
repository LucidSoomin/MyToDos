package com.happy.mytodos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText editTextID, editTextPW;
    SharedPreferences sharedPref;
    String packagename = "com.happy.mytodos;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPW = (EditText) findViewById(R.id.editTextPW);

        sharedPref = this.getSharedPreferences(packagename, Context.MODE_PRIVATE); //다른 어플에서는 볼 수 없도록 private
        //컴퓨터는 '불러오기'를 먼저 한다: id, pw 저장되어 있는지 확인 후 있으면 세팅, 없으면 내버려 둠
        String defaultValue = "";
        String savedID = sharedPref.getString("id", defaultValue); //id의 값을 가져와라. 없으면 defaultValue, 즉 빈 문자열으로 세팅.
        String savedPW = sharedPref.getString("pw", defaultValue); //마찬가지로 pw도 가져와라.
        editTextID.setText(savedID);
        editTextPW.setText(savedPW); //저장되어 있다면 savedID, savedPW로 들어갈 것이고 없다면 빈 문자열이 들어갈 것이다

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correct_id = "happy";
                String correct_pw = "tnals";
                String ID = editTextID.getText().toString();
                String PW = editTextPW.getText().toString();

                if (ID.equals(correct_id) && PW.equals(correct_pw)) {
                    LoginSuccess(correct_id, correct_pw);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong ID or PW",
                            Toast.LENGTH_SHORT).show();
                }
        // if (!ID.equals("happy") {Toast.makeText(getApplicationContext(), "Wrong ID", Toast.LENGTH_SHORT).show();} 식으로 따로 설정해도 됨.
            }
        });
    }

    //프로그램 껐다 켜도 id, pw 저장될 수 있도록: 켜면 저장되었는지 확인 후 저장 & 로그인 후에도 저장
    public void LoginSuccess(String correct_id, String correct_pw) {
        //id, pw 저장해 주기: sharedPreference. 위 onCreate에서 미리 지정해 놓았듯
        SharedPreferences.Editor editor = sharedPref.edit(); //sharedPref를 edit해줄 것
        editor.putString("id", correct_id); //세팅
        editor.putString("pw", correct_pw); //세팅
        editor.commit(); //저장

        Activity fromActivity = MainActivity.this;
        Class toActivity = TodoActivity.class;
        Intent intent = new Intent(fromActivity, toActivity);
        intent.putExtra("id", correct_id); //id가 꼭 있어야 함
        startActivity(intent);
  //      finish();
    }
}