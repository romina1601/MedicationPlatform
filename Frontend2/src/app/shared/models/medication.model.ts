import { Injectable } from '@angular/core';
import { MedicationInterface } from './interfaces/medication';

@Injectable()
export class MedicationModel {
  /** will grab the list of all Medications */
  all: Array<MedicationInterface>;

  /** will grab the id value of the selected Medication */
  selectedMedicationId: number;

  setSelected(medicationId: number) {
    return this.all.find((medication: MedicationInterface) => medication.medicationId === medicationId);
  }

  removeSelected() {
    this.selectedMedicationId = undefined;
  }

  clear() {
    this.removeSelected();
    this.all = undefined;
  }
}