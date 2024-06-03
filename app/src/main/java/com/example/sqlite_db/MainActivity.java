package com.example.sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button addBtn, deleteBtn;
    TextView outputText;
    EditText inputText;

    myDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = (Button) findViewById(R.id.addButton);
        deleteBtn = (Button) findViewById(R.id.deleteButton);
        outputText = (TextView) findViewById(R.id.textOutput);
        inputText = (EditText) findViewById(R.id.textInput);

        dbHandler = new myDBHandler(this, null, null, 1);

        printDatabase();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                Products product = new Products(input);
                dbHandler.addProduct(product);
                printDatabase();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DELETES PRODUCT BY NAME
               // String inputTextD = inputText.getText().toString();
               // dbHandler.deleteProduct(inputTextD);
               // printDatabase();
                dbHandler.deleteAll();
                printDatabase();
            }
        });

    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        outputText.setText(dbString);
        inputText.setText("");
    }
}