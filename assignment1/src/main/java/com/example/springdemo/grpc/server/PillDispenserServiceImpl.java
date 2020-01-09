package com.example.springdemo.grpc.server;

import assign3.grpc.*;
import com.example.springdemo.entities.GrpcMedication;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.repositories.GrpcMedicationRepository;
import com.example.springdemo.repositories.MedicationPlanRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class PillDispenserServiceImpl extends PillDispenserServiceGrpc.PillDispenserServiceImplBase {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PillDispenserServiceImpl.class);

    private MedicationPlanRepository medicationPlanRepository;
    private GrpcMedicationRepository grpcMedicationRepository;

    public PillDispenserServiceImpl(MedicationPlanRepository medicationPlanRepository,
                                    GrpcMedicationRepository grpcMedicationRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.grpcMedicationRepository = grpcMedicationRepository;
    }

    @Override
    public void downloadMedPlan(DownloadReq request, StreamObserver<MedPlan> responseObserver) {
        LOGGER.info("Server received {}", request);

        List<MedicationPlan> medicationPlans =
                this.medicationPlanRepository.getAllByPatientId(request.getPatientId());

        List<Medication> allMedications = new ArrayList<>();

        System.out.println("Patient with id " + request.getPatientId() + " has " +
                medicationPlans.size() + " medication plans");

        for(MedicationPlan mp: medicationPlans)
        {
            System.out.println("Medication plan #" + mp.getMedicationPlanId() +
                    " has " + mp.getMedications().size() + " medications");
            for(Medication m: mp.getMedications())
            {
                allMedications.add(m);
                System.out.println("Medication " + m.getName() + " on med plan #" + mp.getMedicationPlanId());
            }
        }

        MedPlan.Builder medPlan = MedPlan.newBuilder();

        for(Medication m: allMedications)
        {
            assign3.grpc.Medication medication = assign3.grpc.Medication.newBuilder()
            .setMedicationId(m.getMedicationId()).setName(m.getName())
                    .setIntakeInterval(m.getIntakeInterval()).build();
            medPlan.addMedication(medication);
        }

        MedPlan builtMedPlan = medPlan.build();

        responseObserver.onNext(builtMedPlan);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMedicationStatus(TakenMedication request, StreamObserver<EmptyMessage> responseObserver) {
        LOGGER.info("Server received {}", request);

        GrpcMedication grpcMedication = new GrpcMedication(request.getMedicationId(),request.getPatientId(),
                request.getName(), request.getTaken());

        this.grpcMedicationRepository.save(grpcMedication);
        LOGGER.info("Server saved {}", grpcMedication);

        EmptyMessage emptyMessage = EmptyMessage.newBuilder().build();

        responseObserver.onNext(emptyMessage);
        responseObserver.onCompleted();

    }
}
