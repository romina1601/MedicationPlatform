import { MedPlanForPatientInterface } from './medPlanForPatient';

/** JustPatient Interface */

export interface JustPatientInterface {

    patientId: number;
    name: string;
    birthDate: string;
    gender: string;
    address: string;
    medicalRecord: string;
    username: string;
    medicationPlans: Array<MedPlanForPatientInterface>
}
