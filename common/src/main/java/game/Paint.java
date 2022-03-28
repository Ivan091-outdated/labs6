package game;

public enum Paint {
    CROSS(Winner.CROSS),
    CIRCLE(Winner.CIRCLE),
    NONE(Winner.NONE);

    public final Winner winner;

    Paint(Winner winner) {
        this.winner = winner;
    }
}
