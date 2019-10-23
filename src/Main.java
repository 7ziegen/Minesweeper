import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        FieldGenerator fg = new FieldGenerator();

        boolean[][] bomblist = FieldLoader.loadField();
        int[][] gamefield = fg.generateGamefield(bomblist);
        System.out.println(Arrays.deepToString(gamefield).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
