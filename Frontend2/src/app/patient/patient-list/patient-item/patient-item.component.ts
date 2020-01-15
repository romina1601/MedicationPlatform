import { Component, Input, Output, EventEmitter } from '@angular/core';
import { PatientModel } from 'src/app/shared/models/patient.model';
import { PatientViewInterface } from 'src/app/shared/models/interfaces/patientView';

@Component({
  selector: 'app-patient-item',
  templateUrl: './patient-item.component.html',
  styleUrls: ['./patient-item.component.scss']
})
export class PatientItemComponent {
  /** Index number 
   * from patient-list.component.html
  */
  @Input() index: number;

  /** Will hold the data of a single Patient 
   * from patient-list.component.html
  */
  @Input() patientData: PatientViewInterface;

  @Output()
  selectedPatientId: EventEmitter<number> = new EventEmitter();

  constructor(public patientModel: PatientModel) {}

  /**
   * Will notify the parent component (patient-list.component.ts)
   * when a patient as been selected, an pass the id value of the selected patient
   * 
   * It first goes to patient.module.ts and from there patient-list.component.ts gets this value
   * Use the exact same name for the variables (this is how they are linked)
   */
  onPatientSelect() {
    this.selectedPatientId.emit(this.patientData.patientId);
  }
}
