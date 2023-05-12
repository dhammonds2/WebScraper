package csc180final;

import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    dbConnect database = new dbConnect();
    @FXML
    private TextField linkSearchInput;
    @FXML
    private Label linkOutput;
    @FXML
    private TextField firstURL;
    @FXML
    private TextField secondURL;
    @FXML
    private TextField thirdURL;

    public void doTheCrawling() {
        database.createDatbaseConnection();
        String firstGivenUrl = firstURL.getText();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(firstGivenUrl))
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build().send(request, BodyHandlers.ofString());
            Pattern linkTagPattern = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");
            Matcher linkTagMatcher = linkTagPattern.matcher(response.body());
            while (linkTagMatcher.find()) {
                String link = linkTagMatcher.group();
                Pattern linkPattern = Pattern.compile("href=\"((https|http):.*?)\"");
                Matcher linkMatcher = linkPattern.matcher(linkTagMatcher.group());
                while (linkMatcher.find()) {
                    System.out.println(linkMatcher.group());
                    String linkInsert = linkMatcher.group(1);
                    scrapeLink(linkInsert);
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void scrapeLink(String link) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(link))
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build().send(request, BodyHandlers.ofString());
            database.createDatbaseConnection();
            database.setLink(link);
            Pattern titleTagPattern = Pattern.compile("(?<=\\<title\\>).+(?=\\<\\/title\\>)");
            Matcher titleTagMatcher = titleTagPattern.matcher(response.body());
            while (titleTagMatcher.find()) {

                Pattern titlePattern = Pattern.compile("\\w.*\\s.*\\w");
                Matcher titleMatcher = titlePattern.matcher(titleTagMatcher.group());
                while (titleMatcher.find()) {
                    System.out.println(titleMatcher.group());
                    String titleInsert = titleMatcher.group();
                    database.setTitle(titleInsert);
                    database.addToStorage();

                }
            }
            Pattern bodyTagPattern = Pattern.compile("^[\\s\\S]*<body[^\\>]*>([\\s\\S]*)<\\/body>[\\s\\S]*$");
            Matcher bodyTagMatcher = bodyTagPattern.matcher(response.body());
            while (bodyTagMatcher.find()) {

                Pattern bodyPattern = Pattern.compile("\\w.*\\s.*\\w");
                Matcher bodyMatcher = bodyPattern.matcher(bodyTagMatcher.group());
                while (bodyMatcher.find()) {
                    System.out.println(bodyMatcher.group());
                    String bodyInsert = bodyMatcher.group();
                    database.setBody(bodyInsert);
                    database.addToStorage();
                }
            }
            Pattern paragraphTagPattern = Pattern.compile("<p>\\s*(.+?)\\s*<\\/p>");
            Matcher paragraphTagMatcher = paragraphTagPattern.matcher(response.body());
            while (paragraphTagMatcher.find()) {

                Pattern paragraphPattern = Pattern.compile("\\w.*\\s.*\\w");
                Matcher paragraphMatcher = paragraphPattern.matcher(paragraphTagMatcher.group());
                while (paragraphMatcher.find()) {
                    System.out.println(paragraphMatcher.group());
                    String paragraphInsert = paragraphMatcher.group();
                    database.setP(paragraphInsert);
                    database.addToStorage();
                }
            }
            Pattern spanTagPattern = Pattern.compile("(?m)^\\h*<span>[^<]+<\\/span>");
            Matcher spanTagMatcher = spanTagPattern.matcher(response.body());
            while (spanTagMatcher.find()) {
                Pattern spanPattern = Pattern.compile("\\w.*\\s.*\\w");
                Matcher spanMatcher = spanPattern.matcher(spanTagMatcher.group());
                while (spanMatcher.find()) {
                    System.out.println(spanMatcher.group());
                    String spanInsert = spanMatcher.group();
                    database.setSpan(spanInsert);
                    database.addToStorage();
                }
            }
            Pattern divTagPattern = Pattern.compile("(?m)^\\h*<div>[^<]+<\\/div>");
            Matcher divTagMatcher = divTagPattern.matcher(response.body());
            while (divTagMatcher.find()) {
                Pattern divPattern = Pattern.compile("\\w.*\\s.*\\w");
                Matcher divMatcher = divPattern.matcher(divTagMatcher.group());
                while (divMatcher.find()) {
                    System.out.println(divMatcher.group());
                    String divInsert = divMatcher.group();
                    database.setDiv(divInsert);
                    database.addToStorage();
                }
            }
            database.addToStorage();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void doTheCrawling2() {
        database.createDatbaseConnection();
        String secondGivenUrl = firstURL.getText();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(secondGivenUrl))
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build().send(request, BodyHandlers.ofString());
            Pattern linkTagPattern = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");
            Matcher linkTagMatcher = linkTagPattern.matcher(response.body());
            while (linkTagMatcher.find()) {

                Pattern linkPattern = Pattern.compile("href=\"((https|http):.*?)\"");
                Matcher linkMatcher = linkPattern.matcher(linkTagMatcher.group());
                while (linkMatcher.find()) {
                    System.out.println(linkMatcher.group());
                    String linkInsert = linkMatcher.group(1);
                    scrapeLink(linkInsert);
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void doTheCrawling3() {
        database.createDatbaseConnection();
        String thirdGivenUrl = firstURL.getText();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(thirdGivenUrl))
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build().send(request, BodyHandlers.ofString());
            Pattern linkTagPattern = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");
            Matcher linkTagMatcher = linkTagPattern.matcher(response.body());
            while (linkTagMatcher.find()) {

                Pattern linkPattern = Pattern.compile("href=\"((https|http):.*?)\"");
                Matcher linkMatcher = linkPattern.matcher(linkTagMatcher.group());
                while (linkMatcher.find()) {
                    System.out.println(linkMatcher.group());
                    String linkInsert = linkMatcher.group(1);
                    scrapeLink(linkInsert);
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void searchLink() {
        String linkName = linkSearchInput.getText();
        System.out.println("Searching Link...");
        System.out.println(linkName);
        dbConnect searchedLink = new dbConnect();
        searchedLink.getFromStorage(linkName);
        linkOutput.setText(searchedLink.getLink());
    }
}
