package anohin.artem.com.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Artem on 07.04.2016.
 */
public class Bird {
    public static final int MOVEMENT = 115;
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Sound sound;

    private Texture texture;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getHeight(), texture.getWidth() / 3);
        sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0)
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        if (position.y < 0) {
            position.y = 2;
        }
        if (position.y > 370) {
            position.y = 370;
        }
        position.add(MOVEMENT * dt, velosity.y, 0);
        velosity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);

    }

    public void jump() {
        velosity.y = 200;
        sound.play();
    }

    public void dispose() {
        texture.dispose();
        sound.dispose();
    }
}
