package api.steps;

import api.models.BookResponse;
import api.models.CreateBookRequest;
import api.services.BooksService;
import io.qameta.allure.Step;

public class CreateBookSteps extends BookSteps{
    public CreateBookSteps() {
        super();
    }

    @Step("Build book creation request")
    public CreateBookRequest buildCreateBookRequest() {
        return CreateBookRequest.builder()
                .name("Refactoring: Improving the Design of Existing Code")
                .author("Martin Fowler")
                .publication("Addison-Wesley Professional")
                .category("Programming")
                .pages(448)
                .price(35.50)
                .build();
    }

    @Step("Send POST request to create a book")
    public BookResponse createBook(CreateBookRequest createBookRequest) {
        return booksService.createBook(createBookRequest);
    }
}
