/*Реализовать волновой алгоритм базовая версия: 4 направления движения */


import java.util.ArrayDeque;
import java.util.Arrays;


public class Maze {

    public static void main(String[] args) {

        String maze[][] = new String [21][21];

        // Создаём базу для лабиринта - массив 21 на 21 и заполняем его значками #
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                maze[i][j] = "#";
            }
        }

        // Запускаем внутрь кротика, который будет прогрызать в массиве породы из решёток рандомный путь.
        
        int count = 100000;
        while (count > 0) {
            mole(maze);
            count--;
        }
        for (String[] arr : maze) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        waveAlgoritm(maze);

        for (String[] arr : maze) {
            System.out.println(Arrays.toString(arr));
        }

    }

    // Генерирует случайные координаты и проверяет попал ли он в клетку со стенкой. Переходит в move если да.
    public static void mole(String[][] maze) { 
        int coordinate1 = (int) (Math.random() * 11)*2;
        int coordinate2 = (int) (Math.random() * 11)*2;

        if (maze[coordinate1][coordinate2] == "#") {
            move(maze, coordinate1, coordinate2);
        }
    }

    // Случайным образом генерируем направление движения, проверяем, возможно ли туда двигаться (не будет ли выхода за границы матрицы). Если можно - метод продвигает кротика, обновляет координаты и вызывает сам себя.
    public static void move(String[][] maze, int coordinate1, int coordinate2) {
        int arbiter = (int) (Math.random() * 4);
        if (arbiter == 0 & coordinate1-2 > 0) {
            coordinate1 = coordinate1-2;
            maze[coordinate1][coordinate2] = " ";
            maze[coordinate1-1][coordinate2] = " ";
            maze[coordinate1-2][coordinate2] = " ";
            coordinate1 = coordinate1-2;
            move(maze, coordinate1, coordinate2);
        }
        if (arbiter == 1 & coordinate1+2 < 21) {
            maze[coordinate1][coordinate2] = " ";
            maze[coordinate1+1][coordinate2] = " ";
            maze[coordinate1+2][coordinate2] = " ";
            coordinate1 = coordinate1+2;
            move(maze, coordinate1, coordinate2);
        }
        if (arbiter == 2 & coordinate2-2 > 0) {
            maze[coordinate1][coordinate2] = " ";
            maze[coordinate1][coordinate2-1] = " ";
            maze[coordinate1][coordinate2-2] = " ";
            coordinate2 = coordinate2-2;
            move(maze, coordinate1, coordinate2);
        }
        if (arbiter == 3 & coordinate2+2 < 21) {
            maze[coordinate1][coordinate2] = " ";
            maze[coordinate1][coordinate2+1] = " ";
            maze[coordinate1][coordinate2+2] = " ";
            coordinate2 = coordinate2+2;
            move(maze, coordinate1, coordinate2);
        }
    }

    public static void waveAlgoritm(String[][] maze) {
        
        Integer count = 1;
        Point point1 = new Point();
        point1.x = 2;
        point1.y = 2;
        point1.count = 1;
        ArrayDeque<Point> coordinate = new ArrayDeque<Point>();
        coordinate.add(point1);

        maze[point1.x][point1.y] = "Start"; // Обозначаем начало лабиринта
        maze[maze.length-2][maze.length-2] = "End"; // Обозначаем конец лабиринта
        
        while (coordinate.peek() != null) {
            wavePlus(maze, count, coordinate);
            count++;
        }
    }

    public static void wavePlus(String[][] maze, Integer count, ArrayDeque<Point> coordinate) {
        Point point = coordinate.pop();
        if (maze[point.x+1][point.y] == " " & point.x+1 < maze.length-1) {
            maze[point.x+1][point.y] = point.count.toString();
            Point pointRight = new Point();
            pointRight.x = point.x+1;
            pointRight.y = point.y;
            pointRight.count = point.count+1;
            coordinate.add(pointRight);
        }
        if (maze[point.x][point.y-1] == " " & point.y-1 > 0) {
            maze[point.x][point.y-1] = point.count.toString();
            Point pointDown = new Point();
            pointDown.x = point.x;
            pointDown.y = point.y-1;
            pointDown.count = point.count+1;
            coordinate.add(pointDown);
        }
        if (maze[point.x-1][point.y] == " " & point.x-1 > 0) {
            maze[point.x-1][point.y] = point.count.toString();
            Point pointLeft = new Point();
            pointLeft.x = point.x-1;
            pointLeft.y = point.y;
            pointLeft.count = point.count+1;
            coordinate.add(pointLeft);
        }
        if (maze[point.x][point.y+1] == " " & point.y+1 < maze.length-1) {
            maze[point.x][point.y+1] = point.count.toString();
            Point pointUp = new Point();
            pointUp.x = point.x;
            pointUp.y = point.y+1;
            pointUp.count = point.count+1;
            coordinate.add(pointUp);
        }
    }
}