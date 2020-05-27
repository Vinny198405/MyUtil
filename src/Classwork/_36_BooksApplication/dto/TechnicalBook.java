package Classwork._36_BooksApplication.dto;

import java.time.LocalDate;

public class TechnicalBook extends Book {

    public TechnicalBook(long isbn, String title, String author, CoverType cover, int pages, String publisher,
                         LocalDate publishDate, String subject) {
        super(isbn, title, author, cover, pages, publisher, publishDate);
        this.subject = subject;
    }
    /**
     *
     */
    private static final long serialVersionUID = 100L;
    String subject;
    public TechnicalBook() {
    }
    @Override
    public String toString() {
        return "TechnicalBook [subject=" + subject + ", isbn=" + isbn + ", title=" + title + ", author=" + author
                + ", cover=" + cover + ", pages=" + pages + ", publisher=" + publisher + ", publishDate=" + publishDate
                + "]";
    }

}
