import { MedPlanForPatientInterface } from './medPlanForPatient';
import { CaregiverForPatientInterface } from './caregiverForPatient';

/** PatientView Interface */

export interface PatientViewInterface {

    patientId: number;
    name: string;
    birthDate: string;
    gender: string;
    address: string;
    medicalRecord: string;
    username: string;
    caregiverDTO: CaregiverForPatientInterface;
    medicationPlans: Array<MedPlanForPatientInterface>
}
