package ir.bkhezry.retrofit2.Model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.bkhezry.retrofit2.Model.Movie;
import ir.bkhezry.retrofit2.R;

/**
 * Created by bkhezry on 2/20/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    public CustomListAdapter(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }@Override
     public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        }else{
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        // getting movie data for the row
        Movie m = movieItems.get(position);

        // thumbnail image
        if(holder.thumbNail.getTag()==null || !holder.thumbNail.getTag().equals(m.getImage())) {
            Picasso.with(activity).load(m.getImage()).into(holder.thumbNail);
            holder.thumbNail.setTag(m.getImage());
        }
        // title
        holder.title.setText(m.getTitle());

        // rating
        holder.rating.setText("Rating: " + String.valueOf(m.getRating()));

        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        holder.genre.setText(genreStr);

        // release year
        holder.releaseYear.setText(String.valueOf(m.getReleaseYear()));

        return convertView;
    }
    static class ViewHolder {
        @Bind(R.id.title) TextView title;
        @Bind(R.id.rating) TextView rating;
        @Bind(R.id.genre) TextView genre;
        @Bind(R.id.releaseYear) TextView releaseYear;
        @Bind(R.id.thumbnail) ImageView thumbNail;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
