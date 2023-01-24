package com.todox.desktop.controllers;

import com.todox.desktop.entities.Task;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TaskCellFactory implements Callback<ListView<Task>, ListCell<Task>> {
    @Override
    public ListCell<Task> call(ListView<Task> taskListView) {
        return new ItemController();
    }
}
