package api.steps;

import api.models.BookResponse;
import api.services.BooksService;
import io.qameta.allure.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetBookSteps extends BookSteps{

    public GetBookSteps() {
        super();
    }

    @Step("Send GET /api/v1/books request and fetch list")
    public List<BookResponse> fetchBooks() {
        return booksService.getBooks();
    }

    @Step("Fetch book details from GET /api/v1/books/{id}")
    public BookResponse fetchBookById(Integer id) {
        return booksService.getBook(id);
    }

    @Step("Extract ID of the first book from the list")
    public Integer extractFirstBookId(List<BookResponse> books) {
        return books.stream().findFirst().get().getId();
    }

    @Step("Select a random book ID from the list")
    public Integer selectRandomBookId(List<BookResponse> books) {
        return books.stream().findAny().get().getId();
    }

    @Step("Verify list of books is not empty")
    public void assertBooksReturned(List<BookResponse> books) {
        assertThat(books.size())
                .as("Books response should contain books")
                .isGreaterThan(1);
    }

    @Step("Assert that books response contains books")
    public void assertBooksNotEmpty(List<BookResponse> books) {
        assertThat(books)
                .as("Books response should contain books")
                .isNotEmpty();
    }

    @Step("Assert that book ID in response matches requested ID")
    public void assertIdMatches(BookResponse bookResponse, Integer expectedId) {
        assertThat(bookResponse.getId())
                .as("Id value from response should be the same as in request")
                .isEqualTo(expectedId);
    }

    @Step("Verify that the name for book id = {id} is the same from both endpoints")
    public void assertBookNamesMatch(List<BookResponse> allBooks, BookResponse specifiedBook, Integer id) {
        assertThat(allBooks.stream()
                .filter(b -> b.getId().equals(id))
                .toList().get(0)
                .getName())
                .as("Name of book for id: <%s> should be the same for both API calls", id)
                .isEqualTo(specifiedBook.getName());
    }
}
