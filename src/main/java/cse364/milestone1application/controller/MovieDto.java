package cse364.milestone1application.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MovieDto {

    @Getter
    private String title;
    @Getter
    private String genre;
}
