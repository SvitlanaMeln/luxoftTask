package api.steps;

import api.models.BookResponse;
import api.services.BooksService;
import io.qameta.allure.Step;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteBookSteps extends BookSteps{
    public DeleteBookSteps() {
        super();
    }

    @Step("Delete book with ID: {id}")
    public void deleteBook(Integer id) throws IOException {
        ResponseBody response = booksService.deleteBook(id);
        assertThat(response.string())
                .as("Book should be deleted")
                .isEqualTo("Book has been deleted successfully");
    }

    @Step("Verify book with ID: {id} is no longer present")
    public void verifyBookIsDeleted(Integer id) {
        var getBooksResponse = booksService.getBooks();

        assertThat(getBooksResponse.stream()
                .filter(b -> b.getId().equals(id))
                .toList().size())
                .as("Book should be deleted")
                .isEqualTo(0);

    }
}
