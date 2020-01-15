import { Injectable } from '@angular/core';
import { DoctorInterface } from './interfaces/doctor';
import { DoctorViewInterface } from './interfaces/doctorView';

@Injectable()
export class DoctorModel {
  /** will grab the list of all Doctors */
  all: Array<DoctorViewInterface>;

  /** will grab the id of the selected Doctor */
  selectedDoctorId: number;

  setSelected(doctorId: number) {
    return this.all.find((doctor: DoctorViewInterface) => doctor.doctorId === doctorId);
  }

  removeSelected() {
    this.selectedDoctorId = undefined;
  }

  clear() {
    this.removeSelected();
    this.all = undefined;
  }
}
