import org.junit.*;

import java.io.File;


public class FieldTest {
    @Test()
    public void test1() throws Exception {
        boolean[][] bombs = {
                {true, false, false, false},
                {true, true, true, false},
                {true, true, true, false},
                {true, true, true, false}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "*, 4, 2, 1\n" +
                "*, *, *, 2\n" +
                "*, *, *, 3\n" +
                "*, *, *, 2", fg.fieldToSting());
    }

    @Test()
    public void test2() throws Exception {
        boolean[][] bombs = {
                {true, false, false, false},
                {false, false, true, false},
                {false, true, true, false},
                {true, true, true, false}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "*, 2, 1, 1\n" +
                        "2, 4, *, 2\n" +
                        "3, *, *, 3\n" +
                        "*, *, *, 2", fg.fieldToSting());
    }

    @Test()
    public void test3() throws Exception {
        boolean[][] bombs = {
                {false, false, false}

        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "0, 0, 0", fg.fieldToSting());
    }

    @Test()
    public void test4() throws Exception {
        boolean[][] bombs = {
                {false, true, false}

        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "1, *, 1", fg.fieldToSting());
    }

    @Test()
    public void test5() throws Exception {
        boolean[][] bombs = {
                {false, true, false},
                {false, true, false}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "2, *, 2\n" +
                "2, *, 2",
                fg.fieldToSting());
    }

    @Test()
    public void test6() throws Exception {
        boolean[][] bombs = {
                {true, true, false},
                {false, true, false}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "*, *, 2\n" +
                        "3, *, 2",
                fg.fieldToSting());
    }

    @Test()
    public void test7() throws Exception {
        boolean[][] bombs = {
                {true, true},
                {false, true}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "*, *\n" +
                        "3, *",
                fg.fieldToSting());
    }

    @Test()
    public void test8() throws Exception {
        boolean[][] bombs = {
                {true, true},
                {false, false},
                {false, true},
                {false, true}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "*, *\n" +
                        "3, 3\n" +
                        "2, *\n" +
                        "2, *",
                fg.fieldToSting());
    }

    @Test()
    public void test9() throws Exception {
        boolean[][] bombs = {
                {true},
                {false},
                {false},
                {false}
        };
        FieldGenerator fg = new FieldGenerator();
        fg.generateGamefield(bombs);
        Assert.assertEquals("wrong result",
                "*\n" +
                        "1\n" +
                        "0\n" +
                        "0",
                fg.fieldToSting());
    }
}
