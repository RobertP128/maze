import java.util.Scanner;

public class Application {

    GameRaster raster;
    RasterView view;

    private final int DIR_U = 0;
    private final int DIR_R = 1;
    private final int DIR_D = 2;
    private final int DIR_L = 3;

    public void init(int width, int height) {
        raster = new GameRaster(width, height);
        view = new RasterView();
    }

    private void createLettace(GameRaster raster) {
        for (int y = 1; y < raster.board.length - 1; y++) {
            for (int x = 1; x < raster.board[y].length - 1; x++) {
                if (y % 2 == 0) {
                    // create vertical lines
                    if (x % 2 == 0) {
                        raster.board[y][x] = raster.WALL_V;
                    } else {
                        raster.board[y][x] = raster.FREE;
                    }
                } else {
                    // create the crosses
                    if (x % 2 == 0) {
                        raster.board[y][x] = raster.WALL_KR;
                    } else {
                        raster.board[y][x] = raster.WALL_H;
                    }
                }
            }
        }
        // Create the border walls
        for (int x = 0; x < raster.board[0].length; x++) {
            if (x == 0) {
                raster.board[0][x] = raster.WALL_TL;
            } else if (x == raster.board[0].length - 1) {
                raster.board[0][x] = raster.WALL_TR;
            } else if (x % 2 == 0) {
                raster.board[0][x] = raster.WALL_TT;
            } else {
                raster.board[0][x] = raster.WALL_BH;
            }
        }
        // create the bottom Wall
        for (int x = 0; x < raster.board[raster.board.length - 1].length; x++) {
            if (x == 0) {
                raster.board[raster.board.length - 1][x] = raster.WALL_BL;
            } else if (x == raster.board[raster.board.length - 1].length - 1) {
                raster.board[raster.board.length - 1][x] = raster.WALL_BR;
            } else if (x % 2 == 0) {
                raster.board[raster.board.length - 1][x] = raster.WALL_BT;
            } else {
                raster.board[raster.board.length - 1][x] = raster.WALL_BH;
            }
        }

        // create the left and right walls
        for (int y = 1; y < raster.board.length - 1; y++) {
            if (y % 2 == 1) {
                raster.board[y][0] = raster.WALL_LT;
                raster.board[y][raster.board[y].length - 1] = raster.WALL_RT;
            } else {
                raster.board[y][0] = raster.WALL_BV;
                raster.board[y][raster.board[y].length - 1] = raster.WALL_BV;
            }
        }

    }

    public void startGame() {
        createLettace(raster);

        // Implement Rasterlogic
        var startPos = new Pos();

        // exit
        startPos.y = raster.board.length-1;
        startPos.x = raster.board[startPos.y].length-4;
        raster.board[startPos.y][startPos.x]=raster.FREE;

        // entry
        startPos.x = 7;
        startPos.y = 0;
        raster.board[startPos.y][startPos.x]=raster.VISITED;


        view.printRaster(raster);
        System.out.println("Starting Maze");
        rekGotoNextPosition(startPos);
        System.out.println("Show result");
        view.printRaster(raster);

    }

    private int[] dirs={DIR_D,DIR_D,DIR_D,DIR_R,DIR_R,DIR_U,DIR_U,DIR_L};
    private int dirsPos=0;
    private Scanner scanner=new Scanner(System.in);
    private int getRandomDir(){
        return (int) (Math.random() * 4.0);
        //return dirs[++dirsPos];
        //System.out.println("dir? 0,1,2,3");
        //return scanner.nextInt();
    }

    private void rekGotoNextPosition(Pos pos) {
        // check
        // binary directions
        // bit0... up
        // bit1... right
        // bit2... down
        // bit3... left
        int checkedDirs = 0;

        // while not all directions checked
        while (checkedDirs != 15) {
            // get some random Dir
            int dir = getRandomDir();
            // check if not already set
            int bitdir = 1 << dir;
            if ((checkedDirs & bitdir) == 0) {
                checkedDirs |= bitdir;
                // Copy the position
                var newPos=new Pos(pos);
                gotoNextPos(newPos,dir,false);
                //System.out.println("Cur:="+newPos.x+":"+newPos.y);
                if (posIsValid(newPos,dir)) {
                    // Mark position as visited
                    var newHalfPos = new Pos(pos);
                    gotoNextPos(newHalfPos,dir,true);
                    raster.board[newHalfPos.y][newHalfPos.x]=raster.VISITED;
                    raster.board[newPos.y][newPos.x]=raster.VISITED;
                    //System.out.println("Interm Result");
                    //view.printRaster(raster);
                    rekGotoNextPosition(newPos);
                }
                else {
                    //System.out.println("Invalid Move!");
                }
            }
        }
        //System.out.println("No way further hear, going back to prev item");


    }

    private boolean posIsValid(final Pos pos,int dir) {

        if (pos.x < 0) return false;
        if (pos.y < 0) return false;
        if (pos.y >= raster.board.length) return false;
        if (pos.x >= raster.board[pos.y].length) return false;

        if (raster.board[pos.y][pos.x] == raster.FREE ||
                raster.board[pos.y][pos.x] == raster.WALL_H ||
                raster.board[pos.y][pos.x] == raster.WALL_V)
        {
            // check if behind the wall there is some visited. We are not allowed to break walls where on the other side we have already been before
            return true;
        }

        return false;
    }

    private void gotoNextPos(Pos pos, final int dir,boolean halfstep) {
        int step=2;
        if (halfstep) step=1;

        if (dir == DIR_U) {
            pos.y-=step;
        } else if (dir == DIR_R) {
            pos.x+=step;
        } else if (dir == DIR_D) {
            pos.y+=step;
        } else if (dir == DIR_L) {
            pos.x-=step;
        }

    }


}
