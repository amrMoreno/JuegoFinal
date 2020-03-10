package entrada;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import bd.BaseDeDatos;
import clases.Personaje;


public class Teclado implements InputProcessor {
    Personaje actor;
    BaseDeDatos bd;


    public Teclado(Personaje p, Map map,BaseDeDatos bd){
        super();
        this.actor=p;
        this.bd=bd;

    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
                actor.mover('u');
                bd.guardar(bd.cargar()+1);
                break;
            case Input.Keys.DOWN:
                actor.mover('d');
                bd.guardar(bd.cargar()+1);
                break;
            case Input.Keys.LEFT:
                actor.mover('l');
                bd.guardar(bd.cargar()+1);
                break;
            case Input.Keys.RIGHT:
                actor.mover('r');
                bd.guardar(bd.cargar()+1);
                break;
            case Input.Keys.MINUS:
                if(actor.getCamara().zoom<1) {
                    actor.getCamara().zoom+=0.1;
                    actor.getCamara().update();
                }
                break;
            case Input.Keys.PLUS:
                if(actor.getCamara().zoom>0.5) {
                    actor.getCamara().zoom -= 0.1;
                    actor.getCamara().update();
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
        if(screenX>Gdx.graphics.getWidth()/2){
            actor.mover('l');
            bd.guardar(bd.cargar()+1);
        }else{
            actor.mover('r');
            bd.guardar(bd.cargar()+1);}
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenY<Gdx.graphics.getHeight()/2) {
            actor.mover('d');
            bd.guardar(bd.cargar()+1);

        }else{
            actor.mover('u');
            bd.guardar(bd.cargar()+1);
        }

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
