package com.howaboutthis.satyaraj.hollywood;

import org.json.JSONArray;

public class ItemEvent {

    private long id;
    private String title;
    private String description;
    private JSONArray genre;
    private double rating;
    private String urlToImage;
    private String year;
    private double popularity;
    private long votingCount;
    private String backdrop;
    private int pageNumber;
    private int totalPages;

    ItemEvent(long id, String title, String description, JSONArray genre, double rating, String imageView, String year, double popularity, long votingCount, String backdrop, int mPageNumber, int totalPages) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.urlToImage = imageView;
        this.year = year;
        this.id = id;
        this.popularity = popularity;
        this.votingCount = votingCount;
        this.backdrop = backdrop;
        this.pageNumber = mPageNumber;
        this.totalPages = totalPages;
    }

    String getBackdrop() { return backdrop; }

    double getPopularity() { return popularity; }

    long getVotingCount() { return votingCount; }

    int getTotalPages(){ return totalPages;}

    public String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    JSONArray getGenre() {
        return genre;
    }

    double getRating() {
        return rating;
    }

    String getImageView() {
        return urlToImage;
    }

    String getYear() {
        return year;
    }

    public Long getId() { return id; }

    int getPageNumber() { return pageNumber; }
}
