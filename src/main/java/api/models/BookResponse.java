package api.models;

import lombok.Data;

@Data
public class BookResponse {
    public Integer id;
    public String name;
    public String author;
    public String publication;
    public String category;
    public Integer pages;
    public Double price;
}
