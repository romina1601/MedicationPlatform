package com.grpc.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MainApp.class, args);
        MainGUI mainGUI = context.getBean(MainGUI.class);
        try {
            mainGUI.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
