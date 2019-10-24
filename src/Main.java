import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        FieldGenerator fg = new FieldGenerator();

        boolean[][] bomblist = FieldLoader.loadField();
        fg.generateGamefield(bomblist);
        System.out.println(fg.fieldToSting());
    }
}
