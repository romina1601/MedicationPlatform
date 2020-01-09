import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MedicationModel } from 'src/app/shared/models/medication.model';
import { MedicationInterface } from 'src/app/shared/models/interfaces/medication';

@Component({
  selector: 'app-medication-item',
  templateUrl: './medication-item.component.html',
  styleUrls: ['./medication-item.component.scss']
})
export class MedicationItemComponent {
  /** Index number */
  @Input() index: number;

  /** Will hold the data of a single Medication */
  @Input() medicationData: MedicationInterface;

  @Output()
  selectedMedicationId: EventEmitter<number> = new EventEmitter();

  constructor(public medicationModel: MedicationModel) {}

  /**
   * Will notify the parent component (medication-list.component.ts)
   * when a medication has been selected, an pass the id value of the selected medication
   */
  onMedicationSelect() {
    this.selectedMedicationId.emit(this.medicationData.medicationId);
  }
}
