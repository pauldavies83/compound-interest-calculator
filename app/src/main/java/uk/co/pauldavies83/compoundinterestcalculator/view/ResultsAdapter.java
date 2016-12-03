package uk.co.pauldavies83.compoundinterestcalculator.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import uk.co.pauldavies83.compoundinterestcalculator.R;

final class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    interface StringProvider {
        String[] getYearLabels();
        String getCurrencyFormatString();
    }

    private List<Double> values;
    private StringProvider stringProvider;

    ResultsAdapter(List<Double> values, StringProvider stringProvider) {
        this.values = values;
        this.stringProvider = stringProvider;
    }

    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultsAdapter.ViewHolder holder, int position) {
        holder.resultLabel.setText(stringProvider.getYearLabels()[position]);
        holder.resultValue.setText(String.format(Locale.UK, stringProvider.getCurrencyFormatString(), values.get(position)));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    void setResults(List<Double> results) {
        this.values = results;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView resultLabel;
        private final TextView resultValue;

        ViewHolder(View view) {
            super(view);
            resultLabel = (TextView) view.findViewById(R.id.result_label);
            resultValue = (TextView) view.findViewById(R.id.result_value);
        }
    }
}
