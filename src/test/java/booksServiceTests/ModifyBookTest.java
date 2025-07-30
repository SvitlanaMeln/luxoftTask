package booksServiceTests;

import api.models.BookResponse;
import api.models.CreateBookRequest;
import api.services.BooksService;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("POST /api/v1/books and PUT /api/v1/books/{id} APIs")
@ExtendWith(AllureJunit5.class)
public class ModifyBookTest {
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

    @AfterEach
    void tearDown() throws IOException {
        System.out.println("This runs after each test");
        ResponseBody deleteBookResponse = booksService.deleteBook(createBookResponse.getId());

        assertThat(deleteBookResponse.string())
                .as("Book should be deleted")
                .isEqualTo("Book has been deleted successfully");
    }

//    @RepeatedTest(3)
    @Test
    @Description("Ensure that POST /api/v1/books endpoint creates valid entity")
    public void testCreateBook() throws IOException {

        assertThat(createBookResponse.getName())
                .as("Name of book in response should be the same as in request")
                .isEqualTo(createBookRequest.getName());

        assertThat(createBookResponse.getAuthor())
                .as("Author of book in response should be the same as in request")
                .isEqualTo(createBookRequest.getAuthor());

        assertThat(createBookResponse.getPublication())
                .as("Publication of book in response should be the same as in request")
                .isEqualTo(createBookRequest.getPublication());

        assertThat(createBookResponse.getCategory())
                .as("Category of book in response should be the same as in request")
                .isEqualTo(createBookRequest.getCategory());

        assertThat(createBookResponse.getPages())
                .as("Pages value of book in response should be the same as in request")
                .isEqualTo(createBookRequest.getPages());

        assertThat(createBookResponse.getPrice())
                .as("Price of book in response should be the same as in request")
                .isEqualTo(createBookRequest.getPrice());
    }

    @Test
    @Description("Ensure that PUT /api/v1/books/{id} endpoint does valid update of specified entity")
    public void testUpdateBook() {

        var updateBookRequest = CreateBookRequest.builder()
                .name("Test Book C")
                .author("Alan Poe")
                .publication("Addison-Wesley 2008")
                .category("Fiction")
                .pages(150)
                .price(54.5)
                .build();

        var updateBookResponse = booksService.updateBook(updateBookRequest, createBookResponse.getId());

        assertThat(updateBookResponse.getName())
                .as("Name of book in response should be the same as in request")
                .isEqualTo(updateBookRequest.getName());

        assertThat(updateBookResponse.getAuthor())
                .as("Author of book in response should be the same as in request")
                .isEqualTo(updateBookRequest.getAuthor());

        assertThat(updateBookResponse.getPublication())
                .as("Publication of book in response should be the same as in request")
                .isEqualTo(updateBookRequest.getPublication());

        assertThat(updateBookResponse.getCategory())
                .as("Category of book in response should be the same as in request")
                .isEqualTo(updateBookRequest.getCategory());

        assertThat(updateBookResponse.getPages())
                .as("Pages value of book in response should be the same as in request")
                .isEqualTo(updateBookRequest.getPages());

        assertThat(updateBookResponse.getPrice())
                .as("Price of book in response should be the same as in request")
                .isEqualTo(updateBookRequest.getPrice());
    }
}
