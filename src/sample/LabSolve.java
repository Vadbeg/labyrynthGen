package sample;

import java.util.ArrayList;
import java.util.Random;

public class LabSolve {

    public static void main(String[] args) {
        int maze[][] = LabCreate.mazeFulfill(LabCreate.mazeCreate(25 , 25));

        ArrayList<int[]> result = solveMaze(maze);

        int num = 0;
        for(int[] step : result){
            System.out.println(num);
            System.out.println("Height: " + step[0]);
            System.out.println("Length: " + step[1]);
            num++;
        }
    }

    public static final int way = 7;

    public static int[][] resultMaze;

    public static ArrayList<int[]> solveMaze(int[][] maze){
        ArrayList<int[]> resultWay = new ArrayList<>();

        int[] oneStep = new int[2];
        int height = 1;
        int length = 1;
        oneStep[0] = height;
        oneStep[1] = length;
        maze[height][length] = way;
        resultWay.add(oneStep);

        while(maze[maze.length - 2][maze[0].length - 2] != way){
            if(isImpasse(maze , height , length)){
                int[] nextStep = branches.get(branches.size() - 1);
                branches.remove(branches.size() - 1);
                removeFrom(resultWay , nextStep);
                height = nextStep[0];
                length = nextStep[1];
                System.out.println("Height: " + height);
                System.out.println("Length: " + length);
                maze[height][length] = way;
                resultWay.add(nextStep);
            }

            oneStep = getCoordinates(maze , height , length);
            height = oneStep[0];
            length = oneStep[1];
            System.out.println("Height: " + height);
            System.out.println("Length: " + length);

            maze[height][length] = way ;
            resultWay.add(oneStep);

            resultMaze = maze;
        }

        branches.removeAll(branches);
        resultMaze = maze;
        return resultWay;
    }

    private static ArrayList<int[]> branches = new ArrayList<>();
    private static int[] previousBranch;

    private static int[] getCoordinates(int[][] maze , int height , int length){
        ArrayList<int[]> ways = new ArrayList<>();
        int[] res;

        if((height + 1 < maze.length) && (maze[height + 1][length] == LabCreate.step)){
            ways.add(new int[]{height + 1 , length});
        }
        if((height - 1 > 0) && (maze[height - 1][length] == LabCreate.step)){
            ways.add(new int[]{height - 1 , length});
        }
        if((length + 1 < maze[0].length) && (maze[height][length + 1] == LabCreate.step)){
            ways.add(new int[]{height , length + 1});
        }
        if((length - 1 > 0) && (maze[height][length - 1] == LabCreate.step)){
            ways.add(new int[]{height , length - 1});
        }

        Random rnd = new Random();
        System.out.println(ways.size());
        res = ways.get(rnd.nextInt(ways.size()));

        if(ways.size() > 1){
            previousBranch = new int[]{height , length};
            branches.add(previousBranch);
        }

        return res;
    }

    private static boolean isImpasse(int[][] maze , int height , int length){
        if((height + 1 < maze.length) && (maze[height + 1][length] == LabCreate.step)){
            return false;
        }else if((height - 1 > 0) && (maze[height - 1][length] == LabCreate.step)){
            return false;
        }else if((length + 1 < maze[0].length) && (maze[height][length + 1] == LabCreate.step)){
            return false;
        }else if((length - 1 > 0) && (maze[height][length - 1] == LabCreate.step)){
            return false;
        }

        return true;
    }

    private static ArrayList<int[]> removeFrom(ArrayList<int[]> resultWay , int[] from){
        for(int i = 0; i < resultWay.size(); i++){
            int[] now = resultWay.get(i);
            if((now[0] == from[0]) && (now[1] == from[1])){
                for(int l = i ; i < resultWay.size() ; ){
                    resultWay.remove(i);
                }
                return resultWay;
            }
        }

        return resultWay;
    }
}