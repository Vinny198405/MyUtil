package _36_CW_BooksApplication.dto;

import java.time.LocalDate;

public class LiteratureBook extends Book {

    /**
     *
     */
    private static final long serialVersionUID = 10L;
    Genre genre;
    public LiteratureBook(long isbn, String title, String author, CoverType cover, int pages, String publisher,
                          LocalDate publishDate, Genre genre) {
        super(isbn, title, author, cover, pages, publisher, publishDate);
        this.genre = genre;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "LiteratureBook [genre=" + genre + ", isbn=" + isbn + ", title=" + title + ", author=" + author
                + ", cover=" + cover + ", pages=" + pages + ", publisher=" + publisher + ", publishDate=" + publishDate
                + "]";
    }


}
