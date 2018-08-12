package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField heightField;
    @FXML
    private TextField lengthField;
    @FXML
    private Slider scaleField;
    @FXML
    private Canvas areaOfLab;
    @FXML
    private Group group;

    int[][] maze;
    int scale = 10;

    @FXML
    public void createButton(ActionEvent event) {
        int width = 10000;
        int height = 10000;
        clearCanvas(areaOfLab , width , height);

        int heightMaze = Integer.parseInt(heightField.getText());
        int lengthMaze = Integer.parseInt(lengthField.getText());
        scale = (int)(scaleField.getValue());

        if (heightMaze != 0 && lengthMaze != 0) {

            maze = LabCreate.mazeFulfill(LabCreate.mazeCreate(heightMaze, lengthMaze));

            width = maze[0].length * scale;
            height = maze.length * scale;
            areaOfLab = new Canvas(width , height);
            GraphicsContext gr = areaOfLab.getGraphicsContext2D();

            for(int i = 0 ; i < maze.length ; i++){
                for(int l = 0 ; l < maze[0].length ; l++){

                   if(maze[i][l] == LabCreate.wall && !(i == 1 && l == 0) && !(i == maze.length - 2 && l == maze[0].length - 1)){
                        gr.setFill(Color.BLACK);
                        gr.fillRect(l * scale , i * scale , scale , scale);
                    }

                }
            }

            group.getChildren().add(areaOfLab);

            System.out.println("DONE");

        } else {
            throw new IllegalArgumentException();
        }

    }

    @FXML
    private void solveButton(ActionEvent event){
        if(maze != null){
            ArrayList<int[]> result = LabSolve.solveMaze(maze);
            int[][] solvedMaze = LabSolve.resultMaze;

            GraphicsContext gr = areaOfLab.getGraphicsContext2D();

            for(int i = 0 ; i < solvedMaze.length ; i++){
                for(int l = 0 ; l < solvedMaze[0].length ; l++) {
                    gr.setFill(Color.BLUE);
                    if (solvedMaze[i][l] == LabSolve.way) {
                        gr.fillRect(l * scale, i * scale, scale, scale);
                    }
                }
            }

            for(int i = 0 ; i < result.size() ; i++){
                int[] oneStep = result.get(i);
                gr.setFill(Color.RED);
                gr.fillRect(oneStep[1] * scale , oneStep[0] * scale , scale , scale);
                gr.fillRect(0 , scale , scale , scale);
                gr.fillRect((maze[0].length - 1) * scale , (maze.length - 2) * scale , scale , scale);
            }
        }
    }

    @FXML
    private void saveButton(ActionEvent event){

        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.setSelectedExtensionFilter(extFilter);

        File file = chooser.showSaveDialog(areaOfLab.getScene().getWindow());

        try {
            WritableImage writableImage = new WritableImage((int)areaOfLab.getWidth(), (int)areaOfLab.getHeight());
            areaOfLab.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearCanvas(Canvas canvas , int width , int length){
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0 , 0 , width , length);
    }

}
