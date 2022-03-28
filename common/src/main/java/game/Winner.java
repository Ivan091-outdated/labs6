package game;

public enum Winner {
    CROSS(Paint.CROSS),
    CIRCLE(Paint.CIRCLE),
    DRAW(Paint.NONE),
    NONE(Paint.NONE);

    public final Paint paint;

    Winner(Paint paint) {
        this.paint = paint;
    }
}
