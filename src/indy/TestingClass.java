package indy;

import indy.PlatformPackage.Board;


public class TestingClass {
    /**
     * Constructor for the testing class
     */
    public TestingClass () {
    }
    Board map;
    /**
     * Test for the shuffle key method
     */

    public void Test1() {
        map=new Board();
        Queue queue = map.shuffleKeys(Constants.KEY_NUMBER_4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
