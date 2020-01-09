package config.services;

import entities.GrpcMedication;
import org.dsassignment4.soap.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.GrpcMedicationRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrpcMedicationService {

    private final GrpcMedicationRepository grpcMedicationRepository;

    @Autowired
    public GrpcMedicationService(GrpcMedicationRepository grpcMedicationRepository) {
        this.grpcMedicationRepository = grpcMedicationRepository;
    }


    public List<Medication> getMedicationHistoryResponse(Integer patientId)
    {
        List<GrpcMedication> allMedications;
        List<Medication> patientMedication = new ArrayList<>();

        System.out.println("All good until here");

        allMedications = this.grpcMedicationRepository.findAll();

        GrpcMedication medicationn = this.grpcMedicationRepository.findById(1).orElse(null);
        System.out.println(medicationn);

        for(GrpcMedication m: allMedications)
        {
            if(m.getPatientId() == patientId)
            {
                Medication medication = new Medication();
                medication.setMedicationId(BigInteger.valueOf(m.getGrpcMedicationId()));
                medication.setPatientId(BigInteger.valueOf(m.getPatientId()));
                medication.setName(m.getName());
                medication.setStatus(m.getStatus());
                patientMedication.add(medication);
            }
        }
        return patientMedication;
    }
}
