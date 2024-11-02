package com.example.movieposter;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying a list of movie posters in a RecyclerView.
 * Provides selection functionality and communicates selections back to the main activity.
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private List<Poster> posterList;
    private PostersListener postersListener;

    /**
     * Constructor for the PosterAdapter.
     *
     * @param posterList       List of Poster objects to be displayed.
     * @param postersListener  Listener to handle selection actions.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    /**
     * Creates and returns a new PosterViewHolder for a RecyclerView item.
     *
     * @param parent   Parent ViewGroup for inflation.
     * @param viewType Type of the view.
     * @return A new instance of PosterViewHolder.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     * Binds data from the Poster object to the ViewHolder.
     *
     * @param holder   ViewHolder to bind data to.
     * @param position Position of the item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * Returns the total count of items in the poster list.
     *
     * @return The size of posterList.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    /**
     * Retrieves a list of selected posters.
     *
     * @return List of selected Poster objects.
     */
    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**
     * ViewHolder class for a Poster item.
     * Responsible for setting up the item layout and handling click events for selection.
     */
    class PosterViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * Constructor for PosterViewHolder.
         *
         * @param itemView The itemView for a poster item in the RecyclerView.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds a Poster object to the ViewHolder.
         * Sets up the image, text, and rating of the poster and manages selection UI.
         *
         * @param poster The Poster object to bind to this ViewHolder.
         */
        void bindPosters(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            // Update the UI based on selection status
            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            // Handle click event for toggling selection
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle selection state and update UI
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPosters().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
