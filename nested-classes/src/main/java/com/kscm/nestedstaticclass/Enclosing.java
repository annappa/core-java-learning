package com.kscm.nestedstaticclass;

public class Enclosing {
    private static int x = 1;

    public static class StaticNested {
        private void run() {
            System.out.println("StaticNested private method run");
        }
    }

    @Test
    public void test() {
        Enclosing.StaticNested nested = new Enclosing.StaticNested();
        nested.run();
    }
}
