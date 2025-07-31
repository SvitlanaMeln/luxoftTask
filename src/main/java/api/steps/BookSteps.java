package api.steps;

import api.services.BooksService;

public class BookSteps {
    protected final BooksService booksService;

    public BookSteps() {
        this.booksService = new BooksService();
    }
}
