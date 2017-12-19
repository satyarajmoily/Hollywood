package com.howaboutthis.satyaraj.hollywood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ItemEvent> listItem;
    private FloatingActionMenu fab;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 0;

    MoviesAdapter(List<ItemEvent> list, Context context, FloatingActionMenu fab) {
        this.listItem = list;
        this.context = context;
        this.fab = fab;
    }


    @Override
    public int getItemViewType(int position) {
        if(isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position)
    {
        return position == listItem.size () - 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview, parent, false);
            return new ViewItem(v);
        }
        else if(viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.page_number,parent,false);
            return new ViewFooter(v);

        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemEvent listItemEvent = listItem.get(position);

        if (holder instanceof  ViewItem) {


            final ViewItem viewItem = (ViewItem) holder;
            ArrayList<String> genre = new ArrayList<>();

            viewItem.headingTextView.setText(listItemEvent.getTitle());
            viewItem.descriptionTextView.setText(listItemEvent.getDescription());
            if (listItemEvent.getYear().length() != 0) {
                String year = listItemEvent.getYear().substring(0, 4);
                viewItem.yearTextView.setText(year);
            }
            viewItem.ratingTextView.setText(String.valueOf(listItemEvent.getRating()));

            viewItem.ratingBar.setRating((float) listItemEvent.getRating());

            Picasso.with(context)
                    .load(listItemEvent.getImageView())
                    .into(viewItem.imageView);

            int number;
            for (int i = 0; i < listItemEvent.getGenre().length(); i++) {

                try {
                    number = listItemEvent.getGenre().getInt(i);

                    if (number == 27)
                        genre.add("Horror");
                    else if (number == 10402)
                        genre.add("Music");
                    else if (number == 9648)
                        genre.add("Mystery");
                    else if (number == 10749)
                        genre.add("Romance");
                    else if (number == 878)
                        genre.add("Sci-Fi");
                    else if (number == 10770)
                        genre.add("TV Movie");
                    else if (number == 53)
                        genre.add("Thriller");
                    else if (number == 28)
                        genre.add("Action");
                    else if (number == 12)
                        genre.add("Adventure");
                    else if (number == 16)
                        genre.add("Animation");
                    else if (number == 35)
                        genre.add("Comedy");
                    else if (number == 80)
                        genre.add("Crime");
                    else if (number == 99)
                        genre.add("Documentary");
                    else if (number == 18)
                        genre.add("Drama");
                    else if (number == 10751)
                        genre.add("Family");
                    else if (number == 14)
                        genre.add("Fantasy");
                    else if (number == 36)
                        genre.add("History");
                    else if (number == 10752)
                        genre.add("War");
                    else if (number == 37)
                        genre.add("Western");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            final String formattedString = genre.toString()
                    .replace("[", "")
                    .replace("]", "");
            viewItem.genreTextView.setText(formattedString);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (fab.isOpened())
                        fab.close(true);

                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("imageUrl", listItemEvent.getImageView());
                    intent.putExtra("genre", formattedString);
                    intent.putExtra("date", listItemEvent.getYear());
                    intent.putExtra("description", listItemEvent.getDescription());
                    intent.putExtra("rating", listItemEvent.getRating());
                    intent.putExtra("title", listItemEvent.getTitle());
                    intent.putExtra("votingCount", listItemEvent.getVotingCount());
                    intent.putExtra("popularity", listItemEvent.getPopularity());
                    intent.putExtra("backdrop", listItemEvent.getBackdrop());

                    context.startActivity(intent);
                }
            });
        }
        else if (holder instanceof ViewFooter){
            ViewFooter viewFooter = (ViewFooter) holder;
                int page =  listItemEvent.getPageNumber();
                 int totalPages = listItemEvent.getTotalPages();
            viewFooter.lastButton.setText(String.valueOf(listItemEvent.getTotalPages()));

                 if (page == 1) {
                     viewFooter.firstButton.setBackgroundResource(R.drawable.custom_button_pressed);
                     viewFooter.firstButton.setClickable(false);

                 }

                 else if(page == 2) {
                     viewFooter.secondButton.setBackgroundResource(R.drawable.custom_button_pressed);
                     viewFooter.secondButton.setClickable(false);

                 }

                  else if (page == 3) {
                     viewFooter.thirdButton.setBackgroundResource(R.drawable.custom_button_pressed);
                     viewFooter.thirdButton.setClickable(false);

                 }

                   else if (page == 4){
                     viewFooter.fourthButton.setBackgroundResource(R.drawable.custom_button_pressed);
                     viewFooter.fourthButton.setClickable(false);

                 }


                    else if (page==totalPages) {
                     viewFooter.lastButton.setBackgroundResource(R.drawable.custom_button_pressed);
                     viewFooter.lastButton.setClickable(false);

                     viewFooter.fifthButton.setText(String.valueOf(totalPages - 1));
                     viewFooter.fourthButton.setText(String.valueOf(totalPages -2));
                     viewFooter.thirdButton.setText(String.valueOf(totalPages - 3));
                     viewFooter.secondButton.setText(String.valueOf(totalPages - 4));

                 }

                     else {
                     viewFooter.fourthButton.setBackgroundResource(R.drawable.custom_button_pressed);
                     viewFooter.fourthButton.setClickable(false);

                     viewFooter.fourthButton.setText(String.valueOf(page));
                     viewFooter.secondButton.setText(String.valueOf(page - 2));
                     viewFooter.thirdButton.setText(String.valueOf(page - 1));
                     viewFooter.fifthButton.setText(String.valueOf(page + 1));
                 }



                 }

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    private class ViewItem extends RecyclerView.ViewHolder {
        TextView headingTextView;
        TextView descriptionTextView;
        TextView ratingTextView;
        TextView genreTextView;
        TextView yearTextView;
        ImageView imageView;
        RatingBar ratingBar;

        ViewItem(View itemView) {
            super(itemView);

            headingTextView = itemView.findViewById(R.id.movie_name);
            descriptionTextView = itemView.findViewById(R.id.movie_description);
            ratingTextView = itemView.findViewById(R.id.rating);
            yearTextView = itemView.findViewById(R.id.year);
            genreTextView = itemView.findViewById(R.id.grene);
            imageView = itemView.findViewById(R.id.imageView);
            ratingBar = itemView.findViewById(R.id.rating_star);
        }
    }

    private class ViewFooter extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button firstButton ;
        Button secondButton;
        Button thirdButton;
        Button fourthButton;
        Button fifthButton;
        Button lastButton;

        ViewFooter(View itemView){
            super(itemView);

            firstButton = itemView.findViewById(R.id.first);
            secondButton = itemView.findViewById(R.id.button_2);
            thirdButton = itemView.findViewById(R.id.button_3);
            fourthButton = itemView.findViewById(R.id.button_4);
            fifthButton = itemView.findViewById(R.id.button_5);
            lastButton = itemView.findViewById(R.id.last);

            firstButton.setOnClickListener(this);
            secondButton.setOnClickListener(this);
            thirdButton.setOnClickListener(this);
            fourthButton.setOnClickListener(this);
            fifthButton.setOnClickListener(this);
            lastButton.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Button b = (Button)view;
            String buttonText = b.getText().toString();
            ((MainActivity)context).changePage(buttonText);
        }
    }
}
