public class RasterView {
    public void printRaster(GameRaster raster) {
        // Print the raster
        for (int y = 0; y < raster.board.length; y++) {
            for (int x = 0; x < raster.board[y].length; x++) {

                if (raster.board[y][x] == raster.FREE) {
                    System.out.print(" ");
                } else if (raster.board[y][x] == raster.WALL_V) {
                    System.out.print("\u2502");
                }else if (raster.board[y][x] == raster.WALL_KR) {
                    System.out.print("\u253c");
                }else if (raster.board[y][x] == raster.WALL_TL) {
                    System.out.print("\u2554");
                }else if (raster.board[y][x] == raster.WALL_TR) {
                    System.out.print("\u2557");
                }else if (raster.board[y][x] == raster.WALL_BL) {
                    System.out.print("\u255A");
                }else if (raster.board[y][x] == raster.WALL_BR) {
                    System.out.print("\u255D");
                }else if (raster.board[y][x] == raster.WALL_H) {
                    System.out.print("\u2500");
                }else if (raster.board[y][x] == raster.WALL_TT) {
                    System.out.print("\u2564");
                }else if (raster.board[y][x] == raster.WALL_BT) {
                    System.out.print("\u2567");
                }else if (raster.board[y][x] == raster.WALL_LT) {
                    System.out.print("\u255F");
                }else if (raster.board[y][x] == raster.WALL_RT) {
                    System.out.print("\u2562");
                }else if (raster.board[y][x] == raster.WALL_BH) {
                    System.out.print("\u2550");
                }else if (raster.board[y][x] == raster.WALL_BV) {
                    System.out.print("\u2551");
                }else if (raster.board[y][x] == raster.VISITED) {
                    System.out.print("\u2588");
                }
                else {
                    System.out.print("?");
                }
            }
            System.out.println();
        }
    }
}
