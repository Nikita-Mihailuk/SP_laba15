import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader bf = new BufferedReader( new FileReader("file.txt"))){

            int countRow = Integer.parseInt(bf.readLine()); // считываем первую строку, содержащую количество мест

            List<int[]> places = new ArrayList<>();

            for (int i = 0; i < countRow; i++) {
                String[] temp = bf.readLine().split(" "); // чтение ряда и места в каждой строке

                places.add(new int[]{Integer.parseInt(temp[0]),Integer.parseInt(temp[1])});
            }
            // сортировка всех пар сначала по номеру ряда по возрастанию, а затем по номеру места по убыванию
            places.sort(Comparator.comparingInt((int[] a) -> a[0]).
                    thenComparing((int[] a) -> a[1], Comparator.reverseOrder()));

            int maxRow  = 0, minPlace = 0;

            for (int i = 1; i < places.size(); i++) {
                // если номер ряда совпадает с предыдущим и при этом разница между местами равна 3, то это подходящее место
                // найдётся максимальный номер ряда, т. к. пары отсортированы
                if (places.get(i)[0] == places.get(i - 1)[0] && places.get(i)[1] - places.get(i - 1)[1] == -3) {

                    maxRow  = places.get(i)[0];
                    minPlace = places.get(i)[1]+1;// прибавляем 1, это и будет свободное место
                }
            }

            System.out.println(maxRow  + " " + minPlace);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}