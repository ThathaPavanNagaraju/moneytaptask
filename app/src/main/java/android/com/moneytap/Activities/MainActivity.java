package android.com.moneytap.Activities;

import android.com.moneytap.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvWikiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWikiSearch = (TextView) findViewById(R.id.tv_open_wiki_search);
        tvWikiSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WikiSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}