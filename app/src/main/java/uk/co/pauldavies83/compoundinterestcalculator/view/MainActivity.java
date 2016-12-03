package uk.co.pauldavies83.compoundinterestcalculator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import uk.co.pauldavies83.compoundinterestcalculator.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView resultsList = (RecyclerView) findViewById(R.id.results_list);
        ResultsAdapter adapter = new ResultsAdapter();
        resultsList.setAdapter(adapter);

        findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsList.setVisibility(View.VISIBLE);
            }
        });
    }

}
