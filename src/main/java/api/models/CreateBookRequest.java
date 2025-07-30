package api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookRequest {
    public String name;
    public String author;
    public String publication;
    public String category;
    public Integer pages;
    public Double price;
}
