import { UsersInterface } from './users';

/** Doctor Interface */

export interface DoctorInterface {

    doctorId: number;
    name: string;
    specialization: string;
    user: UsersInterface;
}
