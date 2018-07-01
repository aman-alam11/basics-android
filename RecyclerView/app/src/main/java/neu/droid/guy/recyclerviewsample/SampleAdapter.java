package neu.droid.guy.recyclerviewsample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * First the constructor is called from MainActivity
 * Then the size of list is called
 * <p>
 * Now after that when RecyclerView.setAdapter() is called:
 * onCreateViewHolder is called -> Call goes to ViewHolder
 * viewHolder has all the views in 1 item of Recycler View
 * <p>
 * onBindViewHolder is then called which fills the views in viewholder by
 * extracting the relevant data from ArrayList. This data is extracted through position
 * and hence every call from onCreateViewHolder to onBindViewHolder also involves
 * calling sizeOfList aka getItemCount()
 * <p>
 * If I rotate the views and the views are in memory, all cached views
 * are returned and new views are not created
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private ArrayList<SampleData> listForRecyclerView;
    private onListClickListener clickListener;

    public interface onListClickListener {
        void onListClick(int viewClickedPosition);
    }

    SampleAdapter(ArrayList<SampleData> sampleDataList, onListClickListener viewClickListener) {
        this.listForRecyclerView = sampleDataList;
        this.clickListener = viewClickListener;
    }

    /**
     * Called when RecyclerView needs a new {@link SampleViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(SampleViewHolder, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(SampleViewHolder, int)
     */
    @NonNull
    @Override
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                SampleViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link SampleViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link SampleViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(SampleViewHolder, int)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
        holder.bindView(position);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (listForRecyclerView.size() > 0) {
            return listForRecyclerView.size();
        }
        return 0;
    }


    /**
     *
     */
    class SampleViewHolder extends RecyclerView.ViewHolder {
        TextView mainTextView;
        TextView leftTextView;
        TextView rightTextView;

        SampleViewHolder(View itemView) {
            super(itemView);
            mainTextView = itemView.findViewById(R.id.textView);
            leftTextView = itemView.findViewById(R.id.left_text_view);
            rightTextView = itemView.findViewById(R.id.right_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onListClick(getAdapterPosition() + 1);
                }
            });
        }

        void bindView(int index) {
            mainTextView.setText(listForRecyclerView.get(index).getMainText());
            leftTextView.setText(listForRecyclerView.get(index).getLeftText());
            rightTextView.setText(listForRecyclerView.get(index).getRightText());
        }

    }
}
