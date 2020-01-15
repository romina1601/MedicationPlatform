import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CaregiverModel } from 'src/app/shared/models/caregiver.model';
import { CaregiverViewInterface } from 'src/app/shared/models/interfaces/caregiverView';

@Component({
  selector: 'app-caregiver-item',
  templateUrl: './caregiver-item.component.html',
  styleUrls: ['./caregiver-item.component.scss']
})
export class CaregiverItemComponent {
  /** Index number */
  @Input() index: number;

  /** Will hold the data of a single Caregiver */
  @Input() caregiverData: CaregiverViewInterface;

  @Output()
  selectedCaregiverId: EventEmitter<number> = new EventEmitter();

  constructor(public caregiverModel: CaregiverModel) {}

  /**
   * Will notify the parent component (caregiver-list.component.ts)
   * when a caregiver as been selected, and pass the id value of the selected caregiver
   * 
   * It first goes to caregiver.module.ts and from there caregiver-list.component.ts gets this value
   * Use the exact same name for the variables (this is how they are linked)
   */
  onCaregiverSelect() {
    this.selectedCaregiverId.emit(this.caregiverData.caregiverId);
  }
}
