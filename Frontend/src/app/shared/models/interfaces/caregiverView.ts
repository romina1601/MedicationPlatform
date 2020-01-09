import { JustPatientInterface } from './justPatient';

/** CaregiverView Interface */

export interface CaregiverViewInterface {

    caregiverId: number;
    name: string;
    birthDate: string;
    gender: string;
    address: string;
    username: string;
    patients: Array<JustPatientInterface>;
}
