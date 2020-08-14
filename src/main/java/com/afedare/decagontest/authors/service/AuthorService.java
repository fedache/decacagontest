package com.afedare.decagontest.authors.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.afedare.decagontest.authors.api.AuthorApi;
import com.afedare.decagontest.authors.data.Author;
import com.afedare.decagontest.authors.data.AuthorsPage;

import lombok.SneakyThrows;

public class AuthorService {
    private AuthorApi authorApi;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.ENGLISH);

    public AuthorService(AuthorApi authorApi) {
        this.authorApi = authorApi;
    }

    public List<String> mostActive(int threshold) {
        List<Author> allAuthors = pullAllAuthorData();
        return allAuthors
                .stream()
                .sorted(Comparator.comparing(Author::getSubmissionCount).reversed())
                .map(Author::getUsername)
                .collect(Collectors.toList()).subList(0, threshold);
    }

    public List<String> commentCount(int threshold) {
        List<Author> allAuthors = pullAllAuthorData();
        return allAuthors.stream()
                .sorted(Comparator.comparing(Author::getCommentCount).reversed())
                .map(Author::getUsername)
                .collect(Collectors.toList())
                .subList(0, threshold);
    }

    public List<String> recordedDate(int threshold) {
        List<Author> allAuthors = pullAllAuthorData();
        return allAuthors.stream()
                .sorted(Comparator.comparing(Author::getCreatedAt).reversed())
                .map(Author::getUsername)
                .collect(Collectors.toList())
                .subList(0, threshold);
    }

    private ZonedDateTime convertToDate(String dateStr) {
        return ZonedDateTime.parse(dateStr, dateTimeFormatter);
    }

    @SneakyThrows
    private List<Author> pullAllAuthorData() {
        ArrayList<Author> result = new ArrayList<>();
        Integer numberPages = null;
        int countPages = 0;
        do {
            AuthorsPage authorsPage = authorApi.getAuthors(countPages + 1).execute().body();
            if (numberPages == null) {
                numberPages = authorsPage.getTotalPages();
            }
            result.addAll(authorsPage.getData());
            countPages++;
        } while (countPages < numberPages);
        return result;
    }
}