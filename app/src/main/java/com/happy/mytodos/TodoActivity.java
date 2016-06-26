package com.happy.mytodos;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoActivity extends ListActivity {

    TextView textViewIntro;
    Button btnAdd;
    ArrayList<String> todo_list;
    SharedPreferences sharedPref;
    String packagename = "com.happy.mytodos;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        textViewIntro = (TextView) findViewById(R.id.textViewIntro);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        Bundle extras = getIntent().getExtras(); //LoginSuccess();에서 만든 id "message"를 가져오는 코드
        String message = "";
        if (extras != null) { // null=아무것도 없다. extra가 있는지 없는지부터 확인!
            message = extras.getString("id"); //extras에 있는 아이디 "message"를 가져올 것이다
        }
        textViewIntro.setText("Hello " + message + "!");

        sharedPref = this.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        todo_list = new ArrayList<String>();
        loadArrayList(); //새롭게 저장된 리스트를 보여주는 함수.
        populateListView();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }
    public void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add");
        builder.setMessage("Add your Todos");

        //EditText 창이 alertdialog 안에 들어감
        final EditText todo_input = new EditText(this);
        todo_input.setInputType
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL); //세팅 설정
        builder.setView(todo_input);
        //버튼설정
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //할일 저장 가능
                String input = todo_input.getText().toString();
                todo_list.add(input);
                LogLibrary.printEach(todo_list); //할일을 로그로 프린트해 줌
                saveArrayList(); //프로그램 꺼도 넣은 값을 저장할 수 있도록 함
                populateListView(); //이 함수 불러줘서 ListView 다시 생성!
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id); //아이템을 클릭하면
        LogLibrary.print(position); //번째수 프린트
        String work = todo_list.get(position); //position번째의 값도 가져오고
        LogLibrary.print(work); //제대로 갖고 왔는지 프린트

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(work);
        builder.setMessage("Did you finish this work?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //여기서 할일 지워주기
                todo_list.remove(position);
                LogLibrary.printEach(todo_list);
                saveArrayList(); //할일이 지워진 arrayList를 저장해 줌
                populateListView(); //없어진 것 반영해서 리스트 다시 뜸
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void saveArrayList() { //ArrayList를 저장하는 함수
        String todo_str = "";
        for (String todo: todo_list) { //for-in
            todo_str = todo_str + todo + "&&&"; //리스트에 절대 안써줄 만한 것 써주기. todo1/todo2/todo3... 식으로 /를 넣으면 / 들어가있는 todo는 오류남
            LogLibrary.print(todo_str);
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("todo", todo_str);
        editor.commit();
    }

    public void loadArrayList() {
        String todo = sharedPref.getString("todo", "");
        String [] todo_arr = todo.split("&&&"); //묶인 리스트나 문장을 단어마다 풀어 하나씩 배열에 넣어줌
        LogLibrary.printEach(todo_arr);
        for(String work : todo_arr) {
            todo_list.add(work);
        }
    }

    public void populateListView() { //보여지게 하는 코드
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, todo_list);
        setListAdapter(adapter);
    }

}