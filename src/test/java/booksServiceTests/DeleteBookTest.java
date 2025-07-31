package booksServiceTests;

import api.models.BookResponse;
import api.models.CreateBookRequest;
import api.services.BooksService;
import api.steps.CreateBookSteps;
import api.steps.DeleteBookSteps;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("DELETE /api/v1/books{id} API")
@ExtendWith(AllureJunit5.class)
public class DeleteBookTest {
    private final CreateBookSteps createBookSteps = new CreateBookSteps();
    private final DeleteBookSteps deleteBookSteps = new DeleteBookSteps();
    private CreateBookRequest createBookRequest;
    private BookResponse createBookResponse;

    @BeforeEach
    void setUp() {
        System.out.println("This runs before each test");
        createBookRequest = createBookSteps.buildCreateBookRequest();
        createBookResponse = createBookSteps.createBook(createBookRequest);
    }

    @Test
    @Description("Ensure that DELETE /api/v1/books/{id} endpoint deletes specified entity")
    public void testDeleteBook() throws IOException {
        var id = createBookResponse.getId();

        deleteBookSteps.deleteBook(id);

        deleteBookSteps.verifyBookIsDeleted(id);
    }
}
