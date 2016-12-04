package uk.co.pauldavies83.compoundinterestcalculator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.Collections;
import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.R;
import uk.co.pauldavies83.compoundinterestcalculator.application.AppContainer;
import uk.co.pauldavies83.compoundinterestcalculator.presenter.InterestCalculatorPresenter;

public class InterestCalculatorActivity extends AppCompatActivity implements InterestCalculator.View {

    private ResultsAdapter adapter;
    private RecyclerView resultsList;
    private List<Double> results = Collections.emptyList();
    private InterestCalculator.Interactions presenter;
    private EditText deposit;
    private EditText interestRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppContainer container = (AppContainer) getApplication();

        presenter = new InterestCalculatorPresenter(this, container.getCompoundCalculatorService());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendUserInputToPresenter();
    }

    @Override
    public void initView() {
        resultsList = (RecyclerView) findViewById(R.id.results_list);
        adapter = new ResultsAdapter(results, new AndroidStringProvider());
        resultsList.setAdapter(adapter);

        deposit = (EditText) findViewById(R.id.deposit_amount);
        interestRate = (EditText) findViewById(R.id.interest_rate);

        findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserInputToPresenter();
            }
        });
    }

    private void sendUserInputToPresenter() {
        presenter.onCalculateClicked(deposit.getText().toString(), interestRate.getText().toString());
    }

    @Override
    public void resultsChanged(List<Double> results) {
        adapter.setResults(results);
        resultsList.setVisibility(View.VISIBLE);
    }


    private class AndroidStringProvider implements ResultsAdapter.StringProvider {
        @Override
        public String[] getYearLabels() {
            return getResources().getStringArray(R.array.year_labels);
        }

        @Override
        public String getCurrencyFormatString() {
            return getString(R.string.currency_format);
        }
    }
}
