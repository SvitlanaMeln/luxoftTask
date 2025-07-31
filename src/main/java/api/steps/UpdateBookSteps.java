package api.steps;

import api.models.BookResponse;
import api.models.CreateBookRequest;
import api.services.BooksService;
import io.qameta.allure.Step;

public class UpdateBookSteps extends BookSteps {
    public UpdateBookSteps() {
        super();
    }

    @Step("Build valid book update request")
    public CreateBookRequest buildUpdateRequest() {
        return CreateBookRequest.builder()
                .name("Test Book C")
                .author("Alan Poe")
                .publication("Addison-Wesley 2008")
                .category("Fiction")
                .pages(150)
                .price(54.5)
                .build();
    }

    @Step("Send PUT request to update book with ID: {id}")
    public BookResponse updateBook(CreateBookRequest request, Integer id) {
        return booksService.updateBook(request, id);
    }
}
