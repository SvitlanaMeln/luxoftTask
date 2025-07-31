package booksServiceTests;

import api.services.BooksService;
import api.steps.GetBookSteps;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("GET /api/v1/books API")
@ExtendWith(AllureJunit5.class)
public class GetBookTest {

    private final GetBookSteps getBookSteps = new GetBookSteps();

    @Test
    @Description("Ensure that GET /api/v1/books endpoint returns list of books")
    public void testGetBooks() {
        var books = getBookSteps.fetchBooks();

        getBookSteps.assertBooksReturned(books);
    }

    @Test
    @Description("Ensure that GET /api/v1/books/{id} endpoint returns valid entity")
    public void testGetSpecifiedBookById() {
        var books = getBookSteps.fetchBooks();

        getBookSteps.assertBooksNotEmpty(books);

        var id = getBookSteps.extractFirstBookId(books);
        var bookResponse = getBookSteps.fetchBookById(id);

        getBookSteps.assertIdMatches(bookResponse, id);
    }

    @Test
    @Description("Ensure that GET /api/v1/books and GET /api/v1/books/{id} endpoints return the same entity after filtration by Id")
    public void testGetBookForBothApiCalls() {
        var books = getBookSteps.fetchBooks();
        var id = getBookSteps.selectRandomBookId(books);

        var singleBook = getBookSteps.fetchBookById(id);

        getBookSteps.assertBookNamesMatch(books, singleBook, id);
    }

}
