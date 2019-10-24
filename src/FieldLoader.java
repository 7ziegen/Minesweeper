

public class FieldLoader {
    public static boolean[][] loadField() {
        boolean[][] bombs = {
                {true, false, false, false},
                {false, false, true, false},
                {false, true, true, false},
                {true, true, true, false}
        };
        return bombs;
    }
}
