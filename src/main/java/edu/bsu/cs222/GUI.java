package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application
{
    private TextField articleTitleField;
    private Label outputLabel;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Wikipedia Revision Checker");

        articleTitleField = new TextField();
        Button checkButton = createButton("Check", this::checkRevisionHistory);
        Button errorButton = createButton("Empty Result", this::showErrorAlert);

        VBox inputLayout = createInputLayout();
        VBox outputLayout = createOutputLayout();

        inputLayout.getChildren().addAll(createInstructionLabel(), articleTitleField, checkButton, errorButton);

        Scene inputScene = new Scene(inputLayout, 500, 400);
        Scene outputScene = new Scene(outputLayout, 400, 400);

        primaryStage.setScene(inputScene);
        primaryStage.show();

        outputLabel = new Label("This is where the output will go.");
        outputLayout.getChildren().add(outputLabel);

        checkButton.setOnAction(e -> primaryStage.setScene(outputScene));
    }

    private Button createButton(String text, Runnable action)
    {
        Button button = new Button(text);
        button.setOnAction(event -> action.run());
        return button;
    }

    private VBox createInputLayout()
    {
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER_LEFT);
        return layout;
    }

    private VBox createOutputLayout()
    {
        return new StackPane();
    }

    private Label createInstructionLabel()
    {
        return new Label("Please enter the title of an article to check its revision history.");
    }

    private void checkRevisionHistory()
    {
        String inputTitle = articleTitleField.getText();
        // Perform the revision history check with the input title.
        // Update the outputLabel with the result.
        outputLabel.setText("Revision history for '" + inputTitle + "' goes here.");
    }

    private void showErrorAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oops");
        alert.setHeaderText(null);
        alert.setContentText("Invalid input");
        alert.showAndWait();
    }
}
