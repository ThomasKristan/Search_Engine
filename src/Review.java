public class Review {
    //Attributes
    private String content;
    private Author author;
    private Document reviewedDocument;
    private String language;
    private Date releaseDate;
    private int rating;

    //Getter-Methods
    public String getContent() { return content; }
    public Author getAuthor() { return author; }
    public Document getReviewedDocument() { return reviewedDocument; }
    public String getLanguage() { return language; }
    public Date getReleaseDate() { return releaseDate; }
    public int getRating() { return rating; }

    //Constructor
    public Review (Author author, Document reviewedDocument, String language, Date releaseDate, String content) {
    }

    //toString-Method
    public String toString() {
        return this.reviewedDocument + " is rated " + this.rating + " by " + this.author;
    }

    //further methods
    public int getAgeAt (Date today) {
        return this.releaseDate.getAgeInDaysAt(today);
    }
}
