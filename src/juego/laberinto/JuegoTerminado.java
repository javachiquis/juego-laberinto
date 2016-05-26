/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.laberinto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import juego.laberinto.modelo.Jugador;

/**
 *
 * @author ener2
 */
public class JuegoTerminado extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Jugador chiqui = (Jugador)primaryStage.getUserData();
        
        Label mensaje = new Label();
        Image imgProfe = new Image("/juego/laberinto/sprites/teacheroriginal.png");
        ImageView imgViewProfe = new ImageView(imgProfe);
        
        if(chiqui.isGanador()) {
            mensaje.setText("Juego Terminado\n\nMonedas Obtenidas: " + chiqui.getPuntaje());
        } else {
            mensaje.setText("Juego Terminado\n\nSe acabó el tiempo.");
        }
        
        mensaje.setScaleX(2);
        mensaje.setScaleY(2);
        
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setMinSize(400, 300);
        root.setVgap(10);
        root.setHgap(40);
                
        root.add(imgViewProfe, 0, 0);
        root.add(mensaje, 1, 0);
        
        // Se crea la ventana con el panel que ya tiene los componentes
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setTitle("Juego Terminado!");
        primaryStage.getIcons().add(new Image("/juego/laberinto/sprites/lab-icon.png"));
        primaryStage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal para evitar duplicados
        primaryStage.setResizable(false); // no permite reajustar el tamano de la ventana
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}