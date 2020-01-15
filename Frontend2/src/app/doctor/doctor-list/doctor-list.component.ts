import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { DoctorModel } from 'src/app/shared/models/doctor.model';
import { ApiService } from 'src/app/shared/services/api.service';
import { DoctorInterface } from 'src/app/shared/models/interfaces/doctor';
import { DoctorViewInterface } from 'src/app/shared/models/interfaces/doctorView';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.scss']
})
export class DoctorListComponent implements OnInit {
  /** Flag for letting know the user that something is loading/happening */
  isProcessing: boolean;

  //flag to see if add doctor button is selected
  addDoctor: boolean = false;


  constructor(public doctorModel: DoctorModel, private apiService: ApiService) {}

  ngOnInit() {
    this.getAllDoctors();
  }

  getAllDoctors(reloadDoctors: boolean = false) {
    if (!reloadDoctors) {
      /** Initialize the loading process */
      this.isProcessing = true;
      /** Clear the Docttor Model values (if it was instantiated before) */
      this.doctorModel.clear();
    }
    this.apiService.getDoctors().subscribe(
      /** On Success: save the list of doctors in the doctor model */
      (data: Array<DoctorViewInterface>) => (this.doctorModel.all = data),
      /** On Error: log the error and end the loading process */
      (error: HttpErrorResponse) => {
        console.error(error);
        this.isProcessing = false;
      },
      /** End the loading process no matter what
       * at the end of all operations */
      () => (this.isProcessing = false)
    );
  }

  onAddDoctor()
  {
    this.addDoctor = true;
    //console.log("button pressed");
   // this.addNewDoctor.emit(this.addDoctor);
  }

  onDoctorIdSelected(doctorId: number) {
    this.doctorModel.selectedDoctorId = doctorId;
    this.addDoctor = false;
  }
}
