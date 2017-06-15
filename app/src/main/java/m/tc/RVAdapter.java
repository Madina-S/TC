package m.tc;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder>{
    List<Item> items;
    boolean isInChoiceMode;
    private SparseBooleanArray selectedItems;
    private Context context;

    public RVAdapter(List<Item> items, Context context){
        this.items = items;
        selectedItems = new SparseBooleanArray();
        this.context = context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView itemName;
        ImageView itemIcon;

        ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            itemName = (TextView)itemView.findViewById(R.id.item_name);
            itemIcon = (ImageView)itemView.findViewById(R.id.item_icon);
        }
    }

    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }

        return items;
    }

    public void setIsInChoiceMode(boolean isInChoiceMode) {
        this.isInChoiceMode = isInChoiceMode;
    }

    public boolean getIsInChoiceMode() {
        return isInChoiceMode;
    }

    public void onSelect(int position){

    }

    public void onSelect(int position, boolean bool){
        //if(isIn)
    }

    public void switchSelectedState(int position) {
        if (selectedItems.get(position)) {       // item has been selected, de-select it.
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.itemName.setText(items.get(i).text);
        itemViewHolder.itemIcon.setImageResource(items.get(i).iconID);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}