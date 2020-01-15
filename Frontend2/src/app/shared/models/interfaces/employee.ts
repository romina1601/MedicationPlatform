import { ActivityInterface } from './activity';

/** Employee interface */

export interface EmployeeInterface
{
    activityList: Array<ActivityInterface>;
    
    username: string;

    password: string;

    type: string;

    name: string;

    telephone: string;

    address: string;

    hiring_date: string;
}