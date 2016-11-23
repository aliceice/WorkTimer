package de.aice.worktimer.ui;

import java.time.Duration;

import de.aice.worktimer.WorkingDay;
import javafx.application.Application;
import javafx.stage.Stage;

public final class WorkTimerApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WorkingDayPresenter presenter = new WorkingDayPresenter(WorkingDay.with(Duration.ofHours(8)),
                                                                new FXWorkingDayDisplay(primaryStage));
        presenter.start();
        primaryStage.show();
    }
}
