package indy.PlatformPackage;

import indy.Constants;
import indy.Keys;
import indy.Player;
import indy.Queue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class Board {
    private static Platforms[][] borderArray;
    private Platforms myPlatform;
    private Queue keyOrder;
    private Queue keyOrderBackUp;
    private static final ArrayList<Platforms> SBArray = new ArrayList<>();
    /**
     * Constructor for the board class
     */
    public Board() {
        borderArray = new Platforms[Constants.BOARD_WIDTH_HEIGHT][Constants.BOARD_WIDTH_HEIGHT];
    }
    /**
     * Method that places the platforms an isometric way.
     */
    public void setBoard(int row, int col) {
        borderArray[row][col] = this.myPlatform;
        (borderArray[row][col]).setX(col * Constants.HORIZONTAL_MOVE +(row-1)*
                Constants.VERTICAL_MOVE_X+Constants.PLATFORM_OFFSET_X);
        (borderArray[row][col]).setY(row * Constants.VERTICAL_MOVE_Y+Constants.PLATFORM_OFFSET_Y);
    }
    /**
     * Method that shuffles the order of the keys
     */
    public Queue shuffleKeys(int numberOfKeys) {
        this.keyOrder = new Queue();
        this.keyOrderBackUp = new Queue();
        this.keyOrderBackUp.clear();
        ArrayList<Integer> keyArray = new ArrayList<>();
        for(int i=1; i<numberOfKeys+1;i++) {
            keyArray.add(i);
        }
        for(int i=0; i<numberOfKeys;i++) {
            int randomInt = (int) (Math.random() * (numberOfKeys-i));
            this.keyOrder.enqueue(keyArray.get(randomInt));
            this.keyOrderBackUp.enqueue(keyArray.get(randomInt));
            keyArray.remove(randomInt);
        }
        return this.keyOrder;
    }
    /**
     * Method that reads the blueprints for the map and fills it with platforms
     */
    public void setUpBoard(Pane blockPane, int[][] boardBluePrint, int numberOfKeys,
                           ArrayList<Keys> keyArray, boolean safeReset) {
        /**
         * set up the keys for the level
         */
        Keys myKeys = new Keys(blockPane, numberOfKeys);
        myKeys.remove(blockPane);
        myKeys.keyGeneration(numberOfKeys,blockPane, keyArray);
        if(!safeReset) {
            this.shuffleKeys(numberOfKeys);
        }
        /**
         * Checks which platforms need to be placed according to the blueprints
         */
        for (int row = 0; row < borderArray.length; row++) {
            for (int col = 0; col < borderArray[0].length; col++) {
                switch(boardBluePrint[row][col]) {
                    case 0:
                        borderArray[row][col]=null;
                        break;
                    case 1:
                        this.myPlatform = new Platforms();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        break;
                    case 2:
                        this.myPlatform = new SeparationPlat();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        break;
                    case 3:
                        this.myPlatform = new EndPlat();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        break;
                    case 4:
                        this.myPlatform = new Platforms();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);

                        int x = col * Constants.HORIZONTAL_MOVE + (row - 1)
                                * Constants.VERTICAL_MOVE_X + Constants.PLATFORM_OFFSET_Y;

                        if(safeReset) {
                            this.keyOrderBackUp.enqueue(this.keyOrderBackUp.getIndex0());
                            myKeys.setKey(x,row * Constants.VERTICAL_MOVE_Y + Constants.PLATFORM_OFFSET_Y,
                                    this.keyOrderBackUp.dequeue());
                        } else {
                            myKeys.setKey(x, row * Constants.VERTICAL_MOVE_Y + Constants.PLATFORM_OFFSET_Y,
                                    this.keyOrder.dequeue());
                        }
                        break;
                    case 5:
                        this.myPlatform = new BigBPlat();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        SBArray.add(myPlatform);
                        break;
                    case 6:
                        this.myPlatform = new SmallBPlat();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        SBArray.add(myPlatform);
                        break;
                    case 7:
                        this.myPlatform =new LifePlat();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        LifePlat.heartSetUp(blockPane,col * Constants.HORIZONTAL_MOVE +
                                (row-1)*Constants.VERTICAL_MOVE_X-Constants.LIFE_OFFSET_PLAT,
                                row * Constants.VERTICAL_MOVE_Y-Constants.VERTICAL_MOVE_Y);
                        break;
                    case 8:
                        this.myPlatform =new TPPlat();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        break;
                    case 9:
                        this.myPlatform = new OriginPlatform();
                        setBoard(row,col);
                        borderArray[row][col].addToPane(blockPane);
                        break;
                }
            }
        }
    }
    /**
     * Method that checks if the player is out of the level bounds
     */
    public boolean shiftCheck(Player player, boolean fall, int minX, int maxX) {
        if (!(player.getX()>=minX&&player.getX()<=maxX&&!this.gridCheck(
                (player.getX()-minX)/Constants.HORIZONTAL_MOVE,
                (player.getY()-Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y))) {
            fall = true;
        }
        return fall;
    }
    /**
     * Method that sets the fall boolean if the player is out of bounds
     */
    public Boolean fall(Player player, boolean fall) {
            switch (player.getY()) {
                case 75:
                    fall=shiftCheck(player,fall,Constants.X_MIN_1,Constants.X_MAX_1);
                    break;
                case 107:
                    fall=shiftCheck(player,fall,Constants.X_MIN_2,Constants.X_MAX_2);
                    break;
                case 139:
                    fall=shiftCheck(player,fall,Constants.X_MIN_3,Constants.X_MAX_3);
                    break;
                case 171:
                    fall=shiftCheck(player,fall,Constants.X_MIN_4,Constants.X_MAX_4);
                    break;
                case 203:
                    fall=shiftCheck(player,fall,Constants.X_MIN_5,Constants.X_MAX_5);
                    break;
                case 235:
                    fall=shiftCheck(player,fall,Constants.X_MIN_6,Constants.X_MAX_6);
                    break;
                case 267:
                    fall=shiftCheck(player,fall,Constants.X_MIN_7,Constants.X_MAX_7);
                    break;
                case 299:
                    fall=shiftCheck(player,fall,Constants.X_MIN_8,Constants.X_MAX_8);
                    break;
                case 331:
                    fall=shiftCheck(player,fall,Constants.X_MIN_9,Constants.X_MAX_9);
                    break;
                case 363:
                    fall=shiftCheck(player,fall,Constants.X_MIN_10,Constants.X_MAX_10);
                    break;
                default:
                    fall = true;
            }
            return fall;
    }
    /**
     * Method that checks if a platform is empty
     */
    public boolean gridCheck(int x, int y) {
        return borderArray[y][x] == null;
    }
    /**
     * Method that checks the color of a platform
     */
    public static boolean platCheck(int x, int y, Color color, boolean smallState) {
        if(borderArray[x][y]==null){
            return smallState;
        }
        return borderArray[x][y].getFill()==color;
    }
    /**
     * Method that goes through all the big and small player platforms and
     * checks if they can be lit up
     */
    public void checkLight(boolean smallState){
        for (Platforms sb: SBArray) {
            sb.lightUp(smallState);
        }
    }
}

