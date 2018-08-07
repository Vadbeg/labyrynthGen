package sample;

import java.util.ArrayList;

public class LabSolve {

    public static void main(String[] args){



    }

    public static ArrayList<int[]> solveMaze(int[][] maze){
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> steps = new ArrayList<>();

        int height = 1;
        int length = 1;

        int[] step = new int[2];
        step[0] = height;
        step[1] = length;

        while((result.get(result.size() - 1)[0] != maze.length - 1)
                && (result.get(result.size() - 1)[1] != maze[0].length - 1) ){
            result.add(step);
            steps.add(step);

            for(int i = 0 ; i < 4 ; i++){

            }

        }


        return null;

    }

}
