package clases;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;


public class Colisiones {
    private Actor[]actores;
    private Rectangle[]rect;
    private Rectangle jugador;
    public void checkCollision(TiledMap map, Personaje personaje, float w , float h) {
        jugador=new Rectangle();


        //jugador.set(personaje.getX(),personaje.getY(),personaje.getWidth(),personaje.getHeight());
        MapObjects mons = map.getLayers().get("colision").getObjects();
        actores=new Actor[mons.getCount()];
        rect=new Rectangle[mons.getCount()];
        for (int i = 0;i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
            Rectangle rect1 = obj1.getRectangle();
            rect[i]=new Rectangle(rect1.x*(w*2),rect1.y*(h*2),rect1.width*(w*2),rect1.height*(h*2));
            System.out.println("rec "+rect[i].x);
            actores[i]=new Actor();
            System.out.println(w);
            actores[i].setBounds(rect1.x*(w*1.9f),rect1.y*(h*1.9f),rect1.width*(w*1.9f),rect1.height*(h*1.9f));
        }
    }

    public Actor[] getActores() {
        return actores;
    }

    public Rectangle[] getRect() {
        return rect;
    }
}
