package csc180final;

import java.io.IOException;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConnect {
    Connection connection = null;
    String connectionString = "jdbc:mysql://localhost/finalassignment?user=root&password=Gingerpitbull40!";
    private String link;
    private String title;
    private String body;
    private String p;
    private String span;
    private String div;

    public void setLink(String newLink) {
        this.link = newLink;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String newBody) {
        this.body = newBody;
    }

    public String getBody() {
        return body;
    }

    public void setP(String newP) {
        this.p = newP;
    }

    public String getP() {
        return p;
    }

    public void setSpan(String newSpan) {
        this.span = newSpan;
    }

    public String getSpan() {
        return span;
    }

    public void setDiv(String newDiv) {
        this.div = newDiv;
    }

    public String getDiv() {
        return div;
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public void createDatbaseConnection() {
        try {
            connection = DriverManager
                    .getConnection(connectionString);
            System.out.println("Created database connection");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void addToStorage() {
        try {
            connection = DriverManager.getConnection(connectionString);
            String insertString = "INSERT INTO content Values (?,?,?,?,?,?)";
            PreparedStatement preparedInsert = connection.prepareStatement(insertString);
            preparedInsert.setString(1, link);
            preparedInsert.setString(2, title);
            preparedInsert.setString(3, body);
            preparedInsert.setString(4, p);
            preparedInsert.setString(5, span);
            preparedInsert.setString(6, div);
            preparedInsert.executeUpdate();

            connection.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void getFromStorage(String searchTerm) {
        try {
            connection = DriverManager.getConnection(connectionString);
            String sqlQuery = "SELECT * FROM content WHERE link like ?";
            PreparedStatement sqlStatement = connection.prepareStatement(sqlQuery);
            sqlStatement.setString(1, "%" + searchTerm + "%");
            ResultSet results = sqlStatement.executeQuery();
            if (results.next()) {
                link = results.getString("link");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

}