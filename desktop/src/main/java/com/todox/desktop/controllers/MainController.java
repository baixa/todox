package com.todox.desktop.controllers;

import com.todox.desktop.entities.Task;
import com.todox.desktop.util.ApiClient;
import com.todox.desktop.util.TrayManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {

    @FXML
    public Button btnSave;
    @FXML
    public ListView<Task> lvTasks;
    @FXML
    public TextField tfTask;

    private static MainController loadedScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadedScene = this;
        lvTasks.setCellFactory(new TaskCellFactory());
        refreshContent();
    }

    public static void refreshContent() {
        List<Task> items = Arrays.stream(ApiClient.getObject("http://localhost:8080/tasks", Task[].class))
                .filter(item -> !item.isRemoved())
                .toList();
        ObservableList<Task> observableItems = FXCollections.observableList(items);
        loadedScene.lvTasks.setItems(observableItems);
        loadedScene.tfTask.clear();
    }

    public void createTask(MouseEvent mouseEvent) {
        String content = tfTask.getText();
        Task task = new Task();
        task.setContent(content);

        String title = "Задача создана!";
        String message = "Задача \"" + task.getContent() + "\" создана!";
        TrayManager.showTrayNotification(title, message);

        ApiClient.saveObject("http://localhost:8080/tasks", task);
        refreshContent();
    }

}
