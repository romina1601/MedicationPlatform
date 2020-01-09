import assign3.grpc.Medication;
import assign3.grpc.client.PillDispenserClient;
import gui.MainGUI;
import javafx.application.Application;

public class MainApp {

    public static void main(String[] args) {

        MainGUI.main(args);

//        PillDispenserClient pillDispenserClient = new PillDispenserClient();
//
//        pillDispenserClient.downloadMedPlan(6);
//
//        Medication medication = Medication.newBuilder()
//                .setMedicationId(7)
//                .setName("Nurofen")
//                .setIntakeInterval("10:00-14:00").build();
//
//        pillDispenserClient.sendMedicationStatus(6, medication, false);
    }
}
