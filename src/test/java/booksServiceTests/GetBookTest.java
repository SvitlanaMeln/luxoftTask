package booksServiceTests;

import api.services.BooksService;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("GET /api/v1/books API")
@ExtendWith(AllureJunit5.class)
public class GetBookTest {

    private BooksService booksService;

    @Test
    @Description("Ensure that GET /api/v1/books endpoint returns list of books")
    public void testGetBooks() {
        booksService = new BooksService();

        var books = booksService.getBooks();

        assertThat(books.size())
                .as("Books response should contain books")
                .isGreaterThan(1);
    }

    @Test
    @Description("Ensure that GET /api/v1/books/{id} endpoint returns valid entity")
    public void testGetSpecifiedBookById() {
        booksService = new BooksService();

        var books = booksService.getBooks();

        assertThat(books)
                .as("Books response should contain books")
                .isNotEmpty();

        var id = books.stream().findFirst().get().getId();
        var bookResponse = booksService.getBook(id);

        assertThat(bookResponse.getId())
                .as("Id value from response should be the same as in request")
                .isEqualTo(id);
    }

    @Test
    @Description("Ensure that GET /api/v1/books and GET /api/v1/books/{id} endpoints return the same entity after filtration by Id")
    public void testGetBookForBothApiCalls() {
        booksService = new BooksService();

        var booksResponse = booksService.getBooks();
        var id = booksResponse.stream().findAny().get().getId();

        var bookResponse = booksService.getBook(id);

        assertThat(booksResponse.stream()
                .filter(b -> b.getId().equals(id))
                .toList().get(0)
                .getName())
                .as("Name of book for id: <%s> should be the same for both API calls", id)
                .isEqualTo(bookResponse.getName());
    }

}
