package com.example.lr1_kg;

import com.example.lr1_kg.models.Coordinates;
import com.example.lr1_kg.models.Lines;
import com.example.lr1_kg.models.Matrix;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {


    @FXML
    private Canvas canvas;

    private List<Lines> linesList = new ArrayList<>();
    private GraphicsContext gc;
    private Matrix matrix;

    private Timeline timeline;


    public Controller() throws IOException {
        addLinesFromFile();
        matrix = new Matrix();
    }

    //получение координат из файла
    private void addLinesFromFile() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\111\\IdeaProjects\\Lab_1\\lr1_kg\\src\\main\\java\\com\\example\\lr1_kg\\coordinates.txt");
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] point = line.split(" ");
            String[] coordinateStart = point[0].split(",");
            String[] coordinateEnd = point[1].split(",");
            Coordinates start = new Coordinates(Double.parseDouble(coordinateStart[0]), Double.parseDouble(coordinateStart[1])
                    , Double.parseDouble(coordinateStart[2]));
            Coordinates end = new Coordinates(Double.parseDouble(coordinateEnd[0]), Double.parseDouble(coordinateEnd[1]),
                    Double.parseDouble(coordinateEnd[2]));
            Lines lines = new Lines(start, end);
            linesList.add(lines);

        }

        fr.close();
    }


    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        drawAxexXYZ();
        drawShapes();
    }


    public void clearCanvas() {

        gc.clearRect(-canvas.getWidth() / 2,
                -canvas.getHeight() / 2,
                canvas.getWidth(),
                canvas.getHeight());
    }


    private void drawCanvas() {
        clearCanvas();
        drawAxexXYZ();
        drawShapes();
    }


    @FXML
    protected void btnTransformXpoz(ActionEvent actionEvent) throws URISyntaxException {

        for (Lines line : linesList) {

            line.getStart().multiply(matrix.transform(10, 0, 0));
            line.getEnd().multiply(matrix.transform(10, 0, 0));
        }

        drawCanvas();
    }


    @FXML
    protected void btnTransformXneg(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.transform(-10, 0, 0));
            line.getEnd().multiply(matrix.transform(-10, 0, 0));
        }

        drawCanvas();
    }

    @FXML
    protected void btnTransformYpoz(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.transform(0, 10, 0));
            line.getEnd().multiply(matrix.transform(0, 10, 0));
        }

        drawCanvas();
    }

    @FXML
    protected void btnTransformYneg(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.transform(0, -10, 0));
            line.getEnd().multiply(matrix.transform(0, -10, 0));
        }

        drawCanvas();
    }

    @FXML
    protected void btnTransformZpoz(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.transform(0, 0, 10));
            line.getEnd().multiply(matrix.transform(0, 0, 10));
        }

        drawCanvas();
    }

    @FXML
    protected void btnTransformZneg(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.transform(0, 0, -10));
            line.getEnd().multiply(matrix.transform(0, 0, -10));
        }

        drawCanvas();
    }

    @FXML
    protected void btnDilationXincrease(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.dilation(1.5, 1, 1));
            line.getEnd().multiply(matrix.dilation(1.5, 1, 1));
        }

        drawCanvas();
    }

    @FXML
    protected void btnDilationXdecrease(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.dilation(0.6, 1, 1));
            line.getEnd().multiply(matrix.dilation(0.6, 1, 1));
        }

        drawCanvas();
    }

    @FXML
    protected void btnDilationYincrease(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.dilation(1, 1.5, 1));
            line.getEnd().multiply(matrix.dilation(1, 1.5, 1));
        }

        drawCanvas();
    }

    @FXML
    protected void btnDilationYdecrease(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.dilation(1, 0.6, 1));
            line.getEnd().multiply(matrix.dilation(1, 0.6, 1));
        }

        drawCanvas();
    }

    @FXML
    protected void btnDilationZincrease(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.dilation(1, 1, 1.5));
            line.getEnd().multiply(matrix.dilation(1, 1, 1.5));
        }

        drawCanvas();
    }

    @FXML
    protected void btnDilationZdecrease(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.dilation(1, 1, 0.6));
            line.getEnd().multiply(matrix.dilation(1, 1, 0.6));
        }

        drawCanvas();
    }

    public double speed = 0;
    public double acceleration = 0.01;

    @FXML
    protected void animationScaling() {

        if (Math.abs(speed) > 0.2) {
            acceleration = -acceleration;
        }

        speed += acceleration;

        double dx = 1 - (0.5 * Math.abs(speed));
        double dy = 1 - (0.5 * Math.abs(speed));
        double dz = 1 - (0.5 * Math.abs(speed));

        if (speed < 0) {
            dx = 1 / dx;
            dy = 1 / dy;
            dz = 1 / dz;
        }

        for (Lines line : linesList) {
            line.getStart().multiply(matrix.dilation(dx, dy, dz));
            line.getEnd().multiply(matrix.dilation(dx, dy, dz));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationXpoz(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.rotationX(Math.toRadians(10)));
            line.getEnd().multiply(matrix.rotationX(Math.toRadians(10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationXneg(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.rotationX(Math.toRadians(-10)));
            line.getEnd().multiply(matrix.rotationX(Math.toRadians(-10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationYnpoz(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.rotationY(Math.toRadians(10)));
            line.getEnd().multiply(matrix.rotationY(Math.toRadians(10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationYneg(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.rotationY(Math.toRadians(-10)));
            line.getEnd().multiply(matrix.rotationY(Math.toRadians(-10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationZpoz(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.rotationZ(Math.toRadians(10)));
            line.getEnd().multiply(matrix.rotationZ(Math.toRadians(10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnRotationZneg(ActionEvent actionEvent) throws URISyntaxException {
        for (Lines line : linesList) {

            line.getStart().multiply(matrix.rotationZ(Math.toRadians(-10)));
            line.getEnd().multiply(matrix.rotationZ(Math.toRadians(-10)));
        }

        drawCanvas();
    }

    @FXML
    protected void btnStartAnimation(ActionEvent actionEvent) throws URISyntaxException {
        animationStart();
    }

    @FXML
    protected void btnStopAnimation(ActionEvent actionEvent) throws URISyntaxException {
        timeline.stop();
    }

    private void drawShapes() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        for (Lines line : linesList) {
            gc.strokeLine(line.getStart().getX() - line.getStart().getZ() * 0.5 * Math.cos(Math.PI / 4),
                    -line.getStart().getY() + line.getStart().getZ() * 0.5 * Math.sin(Math.PI / 4),
                    line.getEnd().getX() - line.getEnd().getZ() * 0.5 * Math.cos(Math.PI / 4),
                    -line.getEnd().getY() + line.getEnd().getZ() * 0.5 * Math.sin(Math.PI / 4));
        }
    }

    private void drawAxexXYZ() {
        gc.setStroke(Color.BLUEVIOLET);
        gc.setLineWidth(1);

        gc.strokeLine(0, 0, 350, 0);//x
        gc.strokeLine(0, 0, 0, -350);//y
        gc.strokeLine(0, 0, -350, 350);//z

    }

    private void animationStart() {

        timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                animationScaling();

            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    Button bt = new Button();

    @FXML
    public void tmp() {
        bt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode key = event.getCode();
                writeKeyCode(key);
            }

        });
    }

    public void writeKeyCode(KeyCode key) {

        if (key == KeyCode.W) {
//            tnmXup();
        }
    }

}