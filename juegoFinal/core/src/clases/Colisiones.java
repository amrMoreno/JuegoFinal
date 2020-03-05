package clases;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Colisiones {
    private Actor[]actores;
    private Rectangle[]rect;
    private Rectangle jugador;
    private Intersector intersector;
    private  Rectangle rect1;
    public void checkCollision(TiledMap map, Personaje personaje, float w , float h) {
        jugador=new Rectangle();

        System.out.println(personaje.getSprite().getHeight());
        jugador.set(personaje.getSprite().getX(),personaje.getSprite().getY(),personaje.getSprite().getWidth(),personaje.getSprite().getHeight());
        MapObjects mons = map.getLayers().get("colision").getObjects();
        actores=new Actor[mons.getCount()];
        rect=new Rectangle[mons.getCount()];
        for (int i = 0;i < mons.getCount(); i++) {
            RectangleMapObject obj1 = (RectangleMapObject) mons.get(i);
             rect1 = obj1.getRectangle();
            rect[i]=new Rectangle(rect1.x*(w*1.9f),rect1.y*(h*1.9f),rect1.width*(w*1.9f),rect1.height*(h*1.9f));
            System.out.println("rec "+rect[i].x);
            actores[i]=new Actor();
            System.out.println(w);
            actores[i].setBounds(rect1.x*(w*1.9f),rect1.y*(h*1.9f),rect1.width*(w*1.9f),rect1.height*(h*1.9f));


        }
    }

    public boolean collidesWith (Rectangle  rect) {
        return rect1.x < rect.x + rect.width && rect1.y < rect.y + rect.height && rect1.x + rect1.width > rect.x && rect1.y + rect1.height > rect.y;
    }

    public Actor[] getActores() {
        return actores;
    }

    public Rectangle[] getRect() {
        return rect;
    }

    public boolean ComprobarMovimiento(){
        boolean n= true;
        for(int i =0;i<rect.length;i++){

    if(rect[i].overlaps(jugador)){
        n=true;
    }else{
        n= false;
    }
    }
        return n;
}
}
