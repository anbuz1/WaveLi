import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Go {
    public static void main(String[] args) {
        int[] block = {2, 1};
        try {
// В конструктор передаем размер поля, список массивов с координатами блоков, массив координат стартовоя ячейки,
// массив координат финишной ячейкм
            General general = new General(new int[]{9, 9}, new ArrayList<>(Collections.singleton(block)),
                    new int[]{0, 0}, new int[]{7, 6});
            general.wave(new ArrayList<int[]>(Collections.singleton(general.startPosition)));
            general.findShortWay(general.finishPosition);
            for (int[] ints : general.arrWay) {
                general.field[ints[0]][ints[1]] = 0;
            }


            Arrays.stream(general.field).forEach(y ->
            {
                Arrays.stream(y).forEach(x -> {
                    switch (x) {
                        case -1:
                            System.out.print("B "); //Означает блок-ячейку
                            break;
                        case -2:
                            System.out.print("F "); //Означает финиш
                            break;
                        case -3:
                            System.out.print("S ");//Означает старт
                            break;
                        default:
                            System.out.print(x + " ");
                    }
                });
                System.out.println();
            });
        } catch (BadPositionException e) {
            e.printStackTrace();
        }


    }
}
