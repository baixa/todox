package com.todox.desktop.controllers;

import com.todox.desktop.entities.Task;
import com.todox.desktop.util.ApiClient;
import com.todox.desktop.util.TrayManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class ItemController extends ListCell<Task> {
    @FXML
    public Label lbContent;
    @FXML
    public Button btnReady;
    @FXML
    public Button btnRemove;

    @Getter @Setter
    private Task task;

    @Getter @Setter
    private Node root;

    @Getter @Setter
    public MainController owner;

    public ItemController() {
        loadFXML();

        btnReady.setOnAction((event) -> {
            task.setCompleted(!task.isCompleted());
            String title = "Задача выполнена!";
            String message = "Задача \"" + task.getContent() + "\" выполнена!";

            TrayManager.showTrayNotification(title, message);
            updateContent();
        });

        btnRemove.setOnAction((event) -> {
            task.setRemoved(true);
            String title = "Задача удалена!";
            String message = "Задача \"" + task.getContent() + "\" удалена!";

            TrayManager.showTrayNotification(title, message);
            updateContent();
        });
    }

    private void updateContent() {

        if (!task.isRemoved()) {
            if (!task.isCompleted()) {
                lbContent.setStyle("-fx-strikethrough: true;");
                lbContent.setStyle("-fx-text-fill: green;");
                btnReady.setText("Отмена");
            } else {
                lbContent.setStyle("-fx-strikethrough: false;");
                lbContent.setStyle("-fx-text-fill: black;");
                btnReady.setText("Готово");
            }
        }

        if (task.isRemoved()) {
            ApiClient.removeObject("http://localhost:8080/tasks/" + task.getId());
        } else {
            ApiClient.updateObject("http://localhost:8080/tasks", task);
        }
        MainController.refreshContent();

    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(ItemController.class.getResource("/layout/item.fxml"));
            loader.setController(this);
            root = loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            setText(null);
            setGraphic(root);
            task = item;
            this.lbContent.setText(task.getContent());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            if (task.isCompleted()) {
                lbContent.setStyle("-fx-strikethrough: true;");
                lbContent.setStyle("-fx-text-fill: green;");
                btnReady.setText("Отмена");
            } else {
                lbContent.setStyle("-fx-strikethrough: false;");
                lbContent.setStyle("-fx-text-fill: black;");
                btnReady.setText("Готово");
            }
        }
    }
}
