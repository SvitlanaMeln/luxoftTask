package api.interfaces;

import api.models.BookResponse;
import api.models.CreateBookRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BooksApi {
    @GET("/api/v1/books")
    Call<List<BookResponse>> getBooks();

    @GET("/api/v1/books/{id}")
    Call<BookResponse> getBook(
            @Path("id") Integer id
    );

    @POST("/api/v1/books")
    Call<BookResponse> createBook(
            @Body CreateBookRequest request
    );

    @PUT("/api/v1/books/{id}")
    Call<BookResponse> updateBook(
            @Body CreateBookRequest request,
            @Path("id") Integer id
    );

    @DELETE("/api/v1/books/{id}")
    Call<ResponseBody> deleteBook(
            @Path("id") Integer id
    );
}
