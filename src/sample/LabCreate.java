package sample;

import java.util.*;

public class LabCreate {

    public static void main(String[] args){

        /*int[][] first = mazeFulfill(mazeCreate(25 , 25));

        for(int i = 0 ; i < first.length ; i++){
            for(int k = 0 ; k < first[0].length ; k++){
                System.out.print(first[i][k]+ " ");
            }
            System.out.println();
        }*/

    }

    public static int cell = 0;
    public static int wall = 1;
    public static int step = 5;

    /**
     * create maze with walls and cells
     * @param height
     * @param length
     * @return
     */

    public static int[][] mazeCreate(int height , int length){
        if(height % 2 == 0){
            height++;
        }
        if(length % 2 == 0){
            length++;
        }

        int[][] res = new int[height][length];

        for(int i = 0 ; i < res.length ; i++){
            for(int k = 0 ; k < res[0].length ; k++){
                if(i % 2 == 0 || k % 2 == 0){
                    res[i][k] = wall;
                }else{
                    res[i][k] = cell;
                }
            }
        }

        return res;
    }

    /**
     * main method which create maze
     * @param maze
     * @return
     */

    public static int[][] mazeFulfill(int[][] maze){
        Random random = new Random();

        ArrayList<int[]> steps = new ArrayList<>();//write down all our steps
        int[] one;

        int height = 1;
        int length = 1;
        int num = 0;
        int numPr = -1;

        maze[height][length] = step; //Start point


        while (!(mazeIsFulfill(maze) )) {
            one = new int[2];

            if (!(mazeIsFulfill(maze)) && (noSteps(maze , height , length , maze.length , maze[0].length)
                    || ((height == maze.length - 2) && (length == maze[0].length - 2)))){
                if(!((height == maze.length - 2) && (length == maze[0].length - 2))) {
                    one[0] = height;
                    one[1] = length;
                    steps.add(one);
                }
                int[] newPoints = getNewPoint(maze , steps);
                height = newPoints[0];
                length = newPoints[1];
                continue;
            }

            one[0] = height;
            one[1] = length;

            //if because of Random our coordinates not change
            //we not write the same coordinates in array "steps"

            if(numPr != num) {
                steps.add(one);
            }

            numPr = num;

            int rnd = random.nextInt(4);
            if ((rnd == 0) && (height < (maze.length - 3)) && isFree(maze, height + 2, length)) {
                maze[height + 1][length] = step;
                maze[height + 2][length] = step;
                height = height + 2;
                num++;
                continue;
            } else if ((rnd == 1) && (height > 2) && isFree(maze, height - 2, length)) {
                maze[height - 1][length] = step;
                maze[height - 2][length] = step;
                height = height - 2;
                num++;
                continue;
            } else if ((rnd == 2) && (length < (maze[0].length - 3)) && isFree(maze, height, length + 2)) {
                maze[height][length + 1] = step;
                maze[height][length + 2] = step;
                length = length + 2;
                num++;
                continue;
            } else if ((rnd == 3) && (length > 2) && isFree(maze, height, length - 2)) {
                maze[height][length - 1] = step;
                maze[height][length - 2] = step;
                length = length - 2;
                num++;
                continue;
            }

        }


        array.removeAll(array);
        return maze;
    }

    /**
     * check if maze have free cells
     * @param maze
     * @return
     */

    private static boolean mazeIsFulfill(int[][] maze){
        for(int i = 1 ; i < maze.length - 1 ; i++){
            for(int k = 1 ; k < maze[0].length - 1 ; k++){
                if(maze[i][k] == cell){
                    return false;
                }
            }
        }

        return true;
    }

    private static ArrayList<int[]> array = new ArrayList<>();

    /**
     * return new free point
     * @param maze
     * @param list
     * @return
     */

    private static int[] getNewPoint(int[][] maze ,ArrayList<int[]> list){
        int[] res;

        if(array.size() == 0) {
            array = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                int[] step = list.get(i);
                if (!noSteps(maze, step[0], step[1], maze.length, maze[0].length)) {
                    array.add(list.get(i));
                }
            }

        }

        Random random = new Random();

        System.out.println(array.size());
        int num = 0;
        try {
            num = random.nextInt(array.size());
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        res = array.get(num);
        array.remove(num);

        return res;
    }

    /**
     * check if near "cell" is not "stepped" by now
     * @param maze
     * @param height
     * @param length
     * @return
     */
    private static boolean isFree(int maze[][] , int height , int length){

        //But if it is not free it must be "step" now
        //So, we can just check new step is "step

        if(maze[height][length] == step){
            return false;
        }

        return true;
    }


    /**
     * check if from present cell we can't go nowhere
     * @param maze
     * @param presHeight
     * @param presLength
     * @param mazeHeight
     * @param mazeLength
     * @return
     */
    private static boolean noSteps(int[][] maze , int presHeight , int presLength , int mazeHeight , int mazeLength){
        if(presHeight + 2 < mazeHeight){
            if(isFree(maze , presHeight + 2 , presLength)){
                return false;
            }
        }
        if(presHeight - 2 > 0){
            if(isFree(maze , presHeight - 2 , presLength)){
                return false;
            }
        }
        if(presLength + 2 < mazeLength){
            if(isFree(maze , presHeight , presLength + 2)){
                return false;
            }
        }
        if(presLength - 2 > 0){
            if(isFree(maze , presHeight , presLength - 2)){
                return false;
            }
        }

        return true;
    }
}

