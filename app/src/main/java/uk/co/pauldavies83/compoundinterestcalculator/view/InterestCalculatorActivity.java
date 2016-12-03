package uk.co.pauldavies83.compoundinterestcalculator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collections;
import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.R;
import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.presenter.InterestCalculatorPresenter;

public class InterestCalculatorActivity extends AppCompatActivity implements InterestCalculator.View {

    private ResultsAdapter adapter;
    private RecyclerView resultsList;
    private List<Double> results = Collections.emptyList();
    private InterestCalculator.Interactions presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new InterestCalculatorPresenter(this, new CompoundCalculatorService());
    }

    @Override
    public void initView() {
        resultsList = (RecyclerView) findViewById(R.id.results_list);
        adapter = new ResultsAdapter(results);
        resultsList.setAdapter(adapter);

        findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCalculateClicked();
            }
        });
    }

    @Override
    public void resultsChanged(List<Double> results) {
        adapter.setResults(results);
        resultsList.setVisibility(View.VISIBLE);
    }

}
