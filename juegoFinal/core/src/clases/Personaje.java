package clases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class Personaje extends Actor {
    private Sprite sprite;
    private World mundo;
    private BodyDef propiedadesCuerpo;
    private Body cuerpo;
    private FixtureDef propiedadesFisicasCuerpo;


    public Personaje (World m){
        mundo=m;
        sprite=new Sprite(new Texture("Personajes/rock.png"));
        int anchuraSprite= Gdx.graphics.getWidth()/10; //Anchura y altura se expresan ahora en metros
        int alturaSprite=Gdx.graphics.getHeight()/7;//Anchura y altura se expresan ahora en metros
        sprite.setBounds(80,120,
                anchuraSprite,alturaSprite); //La posición inicial también debe estar en metros

        this.propiedadesCuerpo= new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.StaticBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo);

        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape)propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite/2f, alturaSprite/2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);

        sprite.setOrigin(this.sprite.getWidth()/2,
                this.sprite.getHeight()/2);
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        //sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
        sprite.setPosition(getX(),getY());
        //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
        sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());
        sprite.draw(batch);
    }

    public float getX(){
        return this.cuerpo.getPosition().x;
    }


    public float getY(){
        return this.cuerpo.getPosition().y;
    }

    public Body getCuerpo(){
        return cuerpo;
    }

    public void seguir(OrthographicCamera camara){
        camara.position.x=this.cuerpo.getPosition().x;
        camara.position.y=this.cuerpo.getPosition().y;
    }

}