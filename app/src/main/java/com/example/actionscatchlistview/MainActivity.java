package com.example.actionscatchlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<MyData> values;
    ListView list;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.listView);
        String[] from = new String[]{"header", "count"};
        int[] to = new int[]{R.id.textViewHeader, R.id.textViewText};
        values = prepareContent();
        adapter = new MyAdapter(this, values);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, values.get(position).getText() + " - столько в этом тексте букв",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        );
    }

    @NonNull
    private List<MyData> prepareContent() {
        String[] largeText = getString(R.string.large_text).split("\n\n");
        List<MyData> content = new ArrayList<>();
        MyData oneItem;

        for (int i = 0; i < largeText.length; i++) {
            oneItem = new MyData(largeText[i], largeText[i].length());
            content.add(oneItem);
        }
        return content;
    }
}