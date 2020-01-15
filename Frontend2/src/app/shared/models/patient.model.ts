import { Injectable } from '@angular/core';
import { PatientViewInterface } from './interfaces/patientView';

@Injectable()
export class PatientModel {
  /** will grab the list of all Patients */
  all: Array<PatientViewInterface>;

  /** will grab the id value of the selected Patient */
  selectedPatientId: number;

  setSelected(patientId: number) {
    return this.all.find((patient: PatientViewInterface) => patient.patientId === patientId);
  }

  removeSelected() {
    this.selectedPatientId = undefined;
  }

  clear() {
    this.removeSelected();
    this.all = undefined;
  }
}