import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class General {
    private final int BLOCK = -1;
    final int[] startPosition;
    final int[] finishPosition;
    int[][] field;
    int count = 0;
    ArrayList<int[]> arrWay = new ArrayList<>();

    public General(int[] size, List<int[]> blocks, int[] startPosition, int[] finishPosition) throws BadPositionException {
        field = new int[size[0]][size[1]];
        for (int[] block : blocks) {
            if (block[0] != startPosition[0] || block[1] != startPosition[1])
                field[block[0]][block[1]] = BLOCK;
            else throw new BadPositionException("Start field is incorrect");
        }
        field[finishPosition[0]][finishPosition[1]] = -2;
        field[startPosition[0]][startPosition[1]] = -3;
        this.finishPosition = finishPosition;
        this.startPosition = startPosition;
    }

//Проверяет можно ли сделать шаг
    boolean canGo(int[] position, Step step, String way) {
        int check[] = {position[0], position[1]};
        step.doStep(check);
        boolean flag = false;
        try {
            int cell = field[check[0]][check[1]];
            if(!way.equals("back")){
            if (cell != -1 & cell != -2 & cell != -3) flag = true;}
            else flag=true;
        } catch (IndexOutOfBoundsException ex) {
            if (ex != null) return false;
        }
        return flag;
    }


    //Рекурсия шагающая на соседние клетки от исходной. Проверяет значение клеткеи и если его изменила, записывает
// в массив чтобы передать на вызов себя же
    boolean wave(ArrayList<int[]> visitingFields) {
        count++;
        ArrayList<int[]> visitField = new ArrayList<>();
        for (int[] visitingField : visitingFields) {
            for (Step step : Step.getAllSteps()) {
                int[] visitingCell = {visitingField[0], visitingField[1]};
                if (canGo(visitingField, step,"")) {
                    int[] newCell = step.doStep(visitingCell);
                    if (field[newCell[0]][newCell[1]] == 0 ||
                            field[newCell[0]][newCell[1]] > count) {
                        field[newCell[0]][newCell[1]] = count;
                        visitField.add(newCell);
                    }
                }
            }
        }
        if (visitField.size() > 0) wave(visitField);
        return true;
    }

    void findShortWay(int[] position){
        TreeSet<int[]> mySet = new TreeSet<>((x,y)->{
            if (field[x[0]][x[1]] > field[y[0]][y[1]]) return 1;
            else return -1;
        });
        for (Step step : Step.getAllSteps()) {
            int[] visitingCell = {position[0], position[1]};
            if (canGo(position, step, "back")) {
                int[] newCell = step.doStep(visitingCell);
                if(field[newCell[0]][newCell[1]] !=-1 && field[newCell[0]][newCell[1]] != -2)
                mySet.add(newCell);
            }
        }
        if(field[mySet.first()[0]][mySet.first()[1]]!=-3) {
            arrWay.add(mySet.first());
            findShortWay(mySet.first());
        }
    }
// Второй вариант компоратора(то же что и лямда но для ссылочного вари)
//    private int compare(int[] x, int[] y) {
//        if (field[x[0]][x[1]] > field[y[0]][y[1]]) return -1;
//        else return 1;
//
//    }
}
