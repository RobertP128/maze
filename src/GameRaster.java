public class GameRaster {
    int[][] board;

    public final int FREE=0;
    public final int WALL_V=1;
    public final int WALL_H=2;
    public final int WALL_TL=3;
    public final int WALL_TR=4;
    public final int WALL_BL=5;
    public final int WALL_BR=6;
    public final int WALL_KR=7;
    public final int WALL_TT=8;
    public final int WALL_BT=9;
    public final int WALL_LT=10;
    public final int WALL_RT=11;
    public final int WALL_BV=12;
    public final int WALL_BH=13;
    public final int VISITED=14;

    public GameRaster(int width, int height){
        board=new int[height][width];
    }
}
