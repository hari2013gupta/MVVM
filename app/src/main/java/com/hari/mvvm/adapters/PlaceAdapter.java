package com.hari.mvvm.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hari.mvvm.R;
import com.hari.mvvm.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.DataObjectHolder> implements Filterable {
    private static String TAG = PlaceAdapter.class.getSimpleName();
    private static MyClickListener myClickListener;
    public List<NicePlace> placeList;
    public Activity activity;
    static private List<NicePlace> placeListFiltered;
    private ItemFilter mFilter = new ItemFilter();

    public PlaceAdapter(Activity activity, List<NicePlace> placeList) {
        this.activity = activity;
        this.placeList = placeList;
        this.placeListFiltered = placeList;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DataObjectHolder dataObjectHolder = null;
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_place_item, parent, false);

            dataObjectHolder = new DataObjectHolder(view);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        try {
            NicePlace item = placeListFiltered.get(position);
            holder.nameTV.setText(item.getPlaceName());


            // Set the image
            RequestOptions defaultOptions = new RequestOptions()
                    .error(R.drawable.ic_launcher_background);
            Glide.with(activity)
                    .setDefaultRequestOptions(defaultOptions)
                    .load(item.getPlaceImageUrl())
                    .into(holder.placeIV);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public void addItem(NicePlace dataObj, int index) {
        placeList.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        placeList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return placeListFiltered.size();
    }

    public interface MyClickListener {
        void onItemClick(String id, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTV;
        ImageView placeIV;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            placeIV = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(placeListFiltered.get(getAdapterPosition()).getPlaceId() , v);
        }
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            if (charString.isEmpty()) {
                placeListFiltered = placeList;
            } else {
                List<NicePlace> filteredList = new ArrayList<>();
                for (NicePlace row : placeList) {

                    // here we are looking for name  match
                    if (row.getPlaceName().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row);
                    }
                }
                placeListFiltered = filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = placeListFiltered;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            placeListFiltered = (ArrayList<NicePlace>) filterResults.values;
            notifyDataSetChanged();
        }
    }

}
