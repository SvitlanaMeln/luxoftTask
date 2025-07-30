package api.services;

import api.interfaces.BooksApi;
import api.models.BookResponse;
import api.models.CreateBookRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.List;

public class BooksService extends ServiceBase {
    private BooksApi booksApi;
    private String serviceName = "BookService";

    public BooksService() {
        super();
        booksApi = retrofit.create(BooksApi.class);
    }

    public List<BookResponse> getBooks() {
       Call<List<BookResponse>> responseBodyCall = booksApi.getBooks();
       return triggerApiCall(responseBodyCall, serviceName);
    }

    public BookResponse getBook(Integer id) {
        Call<BookResponse> responseBodyCall = booksApi.getBook(id);
        return triggerApiCall(responseBodyCall, serviceName);
    }

    public BookResponse createBook(CreateBookRequest request) {
        Call<BookResponse> responseBodyCall = booksApi.createBook(request);
        return triggerApiCall(responseBodyCall, serviceName);
    }

    public BookResponse updateBook(CreateBookRequest request, Integer id) {
        Call<BookResponse> responseBodyCall = booksApi.updateBook(request, id);
        return triggerApiCall(responseBodyCall, serviceName);
    }

    public ResponseBody deleteBook(Integer id) {
        Call<ResponseBody> responseBodyCall = booksApi.deleteBook(id);
        return triggerApiCall(responseBodyCall, serviceName);
    }
}
