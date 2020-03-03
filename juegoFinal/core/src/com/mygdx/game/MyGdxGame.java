package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import clases.Colisiones;
import clases.Personaje;
import entrada.Teclado;

public class MyGdxGame extends ApplicationAdapter {
	private OrthogonalTiledMapRenderer renderer; //Clase auxiliar para renderizar un mapa.
	private OrthographicCamera camera; //Cámara a través de la que veremos el mundo.
	private Stage stage;
	private static int WIDTH; //Aquí almacenaremos la anchura en tiles
	private static int HEIGHT; //Aquí almacenaremos la altura en tiles
	public static final float unitScale = 1/16f; //Nos servirá para establecer que la pantalla se divide en tiles de 32 pixeles;
	private Personaje personaje;
	private World world;
	private Batch batch;
	private Teclado teclado;
	private Box2DDebugRenderer debugRender;
	 private TiledMap map;
	 private Colisiones colisiones;
	 private float w;
	 private float h;

	@Override
	public void create () {
		batch=new SpriteBatch();

		map = new TmxMapLoader().load("Mapa/mapaPlaya.tmx"); //Cargamos el tilemap desde assets
		Music sonidoFondo = Gdx.audio.newMusic(Gdx.files.internal("Sonidos/MusicaFondo.mp3"));
		sonidoFondo.play();
		stage=new Stage(new ScreenViewport());
		//world=new World(new Vector2(0,-9.8f),true);
		colisiones=new Colisiones();



this.debugRender= new Box2DDebugRenderer();



		renderer = new OrthogonalTiledMapRenderer(map, unitScale); //Establecemos el renderizado del mapa dividido en Tiles de 16 dp.



		camera = new OrthographicCamera(); //Declaramos la cámara a través de la que veremos el mundo
		//camera.zoom=0.1f; //Establecemos el zoom de la cámara. 0.1 es más cercano que 1.
		WIDTH = ((TiledMapTileLayer) map.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
		HEIGHT = ((TiledMapTileLayer) map.getLayers().get(0)).getHeight(); //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa
		camera.setToOrtho(false, WIDTH,HEIGHT); //Establecemos la cámara, y le decimos cuanto tiene que ocupar. Doc:
		camera.position.x=WIDTH/2;
		camera.position.y=HEIGHT/2;
		//camera.zoom=1.001f;
		personaje=new Personaje(camera,map);
		teclado=new Teclado(personaje,map);
		Gdx.input.setInputProcessor(teclado);
		camera.update();
		WIDTH=WIDTH*16;
		HEIGHT=HEIGHT*16;
		 w = Gdx.graphics.getWidth()/WIDTH; //Obtenemos la anchura de nuestra pantalla
		 h = Gdx.graphics.getHeight()/HEIGHT; //Obtenemos la atura de nuestra pantalla
		System.out.println(w);
		colisiones.checkCollision(map,personaje,w,h);
		for(int b=0; b<colisiones.getActores().length;b++){
			stage.addActor(colisiones.getActores()[b]);

		}
		stage.setDebugAll(true);

	}



	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera); //Establecemos la vista del mundo a través de la cámara.
		renderer.render(); //Renderizamos la vista
		batch.begin();

		personaje.dibujarHitboxPersonaje();
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		camera.update();
		//debugRender.render(map,camera.combined);

	}


	@Override
	public void dispose () {
		renderer.dispose(); //Destruimos el objeto que renderiza un mapa, para no tener filtraciones de memoria
	}



}
