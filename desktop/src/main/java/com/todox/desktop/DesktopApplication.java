package com.todox.desktop;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesktopApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxLoader.class, args);
	}

}
