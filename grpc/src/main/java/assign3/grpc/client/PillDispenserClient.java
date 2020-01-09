package assign3.grpc.client;

import assign3.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PillDispenserClient {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PillDispenserClient.class);

        private ManagedChannel managedChannel =
                ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();

    private PillDispenserServiceGrpc.PillDispenserServiceBlockingStub pillDispenserServiceBlockingStub =
            PillDispenserServiceGrpc.newBlockingStub(managedChannel);


    public MedPlan downloadMedPlan(Integer patientId)
    {
        DownloadReq downloadReq = DownloadReq.newBuilder().setPatientId(patientId).build();
        LOGGER.info("Client sending {}", downloadReq);


        MedPlan medPlan = pillDispenserServiceBlockingStub.downloadMedPlan(downloadReq);
        LOGGER.info("Client received {}", medPlan);

        return medPlan;
    }

    public void sendMedicationStatus(Integer patientId, Medication medication, boolean taken)
    {
        String status;
        if(taken)
        {
            status = "TAKEN";
        }
        else
        {
            status = "NOT TAKEN";
        }

        TakenMedication takenMedication = TakenMedication.newBuilder()
                .setPatientId(patientId)
                .setMedicationId(medication.getMedicationId())
                .setName(medication.getName())
                .setTaken(status).build();

        LOGGER.info("Client sending {}", takenMedication);

//        pillDispenserServiceBlockingStub =
//                PillDispenserServiceGrpc.newBlockingStub(managedChannel);

        EmptyMessage emptyMessage = pillDispenserServiceBlockingStub.sendMedicationStatus(takenMedication);
        LOGGER.info("Client received {}", emptyMessage);
    }

}
