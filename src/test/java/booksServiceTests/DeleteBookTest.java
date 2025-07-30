package booksServiceTests;

import api.models.BookResponse;
import api.models.CreateBookRequest;
import api.services.BooksService;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("DELETE /api/v1/books{id} API")
@ExtendWith(AllureJunit5.class)
public class DeleteBookTest {

    private BooksService booksService;
    private CreateBookRequest createBookRequest;
    private BookResponse createBookResponse;

    @BeforeEach
    void setUp() {
        System.out.println("This runs before each test");
        booksService = new BooksService();

        createBookRequest = CreateBookRequest.builder()
                .name("Refactoring: Improving the Design of Existing Code")
                .author("Martin Fowler")
                .publication("Addison-Wesley Professional")
                .category("Programming")
                .pages(448)
                .price(35.50)
                .build();

        createBookResponse = booksService.createBook(createBookRequest);
    }

    @Test
    @Description("Ensure that DELETE /api/v1/books/{id} endpoint deletes specified entity")
    public void testDeleteBook() throws IOException {

        var id = createBookResponse.getId();
        ResponseBody deleteBookResponse = booksService.deleteBook(id);

        assertThat(deleteBookResponse.string())
                .as("Book should be deleted")
                .isEqualTo("Book has been deleted successfully");

        var getBooksResponse = booksService.getBooks();

        assertThat(getBooksResponse.stream()
                .filter(b -> b.getId().equals(id))
                .toList())
                .as("Book should be deleted")
                .isEmpty();
    }
}
