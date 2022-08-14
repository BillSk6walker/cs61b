package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private static final long SEED = 2873123;

    private static final Random RANDOM = new Random(SEED);
    /**draw one line of length N with the start left at(X,Y)*/
    public static void drawLine(int N,int X,int Y,TETile[][] tiles,TETile context){
        for(int i=X;i<X+N;i++){
            tiles[i][Y] = context;
            context = TETile.colorVariant(context,50,50,50,RANDOM);
        }
    }
    /**draw a hexagon of size N,with the left top at position (X,Y)*/
    public static void drawHexagon(int N,int X,int Y,TETile[][] tiles,TETile context){
        if(X-N < -1 || Y-2*N < -1 || X+2*N>WIDTH || Y >= HEIGHT){
            throw new RuntimeException("The hexagon cannot be drawn here!\n");
        }
        else {
            //draw the top half
            int count = N;
            for(int i=Y; i>Y-N; i--){
                drawLine(count,X,i,tiles,context);
                count+=2;
                X--;
            }
            count-=2;
            X++;
            //draw the low half
            for(int i=Y-N; i>Y-2*N;i--){
                drawLine(count,X,i,tiles,context);
                count-=2;
                X++;
            }
        }
    }
    /**return a random tile*/
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.MOUNTAIN;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.TREE;
            case 4: return Tileset.SAND;
            default: return Tileset.NOTHING;
        }
    }
    /** draw a line of hexagons with number N of the size of each,
     * number of how many that is needed to draw,
     * and (X,Y) for the right top of the hexagons' left top
     * the tile of each small hexagon is randomly choosed with the seed
      */
    public static void drawManyHexagons(int N,int number,int X,int Y,TETile[][] tiles){
        for(int i = 0;i <number; i++){
            TETile tile = randomTile();
            drawHexagon(N,X,Y,tiles,tile);
            X-=2*N-1;
            Y-=N;
        }

    }
    /**draw the world that is made of small hexagons,
     * the tile of each small hexagon is randomly chosen with the seed,
     * X and Y should be the address of the top hexagon's top left*/
    public static void drawHexWorld(int N,int X,int Y,TETile[][] tiles){
        for(int i=3;i<6;i++){
            drawManyHexagons(N,i,X,Y,tiles);
            X+=2*N-1;
            Y-=N;
        }
        X-=2*N-1;
        Y-=N;
        for(int i=4;i>2;i--){
            drawManyHexagons(N,i,X,Y,tiles);
            Y-=2*N;
        }
    }
    public static void main(String[] args){
        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        //initialize the tile with NOTHING
        for(int i=0;i<WIDTH;i++){
            for(int j=0; j<HEIGHT;j++){
                tiles[i][j]=Tileset.NOTHING;
            }
        }
        drawHexWorld(5,50,74,tiles);
        ter.renderFrame(tiles);
    }

}
