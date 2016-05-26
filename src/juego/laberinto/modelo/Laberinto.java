    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.laberinto.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ener2
 */
public class Laberinto {
    
    private Jugador jugador;
    private Rectangle inicio;
    private Rectangle llegada;
    private List<Rectangle> paredes;
    private List<ImageView> listaMonedas;
    
    public Laberinto() {
        
    }
    
    /**
     * Crea el laberinto con las coordenadas de las paredes indicadas en los arreglos
     * 
     * @param listaX coordenadas del rectangulo en X
     * @param listaY coordenadas del rectangulo en Y
     * @param listaAncho lista de tamanos de los rectangulo
     * @param listaAlto lista de alturas de los rectangulo
     */
    public Laberinto(int[] listaX, int[] listaY, int[] listaAncho, int[] listaAlto) {
        listaMonedas = new ArrayList<>();
        paredes = new ArrayList<>();
        for (int i = 0; i < listaX.length; i++) {
            paredes.add(new Rectangle(listaX[i], listaY[i], listaAncho[i], listaAlto[i]));
        }
    }

    public List<Rectangle> getParedes() {
        return paredes;
    }

    public void setParedes(List<Rectangle> paredes) {
        this.paredes = paredes;
    }

    public List<ImageView> getListaMonedas() {
        return listaMonedas;
    }

    public void setListaMonedas(List<ImageView> monedas) {
        this.listaMonedas = monedas;
    }
    
    public Rectangle getInicio() {
        return inicio;
    }

    public void setInicio(Rectangle inicio) {
        this.inicio = inicio;
    }

    public Rectangle getLlegada() {
        return llegada;
    }

    public void setLlegada(Rectangle llegada) {
        this.llegada = llegada;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    /**
     * Genera las posiciones de las monedas dentro del laberinto
     * de forma aleatoria
     * @return 
     */
    public List<ImageView> generarMonedas() {
        Image moneda = new Image("/juego/laberinto/sprites/coin-icon.png");
        Random generador = new Random();
        
        for (int i = 1; i<=20; i++){
            Double coordX = 1000 * generador.nextDouble() % (570 - 25) + 25;
            Double coordY = 1000 * generador.nextDouble() % (570 - 75) + 75;
            ImageView imgViewMoneda = new ImageView (moneda);
            imgViewMoneda.setTranslateX(coordX);
            imgViewMoneda.setTranslateY(coordY);
            if(comprobarColision(imgViewMoneda)){
                i--;
            } else {
                listaMonedas.add(imgViewMoneda);
            }
        }
        return listaMonedas;
    }
    
    /**
     * Comprueba si los bordes de la imagen colisionan con cualquiera de los
     * rectangulos
     * @param imgView componente con la imagen
     * @return true si se detecta alguna colision, sino falso
     */
    public boolean comprobarColision(ImageView imgView) {
        for(Rectangle pared : this.getParedes()) {
            if(imgView.getBoundsInParent().intersects(pared.getBoundsInParent())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo encargado de validar si el jugador toca la moneda
     * @param imgView
     * @return 
     */
    public boolean monedaObtenida(ImageView imgView) {
        for(ImageView moneda : listaMonedas) {
            if(imgView.getBoundsInParent().intersects(moneda.getBoundsInParent()) && moneda.isVisible()){
                moneda.setVisible(false);
                return true;
            }
        }
        return false;
    }
}