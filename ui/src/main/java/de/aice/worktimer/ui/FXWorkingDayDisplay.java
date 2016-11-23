package de.aice.worktimer.ui;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import de.aice.worktimer.Progress;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class FXWorkingDayDisplay implements WorkingDayDisplay {

    private final Stage parent;

    @FXML
    private Label label;

    @FXML
    private ProgressBar progressBar;

    public FXWorkingDayDisplay(Stage parent) {
        this.parent = parent;
    }

    @Override
    public void open() {
        this.parent.setTitle("WorkTimer FX");
        this.parent.setScene(new Scene(tryLoadFXPane()));
    }

    private String format(Duration duration) {
        return LocalTime.MIDNIGHT.plus(duration)
                                 .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private Pane tryLoadFXPane() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FXWorkingDayDisplay.fxml"));
        loader.setController(this);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showRemainingTime(Duration remainingTime) {
        String formattedTime = format(remainingTime);
        Platform.runLater(() -> this.label.setText(formattedTime));
    }

    @Override
    public void showProgress(Progress progress) {
        this.progressBar.setProgress(progress.onScaleTo(1));
    }
}
