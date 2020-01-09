package gui;

import assign3.grpc.MedPlan;
import assign3.grpc.Medication;
import assign3.grpc.client.PillDispenserClient;
import entities.MedicationEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainGUI extends Application {

    Stage mainWindow;
    private static Integer patientId = 6;
    public static final String DOWNLOAD_TIME = "09:42:20";
    public static PillDispenserClient pillDispenserClient = new PillDispenserClient();
    public List<MedicationEntity> medicationsStatus = new ArrayList<>();
    public List<Medication> allMedications = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainWindow = primaryStage;
        mainWindow.setTitle("Pill Dispenser App");

        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        // VBox for the center of border pane
        VBox centerVBox = new VBox(10);
        centerVBox.setPadding(new Insets(10,10,10,10));


        /****** TABLE FOR MEDICATION ********/
        TableView<Medication> table;
        // create the columns
        // ID column
        TableColumn<Medication, Integer> medicationIdColumn = new TableColumn<>("Medication ID");
        medicationIdColumn.setMinWidth(200);
        medicationIdColumn.setStyle("-fx-alignment: CENTER;");
        medicationIdColumn.setCellValueFactory(new PropertyValueFactory<>("medicationId"));

        // Name column
        TableColumn<Medication, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Intake interval column
        TableColumn<Medication, String> intakeIntervalColumn = new TableColumn<>("Intake Interval");
        intakeIntervalColumn.setMinWidth(200);
        intakeIntervalColumn.setStyle("-fx-alignment: CENTER;");
        intakeIntervalColumn.setCellValueFactory(new PropertyValueFactory<>("intakeInterval"));


        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(medicationIdColumn, nameColumn, intakeIntervalColumn);
        addBtnToTable(table);
        centerVBox.getChildren().add(table);
        /****** END: TABLE FOR MEDICATION ********/

        // HBOX - patient ID and clock
        HBox topHBox = new HBox(10);
        topHBox.setId("topHBox");
        topHBox.setPadding(new Insets(10,10,10,10));
        // Patient ID label
        Label patientIdLabel = new Label();
        patientIdLabel.setText("YOUR PATIENT ID IS: #" + patientId.toString());
        patientIdLabel.setAlignment(Pos.CENTER_LEFT);
        patientIdLabel.getStyleClass().add("hboxLabel");
        // Timer label
        Label timerLabel = new Label();
        timerLabel.setAlignment(Pos.CENTER_RIGHT);
        timerLabel.getStyleClass().add("hboxLabel");
        initClock(timerLabel, table);
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        topHBox.getChildren().addAll(patientIdLabel, region1, timerLabel);


        //create the entire scene
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topHBox);
        borderPane.setCenter(centerVBox);


        Scene scene = new Scene(borderPane, 900, 600);

        scene.getStylesheets().add(getClass().getResource("/guiCSS.css").toExternalForm());
        mainWindow.setScene(scene);
        mainWindow.show();

    }

    public void downloadMedicationPlan()
    {
        MedPlan medPlan = pillDispenserClient.downloadMedPlan(patientId);

        for(Medication m : medPlan.getMedicationList())
        {
            allMedications.add(m);
            medicationsStatus.add(new MedicationEntity(
                    m.getMedicationId(),
                    m.getName(),
                    m.getIntakeInterval(),
                    null));

            System.out.println("Asta e un medicament: " + m.getName());
        }
    }


    public void initClock(Label label, TableView<Medication> table) {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            label.setText("Current time: " + currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" + currentTime.getSecond());

            if(isDownloadTime(currentTime))
            {
                downloadMedicationPlan();
                System.out.println("Downloading med plan...");
            }
            // if it's past download time, update the list of medication periodically
            if(currentTime.truncatedTo(ChronoUnit.SECONDS).isAfter(LocalTime.parse(DOWNLOAD_TIME)))
            {
                table.setItems(updateMedications(currentTime));
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public boolean isDownloadTime(LocalTime currentTime)
    {
        if(currentTime.truncatedTo(ChronoUnit.SECONDS).equals(LocalTime.parse(DOWNLOAD_TIME)))
            return true;
        else return  false;
    }

    public boolean shouldTake(LocalTime currentTime, Medication medication)
    {
        String startTime = medication.getIntakeInterval().split("-")[0];
        String endTime = medication.getIntakeInterval().split("-")[1];

        if(currentTime.truncatedTo(ChronoUnit.MINUTES).equals(LocalTime.parse(startTime))
            || (currentTime.truncatedTo(ChronoUnit.MINUTES).isAfter(LocalTime.parse(startTime))
                && currentTime.truncatedTo(ChronoUnit.MINUTES).isBefore(LocalTime.parse(endTime))))
            return true;
        else return false;
    }

    public boolean isOverdue(LocalTime currentTime, Medication medication)
    {
        String endTime = medication.getIntakeInterval().split("-")[1];
        endTime += ":00";
        if(currentTime.truncatedTo(ChronoUnit.SECONDS).isAfter(LocalTime.parse(endTime)))
            return true;
        else return false;
    }

    public ObservableList<Medication> updateMedications(LocalTime currentTime)
    {
        ObservableList<Medication> medications = FXCollections.observableArrayList();

        for(Medication m: allMedications)
        {
            MedicationEntity medicationEntity = medicationsStatus.stream()
                    .filter(a -> a.getMedicationId() == m.getMedicationId())
                    .collect(Collectors.toList()).get(0);
            if (medicationEntity.isTaken() == null)
            {
                if (shouldTake(currentTime, m))
                {
                    System.out.println(medicationsStatus);
                    medications.add(m);
                }
                else if (isOverdue(currentTime, m))
                {
                    medicationEntity.setTaken(false);
                    pillDispenserClient.sendMedicationStatus(patientId, m, false);
                    System.out.println();
                }
            }
        }
        return medications;
    }





    public void addBtnToTable(TableView<Medication> table)
    {
        TableColumn<Medication, Void> takenColumn = new TableColumn<>("Take Medication");

        Callback<TableColumn<Medication, Void>,TableCell<Medication, Void>> cellFactory =
                new Callback<TableColumn<Medication, Void>,TableCell<Medication, Void>>()
                {
                    @Override
                    public TableCell<Medication, Void> call(final TableColumn<Medication, Void> param)
                    {
                        final TableCell<Medication, Void> cell = new TableCell<Medication, Void>()
                        {
                            private final Button takenBtn = new Button("TAKE");
                            {
                                takenBtn.setAlignment(Pos.CENTER);
                                takenBtn.setOnAction( e ->
                                {
                                    Medication medication = getTableView().getItems().get(getIndex());
                                    //table.getItems().remove(medication);
                                    MedicationEntity medicationEntity = medicationsStatus.stream()
                                            .filter(a -> a.getMedicationId() == medication.getMedicationId())
                                            .collect(Collectors.toList()).get(0);
                                    medicationEntity.setTaken(true);
                                    pillDispenserClient.sendMedicationStatus(patientId, medication, true);
                                    System.out.println("You took medication: " + medication.getName());
                                });
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(takenBtn);
                                }
                            }
                        };
                        cell.setStyle("-fx-alignment: CENTER;");
                        return cell;
                    }
                };
        takenColumn.setCellFactory(cellFactory);
        takenColumn.setMinWidth(200);
        table.getColumns().add(takenColumn);
    }


}
