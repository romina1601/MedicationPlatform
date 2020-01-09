import { Component, Input, Output, OnChanges, SimpleChange, EventEmitter } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { DoctorInterface } from  'src/app/shared/models/interfaces/doctor';
import { DoctorViewInterface } from 'src/app/shared/models/interfaces/doctorView';
import { DoctorModel } from 'src/app/shared/models/doctor.model';
import { ApiService } from 'src/app/shared/services/api.service';

import {FormControl, Validators} from '@angular/forms'


@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.scss']
})
export class DoctorDetailsComponent implements OnChanges {
  
  /** Comes from doctor-list.component.html */
  @Input()
  doctorId: number;

  @Input()
  addDoctor: boolean;

  @Output()
  onSaveSuccess: EventEmitter<boolean> = new EventEmitter();

  /** Doctor data */
  doctorData: DoctorViewInterface;
  /** Copy of the initial doctor data */
  _doctorData: DoctorViewInterface;
  /** Flag for letting know the user that save is in progress */
  /* Used in doctor-details.component.html*/
  isSaving: boolean;
  /** Flag for letting know the user that remove is in progress */
  /* Used in doctor-details.component.html*/
  isRemoving: boolean;
/** Flag for letting know the user that inserting is in progress */
  /* Used in doctor-details.component.html*/
  isInserting: boolean;

  nameForm = new FormControl();
  specializationForm = new FormControl();
  usernameForm = new FormControl();
  passwordForm = new FormControl();
  roleForm = new FormControl();


  constructor(public doctorModel: DoctorModel, private apiService: ApiService, private toastr: ToastrService) {}

  /**
   * This is an Angular method
   * (one of the lifcecycle component hooks: https://angular.io/guide/lifecycle-hooks)
   * Will detect changes on the @Input data, e.g when user select a diferent client from the list
   */
  ngOnChanges(changes: { doctorId: SimpleChange, addDoctor:SimpleChange }) {
    const { doctorId, addDoctor } = changes;


    if (!doctorId || !doctorId.currentValue) this.clearComponentData();

    if (doctorId && doctorId.currentValue && doctorId.currentValue !== doctorId.previousValue) {
      this.clearForm();
      /** If doctorId changes on selection, get the selected doctor details */
      this.getDoctorDetailsData(doctorId.currentValue);
    }
  }

  clearForm()
  {
    this.nameForm.reset();
    this.specializationForm.reset();
    this.usernameForm.reset();
    this.passwordForm.reset();
    this.roleForm.reset();
  }

  /**
   * Requesting doctor details data through the API Service
   * @param doctorId  {number}
   */
  getDoctorDetailsData(doctorId: number): void {
    this.apiService.getDoctorDetails(doctorId).subscribe(
      (data: DoctorViewInterface) => {
        /** Saving the obtained doctor data into a variable */
        this.doctorData = data;
        /** Making copy of the initial doctor data (for comparing purpose only) */
        this._doctorData = Object.assign({}, this.doctorData);
      },
      (error: HttpErrorResponse) => console.error(error)
    );
  }


  /**
   * Will submit the doctor data if has been changed
   * This method is it bind to the `Save Doctor` button
   * in doctor-details.component.html
   */
  onDoctorSave(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the doctorData*/
    this.apiService.saveDoctorDetails(this.doctorData).subscribe(
      /** On Success */
      (data: DoctorViewInterface) => {
        /** Update the copy of the initial doctor data */
        this._doctorData = data;
        /** Notify the parent component to refresh the doctor list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Doctor details updated!');
      },
      /** On Error */
      (error: HttpErrorResponse) => {
        /** Notify the user about the error */
        this.toastr.error(error.message);
        /** End the isSaving flag */
        this.isSaving = false;
      },
      /** End the isSaving flag */
      () => (this.isSaving = false)
    );
  } 


//   /**
//    * Will remove the client data through the API service
//    * This method is it bind to the `Delete` button
//    */
//   onClientRemove(): void {
//     /** Initialize the isRemoving flag */
//     this.isRemoving = true;
//     /** Trigger the removing method from the API Service passing the client ssn*/
//     this.apiService.deleteClient(this.clientData.ssn).subscribe(
//       () => {
//         /** End the isRemoving flag */
//         this.isRemoving = false;
//         /** Clear the component data */
//         this.clearComponentData();
//         /** Notify the parent component to refresh the client list */
//         this.onSaveSuccess.emit(true);
//         /** Notify the user with a successful message */
//         this.toastr.success('Client was removed!');
//       },
//       (error: HttpErrorResponse) => {
//         /** Notify the user about the error */
//         this.toastr.error(error.message);
//         /** End the isRemoving flag */
//         this.isRemoving = false;
//       }
//     );
//   }

  /**
   * Will determinate if the data has been changed or not
   * Buttons will remaing disabled if not change has happened
   * 
   * This is used in doctor-details.component.html
   */
  hasDoctorDataChanged(): boolean {
    return JSON.stringify(this.doctorData) !== JSON.stringify(this._doctorData);
  }

  /** This method will clear the `doctorData` value and `_doctorData` copy value */
  clearComponentData(): void {
    this.doctorData = undefined;
    this._doctorData = undefined;
  }

//     /** Trigger the saving method from the API Service passing the clientData*/
//     this.apiService.insertClientDetails(this.clientData).subscribe(
//       /** On Success */
//       (data: ClientInterface) => {
//         //console.log(this.clientData.ssn);
//         /** Update the copy of the initial client data */
//         this._clientData = data;
//         /** Notify the parent component to refresh the client list */
//         this.onSaveSuccess.emit(true);
//         /** Notify the user with a successful message */
//         this.toastr.success('Client inserted!');
//         this.addClient = false;
//         this.clearForm();
//       },
//       /** On Error */
//       (error: HttpErrorResponse) => {
//         /** Notify the user about the error */
//         this.toastr.error(error.message);
//         /** End the isSaving flag */
//         this.isInserting = false;
//         this.clearForm();
//         this.clientData = undefined;
//       },
//       /** End the isSaving flag */
//       () => (this.isInserting = false)
//     );
//   }
  

//   getSsnErrorMessage() {
//     return this.ssn.hasError('required') ? 'You must enter a value' :
//         this.ssn.hasError('minlength') ? 'SSN is too short; please eneter a 13-digit value' :
//         this.ssn.hasError('maxlength') ? 'SSN is too long; please eneter a 13-digit value' :
//         this.ssn.hasError('pattern') ? 'SSN can contain only numeric characters' :
//             '';
//   }

//   getFirstNameErrorMessage() {
//     return this.firstName.hasError('required') ? 'You must enter a value' :
//             '';
//   }

//   getLastNameErrorMessage() {
//     return this.lastName.hasError('required') ? 'You must enter a value' :
//             '';
//   }

//   getAddressErrorMessage() {
//     return this.address.hasError('required') ? 'You must enter a value' :
//             '';
//   }

//   getIdentityCardNumberErrorMessage() {
//     return this.identityCardNumber.hasError('required') ? 'You must enter a value' :
//             '';
//   }

//   getEmailErrorMessage() {
//     return this.email.hasError('required') ? 'You must enter a value' :
//         this.email.hasError('email') ? 'Not a valid email' :
//             '';
//   }

  
  
}
