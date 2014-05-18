package com.testgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class TouchPadTest implements ApplicationListener {
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private Texture planeTexture;
	private Sprite planeSprite;
	private float planeSpeed;
	
	//box2d
	World world;  
    Box2DDebugRenderer debugRenderer;  
    static final float BOX_STEP=1/60f;  
    static final int BOX_VELOCITY_ITERATIONS=6;  
    static final int BOX_POSITION_ITERATIONS=2;  
    static final float WORLD_TO_BOX=0.01f;  
    static final float BOX_WORLD_TO=100f;     
    static final float PIXEL_METER_RATIO = 32.0f;
    Body body;
    private float METERS_TO_PIXELS = 32f;
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		//Create camera
		/*float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10f*aspectRatio, 10f);*/
		
		camera = new OrthographicCamera();  
        camera.viewportHeight = 320;  
        camera.viewportWidth = 480;  
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);  
        camera.update();
		
			
		touchpadSkin = new Skin();		
		touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));		
		touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
		
		touchBackground = touchpadSkin.getDrawable("touchBackground");		
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		
		touchpadStyle = new TouchpadStyle();
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		
		touchpad = new Touchpad(10, touchpadStyle);
		touchpad.setBounds(15, 15, 200, 200);
		
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, batch);
		stage.addActor(touchpad);			
		Gdx.input.setInputProcessor(stage);
		
		planeTexture = new Texture(Gdx.files.internal("data/block.png"));
				
		planeSprite = new Sprite(planeTexture);
		planeSprite.setPosition(Gdx.graphics.getWidth()/2-planeSprite.getWidth()/2, Gdx.graphics.getHeight()/2-planeSprite.getHeight()/2);
		planeSprite.setOrigin(planeSprite.getWidth(), planeSprite.getHeight()/2);
		
		planeSpeed = 5;		
		
		
		
		world = new World(new Vector2(0, -100), true);
		 
        BodyDef groundBodyDef = new BodyDef();  
        groundBodyDef.position.set(new Vector2(0, 10));  
        Body groundBody = world.createBody(groundBodyDef);         
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f); 
        groundBody.createFixture(groundBox, 0.0f);       
        
          
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;  
        bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);  
        body = world.createBody(bodyDef);  
        
        /*CircleShape dynamicCircle = new CircleShape();  
        dynamicCircle.setRadius(5f);  
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicCircle;  
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 0.5f;*/
        
        PolygonShape planeBox = new PolygonShape();  
        planeBox.setAsBox(planeSprite.getWidth()/2, planeSprite.getHeight()/2); 
        body.createFixture(planeBox, 0.0f);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = planeBox;
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        
        debugRenderer = new Box2DDebugRenderer();  	    
	}

	@Override
	public void dispose() {
		
	}
	private float convertToPIXELS(float x){
	       return x * METERS_TO_PIXELS;
	    }

	@Override
	public void render() {		
    	Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();
        
        float KnobPercentX = touchpad.getKnobPercentX();
        float KnobPercentY = touchpad.getKnobPercentY(); 
      
        
        
        
        if (touchpad.isTouched())
        {
        	//planeSprite.setX(planeSprite.getX() + KnobPercentX*planeSpeed);
            //planeSprite.setY(planeSprite.getY() + KnobPercentY*planeSpeed);
        	
	        Vector2 tmp = new Vector2(); 
	        tmp.set(KnobPercentX, KnobPercentY);        
	        planeSprite.setRotation(tmp.angle()); 
	        
	        
	        float angle = (float) Math.toRadians(tmp.angle());	        
	        float PositionXbody = body.getPosition().x + KnobPercentX*planeSpeed;
	        float PositionYbody = body.getPosition().y + KnobPercentY*planeSpeed;
	        
	        
	        body.setTransform(PositionXbody , PositionYbody,  angle);	        
	        
        } 
        planeSprite.setX(body.getPosition().x);
        planeSprite.setY(body.getPosition().y);     
        
                
        planeSprite.setRotation((float)Math.toDegrees(body.getAngle())); 
        
        
        
        batch.begin();
        planeSprite.draw(batch);
        batch.end();      
	    stage.act(Gdx.graphics.getDeltaTime());	    
	    stage.draw();     
	    	     
	    
        debugRenderer.render(world, camera.combined);  
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);       
        
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void resize(int width, int height) {		
	}
}
