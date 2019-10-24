import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FieldGenerator {
    private Map<Integer, Method> commandList;
    private int[][] gameField;
    public FieldGenerator() {
        this.commandList = new HashMap<Integer, Method>();
        try {
            commandList.put(0, this.getClass().getMethod("handleMiddle", int[][].class, int.class, int.class));
            commandList.put(1, this.getClass().getMethod("handleTop", int[][].class, int.class, int.class));
            commandList.put(2, this.getClass().getMethod("handleBottom", int[][].class, int.class, int.class));
            commandList.put(4, this.getClass().getMethod("handleLeft", int[][].class, int.class, int.class));
            commandList.put(8, this.getClass().getMethod("handleRight", int[][].class, int.class, int.class));
            commandList.put(5, this.getClass().getMethod("handleTopLeft", int[][].class, int.class, int.class));
            commandList.put(9, this.getClass().getMethod("handleTopRight", int[][].class, int.class, int.class));
            commandList.put(6, this.getClass().getMethod("handleBottomLeft", int[][].class, int.class, int.class));
            commandList.put(10, this.getClass().getMethod("handleBottomRight", int[][].class, int.class, int.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
    public void generateGamefield(boolean[][] bombslist) throws Exception {
        if(!boardCheck(bombslist)) {
            throw new Exception("Dimension does not match.");
        }
        int[][] gamefield = new int[bombslist.length][bombslist[0].length];
        if (bombslist.length < 3 || bombslist[0].length < 3) {
            this.handleSpecialBoard(bombslist);
        } else {
            for (int i = 0; i < bombslist.length; i++) {
                for (int j = 0; j < bombslist[0].length; j++) {
                    if (bombslist[i][j]) {
                        gamefield[i][j] = -9;
                        // posCode
                        // 0000 0 middle
                        // 0001 1 top
                        // 0010 2 bottom
                        // 0100 4 left
                        // 1000 8 right
                        // 0101 5 top left
                        // 1001 9 top right
                        // 0110 6 bottom left
                        // 1010 10 bottom right
                        int posCode = 0;
                        posCode += i == 0 ? 1 : 0;
                        posCode += i == bombslist.length - 1 ? 2 : 0;
                        posCode += j == 0 ? 4 : 0;
                        posCode += j == bombslist[0].length - 1 ? 8 : 0;
                        try {
                            commandList.get(posCode).invoke(this, gamefield, i, j);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            this.gameField = gamefield;
        }

    }

    private void handleSpecialBoard(boolean[][] bombslist) {
        int[][] gamefield = new int[bombslist.length][bombslist[0].length];
        for(int i = 0; i < bombslist.length; i++) {
            for(int j = 0; j < bombslist[0].length; j++) {
                if(bombslist[i][j]) {
                    gamefield[i][j] = -9;
                    // Top left
                    if (i - 1 > -1 && j - 1 > -1) {
                        gamefield[i - 1][j - 1] += 1;
                    }
                    // Top
                    if (i - 1 > -1) {
                        gamefield[i - 1][j] += 1;
                    }
                    // Top right
                    if (i - 1 > -1 && j + 1 < bombslist[0].length) {
                        gamefield[i - 1][j + 1] += 1;
                    }
                    // left
                    if (j - 1 > -1) {
                        gamefield[i][j - 1] += 1;
                    }
                    // right
                    if (j + 1 < bombslist[0].length) {
                        gamefield[i][j + 1] += 1;
                    }
                    // bottom left
                    if (i + 1 < bombslist.length && j - 1 > -1) {
                        gamefield[i + 1][j - 1] += 1;
                    }
                    // bottom
                    if (i + 1 < bombslist.length) {
                        gamefield[i + 1][j] += 1;
                    }
                    // bottom right
                    if (i + 1 < bombslist.length && j + 1 < bombslist[0].length) {
                        gamefield[i + 1][j + 1] += 1;
                    }
                }
            }
        }
        this.gameField = gamefield;
    }

    // Check Board Dimension
    private boolean boardCheck(boolean[][] bombslist) {
        for(int i = 0; i < bombslist.length; i++) {
            if (bombslist[i].length != bombslist[0].length) {
                return false;
            }
        }
        return true;
    }

    public String fieldToSting() {
        return Arrays.deepToString(this.gameField).replace("], ", "\n").replace("[[", "").replace("]]", "").replace("[", "").replaceAll("-[1-9]","*");
    }


    public void handleMiddle(int[][] gamefield, int i, int j) {
        // Top left
        gamefield[i - 1][j - 1] += 1;
        // Top
        gamefield[i - 1][j] += 1;
        // Top right
        gamefield[i - 1][j + 1] += 1;
        // left
        gamefield[i][j - 1] += 1;
        // right
        gamefield[i][j + 1] += 1;
        // bottom left
        gamefield[i + 1][j - 1] += 1;
        // bottom
        gamefield[i + 1][j] += 1;
        // bottom right
        gamefield[i + 1][j + 1] += 1;
    }
    public void handleTopLeft(int[][] gamefield, int i, int j) {
        // right
        gamefield[i][j + 1] += 1;
        // bottom
        gamefield[i + 1][j] += 1;
        // bottom right
        gamefield[i + 1][j + 1] += 1;
    }
    public void handleTop(int[][] gamefield, int i, int j) {
        // left
        gamefield[i][j - 1] += 1;
        // right
        gamefield[i][j + 1] += 1;
        // bottom left
        gamefield[i + 1][j - 1] += 1;
        // bottom
        gamefield[i + 1][j] += 1;
        // bottom right
        gamefield[i + 1][j + 1] += 1;
    }

    public void handleTopRight(int[][] gamefield, int i, int j) {
        // left
        gamefield[i][j - 1] += 1;
        // bottom left
        gamefield[i + 1][j - 1] += 1;
        // bottom
        gamefield[i + 1][j] += 1;
    }

    public void handleLeft(int[][] gamefield, int i, int j) {
        // Top
        gamefield[i - 1][j] += 1;
        // Top right
        gamefield[i - 1][j + 1] += 1;
        // right
        gamefield[i][j + 1] += 1;
        // bottom
        gamefield[i + 1][j] += 1;
        // bottom right
        gamefield[i + 1][j + 1] += 1;
    }

    public void handleRight(int[][] gamefield, int i, int j) {
        // Top left
        gamefield[i - 1][j - 1] += 1;
        // Top
        gamefield[i - 1][j] += 1;
        // left
        gamefield[i][j - 1] += 1;
        // bottom left
        gamefield[i + 1][j - 1] += 1;
        // bottom
        gamefield[i + 1][j] += 1;
    }
    public void handleBottomLeft(int[][] gamefield, int i, int j) {
        // Top
        gamefield[i - 1][j] += 1;
        // Top right
        gamefield[i - 1][j + 1] += 1;
        // right
        gamefield[i][j + 1] += 1;
    }
    public void handleBottom(int[][] gamefield, int i, int j) {
        // Top left
        gamefield[i - 1][j - 1] += 1;
        // Top
        gamefield[i - 1][j] += 1;
        // Top right
        gamefield[i - 1][j + 1] += 1;
        // left
        gamefield[i][j - 1] += 1;
        // right
        gamefield[i][j + 1] += 1;
    }
    public void handleBottomRight(int[][] gamefield, int i, int j) {
        // Top left
        gamefield[i - 1][j - 1] += 1;
        // Top
        gamefield[i - 1][j] += 1;
        // left
        gamefield[i][j - 1] += 1;
    }
}
