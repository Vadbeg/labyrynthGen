package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class LabSolve {

    public static void main(String[] args) {

    }

    public static final int way = 7;

    public static int[][] resultMaze;
    private static ArrayList<int[]> resultWay;


    /**
     * method which solve maze
     * @param maze
     * @return
     */

    public static ArrayList<int[]> solveMaze(int[][] maze){
        resultWay = new ArrayList<>();

        int[] oneStep = new int[2];
        int height = 1;
        int length = 1;
        oneStep[0] = height;
        oneStep[1] = length;
        maze[height][length] = way;
        resultWay.add(oneStep);

        while(maze[maze.length - 2][maze[0].length - 2] != way){
            if(isImpasse(maze , height , length)){
                int[] nextStep = branches.getLast();
                branches.removeLast();
                removeFrom(resultWay , nextStep);
                height = nextStep[0];
                length = nextStep[1];
                maze[height][length] = way;
                resultWay.add(nextStep);
            }

            oneStep = getCoordinates(maze , height , length);
            height = oneStep[0];
            length = oneStep[1];

            maze[height][length] = way ;
            resultWay.add(oneStep);
        }

        branches.clear();
        resultMaze = maze;
        return resultWay;
    }

    private static LinkedList<int[]> branches = new LinkedList<>();

    /**
     * this method return next coordinates
     * @param maze
     * @param height
     * @param length
     * @return
     */

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
        res = ways.get(rnd.nextInt(ways.size()));

        if(ways.size() > 1){
            branches.add(new int[]{height , length});
        }

        return res;
    }

    /**
     * return true if this step is impasse
     * @param maze
     * @param height
     * @param length
     * @return
     */

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

    /**
     * remove part of ArrayList from exact number
     * @param resultWay
     * @param from
     * @return
     */

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