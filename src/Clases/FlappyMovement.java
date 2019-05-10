package Clases;

import Interfaz.Game;
import java.awt.Toolkit;

public class FlappyMovement extends Thread {

    private int deltaTime;
    private boolean jump;
    private boolean stopJump1 = false;
    private boolean stopJump2 = true;
    private boolean jumping = false;
    private final Game parent;
    public static boolean stopThread;
    private int y0;
    public static final float g = 9.8f;
    private int v0 = -30;
    long tInit;
    
    public FlappyMovement(Game parent) {
        this.deltaTime = 10;
        this.parent = parent;
    }
    @Override
    public void run() {
        int varA = 1;
        stopThread = false;
        y0 = Game.jFlappy.getLocation().y;
        tInit = System.currentTimeMillis(); //timepo en s desde 1970
        float t;
        while (true) {
            t = (System.currentTimeMillis() - tInit)/100.0f;
            Toolkit.getDefaultToolkit().sync();
            if (stopThread) {
                break;
            }
            int x = Game.jFlappy.getLocation().x;
            float y = y0 + (v0 * t) + (0.5f * g * (t * t));
            if (y <= 0) {
                y = 1;
            }
            Game.jFlappy.setLocation(x, (int) y);
            try {
                Thread.sleep(getDeltaTime());
            } catch (InterruptedException ex) {
                System.out.println("" + ex);
            }
            this.parent.detectColision();
            this.parent.validarChoqueTubos();
        }
    }
    public void jump(){
        tInit = System.currentTimeMillis();
        y0 = Game.jFlappy.getLocation().y;
    }
    
    public int getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    public boolean isStopThread() {
        return stopThread;
    }

}
