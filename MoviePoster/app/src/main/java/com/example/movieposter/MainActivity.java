package com.example.movieposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity for displaying and interacting with movie posters.
 * Users can select posters, view details, and add selections to a watchlist.
 */
public class MainActivity extends AppCompatActivity implements PostersListener {

    // Button to add selected posters to the watchlist
    private Button buttonAddToWatchList;

    /**
     * Sets up the main activity view, initializes RecyclerView and data.
     *
     * @param savedInstanceState if the activity is being re-initialized, this contains the saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView for displaying posters
        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchlist);

        // Populate the poster list with sample data
        List<Poster> posterList = new ArrayList<>();
        addSampleData(posterList);

        // Set up adapter for the RecyclerView
        PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        // Set up the button to show selected posters in a Toast message
        buttonAddToWatchList.setOnClickListener(v -> {
            List<Poster> selectedPosters = posterAdapter.getSelectedPosters();
            StringBuilder posterNames = new StringBuilder();
            for (int i = 0; i < selectedPosters.size(); i++) {
                if (i == 0) {
                    posterNames.append(selectedPosters.get(i).name);
                } else {
                    posterNames.append("\n").append(selectedPosters.get(i).name);
                }
            }
            Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Adds sample movie data to the provided list.
     *
     * @param posterList the list to which sample movie posters are added.
     */
    private void addSampleData(List<Poster> posterList) {
        Poster movie1 = new Poster();
        movie1.image = R.drawable.download;
        movie1.name = "Back to the Future";
        movie1.createdBy = "Bob Gale & Robert Zemeckis";
        movie1.rating = 4f;
        movie1.story = "This is a Family/Sci-fi movie.";
        posterList.add(movie1);

        // Add remaining sample movies
        // Each Poster object represents a unique movie with details like name, creator, rating, and description
        Poster movie2 = new Poster();
        movie2.image = R.drawable.download1;
        movie2.name = "The Dark Knight";
        movie2.createdBy = "Jonathan Nolan & Christopher Nolan";
        movie2.rating = 4.8f;
        movie2.story = "This is a Action/Crime movie.";
        posterList.add(movie2);

        Poster movie3 = new Poster();
        movie3.image = R.drawable.download2;
        movie3.name = "Top Gun";
        movie3.createdBy = "Tony Scott";
        movie3.rating = 3.4f;
        movie3.story = "This is a Action/Drama movie.";
        posterList.add(movie3);

        Poster movie4 = new Poster();
        movie4.image = R.drawable.download3;
        movie4.name = "Hocus Pocus";
        movie4.createdBy = "David Kirschner & Mick Garris";
        movie4.rating = 3.5f;
        movie4.story = "This is a Family/Comedy movie.";
        posterList.add(movie4);

        Poster movie5 = new Poster();
        movie5.image = R.drawable.download4;
        movie5.name = "Indiana Jones and the Last Crusade";
        movie5.createdBy = "Steven Spielberg";
        movie5.rating = 4.5f;
        movie5.story = "This is an Adventure/Action movie.";
        posterList.add(movie5);

        Poster movie6 = new Poster();
        movie6.image = R.drawable.download5;
        movie6.name = "The Shawshank Redemption";
        movie6.createdBy = "Frank Darabont";
        movie6.rating = 5f;
        movie6.story = "This is a Thriller/Crime movie.";
        posterList.add(movie6);

        Poster movie7 = new Poster();
        movie7.image = R.drawable.download6;
        movie7.name = "Forrest Gump";
        movie7.createdBy = "Robert Zemeckis";
        movie7.rating = 5f;
        movie7.story = "This is a Comedy/Romance movie.";
        posterList.add(movie7);

        Poster movie8 = new Poster();
        movie8.image = R.drawable.download7;
        movie8.name = "Hangover";
        movie8.createdBy = "Todd Phillips";
        movie8.rating = 4.5f;
        movie8.story = "This is a Comedy/Mystery movie.";
        posterList.add(movie8);

        Poster movie9 = new Poster();
        movie9.image = R.drawable.download8;
        movie9.name = "The Green Mile";
        movie9.createdBy = "Frank Darabont";
        movie9.rating = 4.8f;
        movie9.story = "This is a Crime/Fantasy movie.";
        posterList.add(movie9);

        Poster movie10 = new Poster();
        movie10.image = R.drawable.download9;
        movie10.name = "Catch Me If You Can";
        movie10.createdBy = "Steven Spielberg";
        movie10.rating = 4.8f;
        movie10.story = "This is a Crime/Comedy movie.";
        posterList.add(movie10);
    }

    /**
     * Callback method for poster selection.
     * Shows or hides the "Add to WatchList" button based on selection status.
     *
     * @param isSelected true if a poster is selected, false otherwise.
     */
    @Override
    public void onPosterAction(boolean isSelected) {
        if (isSelected) {
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}
