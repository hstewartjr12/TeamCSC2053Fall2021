package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.LinkedList;

public class NumbersDetailActivity extends AppCompatActivity {
    private final LinkedList<String> mNumbers = new LinkedList<>();
    private final LinkedList<String> mNumberFacts = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private NumbersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbersdetail);

        Bundle extras = getIntent().getExtras();
        int limit1 = extras.getInt("start");
        int limit2 = extras.getInt("end");

        for (int number = limit1; number <= limit2; number++) {
            mNumbers.add(number + "");
        }


        // THIS IS WHERE YOU ADD THE FACTS
        for (int number = limit1; number <= limit2; number++) {
            mNumberFacts.add("Hello!");
        }


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NumbersAdapter(this, mNumbers, mNumberFacts);
        mRecyclerView.setAdapter(mAdapter);
    }
}