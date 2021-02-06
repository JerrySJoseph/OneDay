package com.example.oneday.UI.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.oneday.Adapters.MessagesAdapter;
import com.example.oneday.Models.MessageDisplayModel;
import com.example.oneday.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessagesAdapter adapter;
    ArrayList<MessageDisplayModel> models= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setTitle("Messages");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recycler);
        for(int i=0;i<50;i++)
            models.add(new MessageDisplayModel(
                    "Alia Bhatt "+i,
                    "https://pbs.twimg.com/profile_images/1266056434187468801/g3S9WX8H_400x400.jpg",
                    "Hi Jerry, How are you?",
                        i,
                    System.currentTimeMillis(),
                    false

            ));
        adapter= new MessagesAdapter(models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}