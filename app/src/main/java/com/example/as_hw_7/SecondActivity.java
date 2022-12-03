package com.example.as_hw_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent =getIntent();
        String book_name=intent.getStringExtra("book_name");
        TextView old_name=findViewById(R.id.old_name);
        old_name.setText(book_name);
        Button confirm_btn=(Button)findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView new_name_v=findViewById(R.id.new_name);
                String new_name=new_name_v.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("new_work_name",new_name);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Button cancel_btn=(Button) findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}