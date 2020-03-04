package Elemento;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;



public class Enemigos extends Actor {

    private Sprite sprite;
    private Rectangle rec;
    private Batch batch;
    protected Boolean co;
    protected String nombre;
    private ShapeRenderer shapeRenderer;


    public Enemigos(String rutaImg, float x, float y ,float w, float h){
        co=false;
        shapeRenderer=new ShapeRenderer();
        sprite=new Sprite(new Texture(rutaImg));
       rec=new Rectangle((int)x,(int)y,(int)w,(int)h);
       batch=new SpriteBatch();
       sprite.setBounds(x,y,x,y);
       this.setSize(Gdx.graphics.getWidth()*100,Gdx.graphics.getHeight()*100);
       sprite.setPosition(h,w);

    }

    public void dibujarHitboxPersonaje(){
        shapeRenderer.end();
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.box(sprite.getX(),sprite.getY(),0,sprite.getWidth(),sprite.getHeight(),20);
        shapeRenderer.setColor(Color.BLUE);
        sprite.draw(batch);

        batch.end();

    }
    public Rectangle getHitBox(){
        return sprite.getBoundingRectangle();
    }


}

