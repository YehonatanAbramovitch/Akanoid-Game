/**
 * Yehonatan Abramovitch yona132@gmail.com 318800182.
 */
package GameMain;

import GameUpdating.BallRemover;
import GameUpdating.BlockRemover;
import GameUpdating.ScoreIndicator;
import GameUpdating.ScoreTrackingListener;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.Counter;
import Objects.Collidable;
import Objects.GameEnvironment;
import Objects.Sprite;
import Objects.SpriteCollection;
import Objects.Ball;
import Objects.Block;
import Objects.Paddle;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;


/**
 * Game class.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private BlockRemover blockListener;
    private BallRemover ballListener;
    private ScoreTrackingListener scoreListener;
    private ScoreIndicator scoreindicator;


    /**
     * Constructs a new Game instance with an empty SpriteCollection and GameEnvironment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = new Counter();
        this.scoreindicator = new ScoreIndicator(this.score);
    }


    /**
     * Adds a Collidable to the game environment.
     *
     * @param c the Collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a Sprite to the game's SpriteCollection.
     *
     * @param s the Sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game by creating the Blocks and Ball and adding them to the game.
     */
    public void initialize() {
        this.blockListener = new BlockRemover(this, this.blocksCounter);
        this.ballListener = new BallRemover(this, this.ballsCounter);
        this.scoreListener = new ScoreTrackingListener(this.score);
        Ball ball = new Ball(new Point(400, 400), 5, Color.BLACK, this.environment);
        ballsCounter.increase(1);
        ball.addToGame(this);
        Ball ball1 = new Ball(new Point(300, 300), 5, Color.BLACK, this.environment);
        ballsCounter.increase(1);
        ball1.addToGame(this);
        Ball ball2 = new Ball(new Point(500, 500), 5, Color.BLACK, this.environment);
        ballsCounter.increase(1);
        ball2.addToGame(this);
        Block block1 = new Block(new Rectangle(new Point(0, 15), 800, 15, Color.darkGray));
        block1.addToGame(this);
        Block block2 = new Block(new Rectangle(new Point(0, 565), 800, 50, Color.darkGray));
        block2.addHitListener(this.ballListener);
        block2.addToGame(this);
        Block block3 = new Block(new Rectangle(new Point(0, 0), 20, 555, Color.darkGray));
        block3.addToGame(this);
        Block block4 = new Block(new Rectangle(new Point(780, 0), 20, 555, Color.darkGray));
        block4.addToGame(this);
        for (int i = 1; i < 13; i++) {
            Block block = new Block(new Rectangle(new Point(i * 57, 30), 57, 30, Color.yellow));
            blocksCounter.increase(1);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        for (int i = 2; i < 12; i++) {
            Block block = new Block(new Rectangle(new Point(i * 57, 60), 57, 30, Color.white));
            blocksCounter.increase(1);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        for (int i = 3; i < 11; i++) {
            Block block = new Block(new Rectangle(new Point(i * 57, 90), 57, 30, Color.red));
            blocksCounter.increase(1);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        for (int i = 4; i < 10; i++) {
            Block block = new Block(new Rectangle(new Point(i * 57, 120), 57, 30, Color.blue));
            blocksCounter.increase(1);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        for (int i = 5; i < 9; i++) {
            Block block = new Block(new Rectangle(new Point(i * 57, 150), 57, 30, Color.green));
            blocksCounter.increase(1);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        for (int i = 6; i < 8; i++) {
            Block block = new Block(new Rectangle(new Point(i * 57, 180), 57, 30, Color.pink));
            blocksCounter.increase(1);
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        (this.scoreindicator).addToGame(this);
    }

    /**
     * Initializes a new game by creating the paddle and add him to the game.
     *
     * @param keyboard the keyboard sensor for controlling the paddle
     */
    public void initializePaddle(KeyboardSensor keyboard) {
        Paddle p = new Paddle(new Rectangle(new Point(400, 550), 75, 15, Color.black), keyboard);
        p.addToGame(this);
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        GUI gui = new GUI("Title", 800, 600);
        Sleeper sleeper = new Sleeper();
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        initializePaddle(keyboard);
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (this.blocksCounter.getValue() == 0) {
                score.increase(100);
                gui.close();
                return;
            }
            if (this.ballsCounter.getValue() == 0) {
                gui.close();
                return;
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes the given collidable from the game environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes the given sprite from the game sprites.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
