package entrada;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import clases.Personaje;


public class Teclado implements InputProcessor {
    Personaje actor;
    private Vector3 posicionTiles;
    private TiledMap mapa; //Necesito el mapa para poder redimensionar al jugador
    private int anchuraMapaTiles; //Anchura del mapa donde nos movemos en  tiles
    private int alturaMapaTiles; //Anchura del mapa donde nos movemos en tiles


    public Teclado(Personaje p, Map map){
        this.actor=p;
        this.mapa= (TiledMap) map;
        anchuraMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
        alturaMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getHeight(); //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa

    }
    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("eventoDown","Input "+keycode);
        switch (keycode) {
            case Input.Keys.LEFT:

                break;

            case Input.Keys.RIGHT:

                break;
            case Input.Keys.DOWN:
                if(posicionTiles.y>0) {
                    posicionTiles.y--;
                }
                break;

            case Input.Keys.UP:
//Cambio posición del jugador, todavía no cambia nada visualmente
                if(posicionTiles.y<this.alturaMapaTiles-1) {
                    posicionTiles.y++;
                }

                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
