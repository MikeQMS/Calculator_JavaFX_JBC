package com.project_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class KreisAufgabe extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// 6 RE, andere Höhe und Breite und Astand voneinander
			Pane g= new Pane();
			Scene scene= new Scene(g,800,600);

			kreise(g, scene);

			primaryStage.setScene(scene);
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void kreise(Pane g, Scene scene) {
		for (int y = 1; y < 10; y++) {
			for (int x = 0; x < 19; x++) {
				Circle c = new Circle();
				c.setRadius((int) scene.getWidth() / 25);
				c.setCenterY((2 * c.getRadius() * (y))-c.getRadius());
				if (y == 1) {
					c.setCenterX(c.getRadius() * 2);
					c.setFill(Color.YELLOW);
					g.getChildren().add(c);
				}
				else if (y == 2) {
					c.setCenterX(c.getRadius() * (x));
					c.setFill(Color.RED);
					g.getChildren().add(c);
				}
				else if (y == 3) {
					c.setCenterX(c.getRadius() * x);
					c.setFill(Color.RED);
					g.getChildren().add(c);
					x++;

				}
				else if (y >= 4) {
					if ( (((x-2)+(y-4)) / 1 == 7)) {
						c.setCenterX(c.getRadius() * (x) * 2);
						c.setFill(Color.BLACK);
						g.getChildren().add(c);
					}
					else if ( (x / 1 == 1+(y-4))) {
						c.setCenterX(c.getRadius() * (x) * 2);
						c.setFill(Color.BLACK);
						g.getChildren().add(c);
					}
					else if (y >= 5){
						c.setCenterX(c.getRadius() * 1 * 2);
						c.setFill(Color.LIME);
						g.getChildren().add(c);
					}

				}
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
