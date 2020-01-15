import { JustPatientInterface } from './justPatient';
import { MedicationInterface } from './medication';

/** Medication Plan Interface */

export interface MedPlanInterface {

    medPlanId: number;
    name: string;
    startDate: string;
    endDatae: string;
    patient: JustPatientInterface;
    medicationList: Array<MedicationInterface>;
}