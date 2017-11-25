package entity;

/**
 * Created by ZhijunHong on 2017/11/24.
 */

public abstract class Computer {
    protected String mBoard;
    protected  String mDisplay;
    protected String mOS;

    public Computer() {
    }

    public void setBoard(String board) {
        mBoard = board;
    }

    public void setDisplay(String display) {
        mDisplay = display;
    }

    public abstract void setOS();

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOS='" + mOS + '\'' +
                '}';
    }
}
