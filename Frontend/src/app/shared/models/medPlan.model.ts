import { Injectable } from '@angular/core';
import { MedPlanInterface } from './interfaces/medPlan';

@Injectable()
export class MedPlanModel {
  /** will grab the list of all Medications */
  all: Array<MedPlanInterface>;

  /** will grab the id value of the selected Medication */
  selectedMedPlanId: number;

  setSelected(medPlanId: number) {
    return this.all.find((medPlan: MedPlanInterface) => medPlan.medPlanId === medPlanId);
  }

  removeSelected() {
    this.selectedMedPlanId = undefined;
  }

  clear() {
    this.removeSelected();
    this.all = undefined;
  }
}