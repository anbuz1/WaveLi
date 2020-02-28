import java.util.*;

public enum Step {
    Up(new int[]{1, 0}), Down(new int[]{-1, 0}), Left(new int[]{0, -1}), Right(new int[]{0, 1}),
    DownLeft(new int[]{-1, -1}), DownRight(new int[]{-1, 1}), UpLeft(new int[]{1, -1}), UpRight(new int[]{1, 1});

    private int[] step;

    Step(int[] step) {
        this.step = step;
    }

    int[] doStep(int[] position) {
        position[0] += step[0];
        position[1] += step[1];
        return position;
    }

    static Set<Step> getAllSteps() {
        return new TreeSet<Step>(Arrays.asList(Up, Down, Left, Right, DownLeft, DownRight, UpLeft, UpRight));
    }
}
