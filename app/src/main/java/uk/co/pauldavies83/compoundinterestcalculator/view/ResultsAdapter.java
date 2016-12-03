package uk.co.pauldavies83.compoundinterestcalculator.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.R;

final class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    private final String[] labels = new String[]{"After 1 year", "After 2 years", "After 3 years", "After 4 years", "After 5 years"};
    private List<Double> values;

    ResultsAdapter(List<Double> values) {
        this.values = values;
    }

    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultsAdapter.ViewHolder holder, int position) {
        holder.resultLabel.setText(labels[position]);
        holder.resultValue.setText(values.get(position).toString());
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
