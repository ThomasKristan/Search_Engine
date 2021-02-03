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

    public int getAgeAt (Date today) {
        return this.releaseDate.getAgeInDaysAt(today);
    }

    public boolean equals(Review review){
        if(this == review){
            return true;
        }
        if(this == null){
            return false;
        }
        return this.content.equals(review.content) && this.language.equals(review.language)
                && (this.rating == review.rating)
                && ((this.author != null && this.author.equals(review.author))
                    || (this.author == null && review.author == null))
                && ((this.reviewedDocument != null && this.reviewedDocument.equals(review.reviewedDocument))
                    || (this.reviewedDocument == null && review.reviewedDocument == null))
                && ((this.releaseDate != null && this.releaseDate.equals(review.releaseDate))
                    || (this.releaseDate == null && review.releaseDate == null));
    }
}
