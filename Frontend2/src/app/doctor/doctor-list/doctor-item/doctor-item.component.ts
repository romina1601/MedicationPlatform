import { Component, Input, Output, EventEmitter } from '@angular/core';
import { DoctorModel } from 'src/app/shared/models/doctor.model';
import { DoctorInterface } from 'src/app/shared/models/interfaces/doctor';

@Component({
  selector: 'app-doctor-item',
  templateUrl: './doctor-item.component.html',
  styleUrls: ['./doctor-item.component.scss']
})
export class DoctorItemComponent {
  /** Index number */
  @Input() index: number;

  /** Will hold the data of a single Doctor */
  @Input() doctorData: DoctorInterface;

  @Output()
  selectedDoctorId: EventEmitter<number> = new EventEmitter();

  constructor(public doctorModel: DoctorModel) {}

  /**
   * Will notify the parent component (doctor-list.component.ts)
   * when a doctor as been selected, an pass the ssn value of the selected doctor
   * 
   * It first goes to doctor.module.ts and from there doctor-list.component.ts gets this value
   * Use the exact same name for the variables (this is how they are linked)
   */
  onDoctorSelect() {
    this.selectedDoctorId.emit(this.doctorData.doctorId);
  }
}
