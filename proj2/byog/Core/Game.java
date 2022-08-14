package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int ROOMMIN = 2;
    public static final int ROOMMAX = 5;
    public static long seed = 0;// the default seed,should be overwritten by the methods
    public static Random RANDOM;
    public static  final int[][] DIRECTIONS = new int[][]{{0, 1}, {0,-1}, {-1,0}, {1,0}};

    /**
     * set the whole world to a nothing
     * usually used for initialization
     *
     * @param tiles the tile that needs to implement
     */
    public void setToNothing(TETile[][] tiles) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = Tileset.NOTHING;//change NOTHING to what you want the background to be
            }
        }
    }

    /**
     * find out the next start place of the current one
     * return the list with first showing how many choice to go for (tree recursion)
     * @param xMin  the x left bound
     * @param xMax  the x right bound
     * @param yMin  the y lower bound
     * @param yMax  the y upper bound
     * @param type  the type of the space:0 for tunnel and 1 for room
     * @return a list of integers, first is the how many pairs and two for another X and another Y
     */
    public int[] findOut(int xMin,int xMax,int yMin,int yMax,int direction,int type){
        int[] ret = new int[100];//big enough to hold
        if(type==0){//a tunnel
            ret[0]=1;//only one way out for a tunnel
            if(direction==0 || direction==1){
                int out= RANDOM.nextInt(yMin+1,yMax+1);
                ret[2] = out;
                int nextDirection = RANDOM.nextInt(2);
                if (nextDirection == 0){
                    ret[1] = xMax-1;
                }
                else{
                    ret[1] = xMax+1;
                }
            }
            else{
                int out= RANDOM.nextInt(xMin+1,xMax+1);
                ret[1] = out;
                int nextDirection = RANDOM.nextInt(2);
                if (nextDirection == 0){
                    ret[2] = yMax-1;
                }
                else{
                    ret[2] = yMax+1;
                }
            }
        }
        else{//a room
            int out = RANDOM.nextInt(5);//at most four ways out
            ret[0] = out;
            for(int i=1;i<2*ret[0]+1;i+=2){
                if(out==4){
                    out=0;
                }
                switch (out){
                    case 0:{
                        ret[i] = RANDOM.nextInt(xMin,xMax+1);
                        ret[i+1] = yMax+1;
                        break;
                    }
                    case 1:{
                        ret[i] = RANDOM.nextInt(xMin,xMax+1);
                        ret[i+1] = yMin-1;
                        break;
                    }
                    case 2:{
                        ret[i+1] = RANDOM.nextInt(yMin,yMax+1);
                        ret[i] = xMin-1;
                        break;
                    }
                    case 3:{
                        ret[i+1] = RANDOM.nextInt(yMin,yMax+1);
                        ret[i] = xMax+1;
                        break;
                    }
                }
                out++;
            }

        }
        return ret;
    }

    /**
     * choose the space for the given location
     * judge whether to build a tunnel or a room
     * return a list of int with five elements
     * the first for the type of the space(0 for the tunnel and 1 for the room)
     * the next four for xMin,xMax,yMin,yMax
     */
    public static int[] chooseSpace(int X,int Y, int direction){
        //TODO:change this to change the chance of room size,etc.
        int choice = RANDOM.nextInt(4);
        int[] ret = new int[5];
        ret[1] = X;//xMin
        ret[2] = X;//xMax
        ret[3] = Y;//yMin
        ret[4] = Y;//yMax
        if(choice < 3){//tunnel
            ret[0]= 0;
            choice = RANDOM.nextInt(2,8);//the length of the tunnel
            switch (direction) {
                case 0: {//up
                    ret[4] += choice;
                    break;
                }
                case 1: {//down
                    ret[3] -= choice;
                    break;
                }
                case 2: {//left
                    ret[1] -= choice;
                    break;
                }
                case 3: {//right
                    ret[2] += choice;
                    break;
                }
            }
        }
        else{//room
            ret[0] = 1;
            int width = RANDOM.nextInt(ROOMMIN,ROOMMAX);//the width of the room
            int height = RANDOM.nextInt(ROOMMIN,ROOMMAX);//the height of the room
            int maxOffset;
            if(direction == 0 || direction == 1){//up or down
                maxOffset = width;
            }
            else {//right or left
                maxOffset = height;
            }
            int offset = RANDOM.nextInt(0,maxOffset);
            switch (direction) {
                case 0: {//up
                    ret[4] += height;
                    ret[1] -= offset;
                    ret[2] += width-offset;
                    break;
                }
                case 1: {//down
                    ret[3] -= height;
                    ret[1] -= offset;
                    ret[2] += width-offset;
                    break;
                }
                case 2: {//left
                    ret[1] -= width;
                    ret[3] -= offset;
                    ret[4] += height-offset;
                    break;
                }
                case 3: {//right
                    ret[2] += width;
                    ret[3] -= offset;
                    ret[4] += height-offset;
                    break;
                }
            }

        }
        return  ret;
    }
    /**
     * check whether the room/tunnel can be put at the given location
     * return the boolean value
     * If it's ok to build,then build the room/tunnel
     *
     * @param tiles the world to check
     * @param xMin  the x left bound
     * @param xMax  the x right bound
     * @param yMin  the y lower bound
     * @param yMax  the y upper bound
     * @param type  the type of the space:0 for tunnel and 1 for room
     * @return the boolean value of whether it can be built
     */
    public boolean buildSpace(TETile[][] tiles, int xMin, int xMax, int yMin, int yMax, int type) {
        if (xMin <= 0 || xMax >= WIDTH-1 || yMin <= 0 || yMax >= HEIGHT-1) {
            return false;//out of the screen cannot be built
        }

        //the inner side of the space has to be NOTHING
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                if (tiles[i][j] != Tileset.NOTHING) {
                    return false;//this place has been used,no way to build it here
                }
            }
        }
        //TODO:the surrounding of the space has to be either NOTHING or WALL? need to be thought of later

        // now the space can be used for building!
        if (type == 0) {//the space is a tunnel
            TETile background = Tileset.FLOOR;
            for (int i = xMin; i <= xMax; i++) {
                for (int j = yMin; j <= yMax; j++) {
                    background = TETile.colorVariant(background, 30, 30, 30, RANDOM);
                    tiles[i][j] = background;//TODO:Make more choices later!
                }
            }
        } else {//the space is a room
            TETile background = Tileset.MOUNTAIN;
            for (int i = xMin; i <= xMax; i++) {
                for (int j = yMin; j <= yMax; j++) {
                    background = TETile.colorVariant(background, 30, 30, 30, RANDOM);
                    tiles[i][j] = background;//TODO:Make more choices later!
                }
            }
        }
        return true;
    }

    /**
     * set the context of the world using recursion
     *
     * @param tiles the world that needs to set
     * @param X     the current X position
     */
    public void setContext(TETile[][] tiles, int X, int Y) {

        int direction=RANDOM.nextInt(4);
        for(int i=0;i<4;i++){
            int x=X;
            int y=Y;
            x+=DIRECTIONS[direction][0];
            y+=DIRECTIONS[direction][0];
            int[] space = chooseSpace(x,y,direction);
            if(buildSpace(tiles,space[1],space[2],space[3],space[4],space[0])){
                int[] togoto = findOut(space[1],space[2],space[3],space[4],direction,space[0]);
                for(int k=1;k<togoto[0]*2+1;k+=2){
                    setContext(tiles,togoto[k],togoto[k+1]);
                }
            }
            direction+=1;
            if(direction==4){
                direction=0;//no direction that equals to 4!
            }
        }


    }

    /**
     * generate the maze with the seed
     */
    public void generateMaze(TETile[][] tiles) {

        setToNothing(tiles);//initialize the tile
        setContext(tiles, RANDOM.nextInt(WIDTH), RANDOM.nextInt(HEIGHT));
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().


        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        seed = 500;//DEBUGGING
        RANDOM = new Random(seed);
        generateMaze(finalWorldFrame);//generate the primitive version of the world

        TERenderer ter = new TERenderer();//DEBUGGING
        ter.initialize(WIDTH, HEIGHT);//DEBUGGING
        ter.renderFrame(finalWorldFrame);//DEBUGGING
        return finalWorldFrame;
    }
}
