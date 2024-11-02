package com.example.movieposter;

/**
 * Model class representing a movie poster.
 * Stores details about the movie, including name, creator, image resource, rating, story, and selection status.
 */
public class Poster {

    /** The name of the movie. */
    String name;

    /** The creator or author(s) of the movie. */
    String createdBy;

    /** A brief story or description of the movie. */
    String story;

    /** Resource ID for the movie's image or poster. */
    int image;

    /** Flag indicating whether the poster is selected. */
    Boolean isSelected = false;

    /** The rating of the movie (out of 5). */
    float rating;
}
