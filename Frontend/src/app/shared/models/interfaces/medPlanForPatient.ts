import { MedicationInterface } from './medication';

/** Medication Plan Interface */

export interface MedPlanForPatientInterface {

    medicationPlanId: number;
    name: string;
    startDate: string;
    endDate: string;
    medicationDTOs: Array<MedicationInterface>
}