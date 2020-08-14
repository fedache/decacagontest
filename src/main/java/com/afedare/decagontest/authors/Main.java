package com.afedare.decagontest.authors;

import java.util.List;

import com.afedare.decagontest.authors.api.AuthorApi;
import com.afedare.decagontest.authors.service.AuthorService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Most active : " + getUsernames(2));
        System.out.println("Highest comment count : " + getUsernameWithHighestCommentCount(2));
        System.out.println("Recorded created : " + getUsernamesSortedByRecordDate(2));
    }

    public static List<String> getUsernames(int threshold) {
        AuthorService authorService = getAuthorService();
        return authorService.mostActive(threshold);
    }

    public static List<String> getUsernameWithHighestCommentCount(int threshold) {
        AuthorService authorService = getAuthorService();
        return authorService.commentCount(threshold);
    }

    public static List<String> getUsernamesSortedByRecordDate(int threshold) {
        AuthorService authorService = getAuthorService();
        return authorService.recordedDate(threshold);
    }

    private static AuthorService getAuthorService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonmock.hackerrank.com/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
        AuthorApi authorApi = retrofit.create(AuthorApi.class);
        return new AuthorService(authorApi);
    }
}
