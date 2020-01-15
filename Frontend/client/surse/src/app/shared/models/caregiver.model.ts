import { Injectable } from '@angular/core';
import { CaregiverViewInterface } from './interfaces/caregiverView';

@Injectable()
export class CaregiverModel {
  /** will grab the list of all Caregivers */
  all: Array<CaregiverViewInterface>;

  /** will grab the id value of the selected Caregiver */
  selectedCaregiverId: number;

  setSelected(caregiverId: number) {
    return this.all.find((caregiver: CaregiverViewInterface) => caregiver.caregiverId === caregiverId);
  }

  removeSelected() {
    this.selectedCaregiverId = undefined;
  }

  clear() {
    this.removeSelected();
    this.all = undefined;
  }
}