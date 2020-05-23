package com.liondy.javalist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText text;
    private ImageButton btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.lstView);
        this.text = (EditText) findViewById(R.id.text);
        this.btn_add = (ImageButton) findViewById(R.id.btn_add);

        final ListAdapter listAdapter = new ListAdapter(this,this);
        listView.setAdapter(listAdapter);

        this.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String todo = text.getText().toString();
                if(!todo.equals("")) listAdapter.add(todo);
                text.setText("");
            }
        });
    }
}
