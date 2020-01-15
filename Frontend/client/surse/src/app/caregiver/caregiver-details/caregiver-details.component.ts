import { Component, Input, Output, OnChanges, SimpleChange, EventEmitter } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { CaregiverViewInterface } from 'src/app/shared/models/interfaces/caregiverView';

import { CaregiverModel } from 'src/app/shared/models/caregiver.model';
import { ApiService } from 'src/app/shared/services/api.service';

import {FormControl, Validators} from '@angular/forms'
import { UsersInterface } from 'src/app/shared/models/interfaces/users';
import { CaregiverInsertInterface } from 'src/app/shared/models/interfaces/caregiverInsert';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

@Component({
  selector: 'app-caregiver-details',
  templateUrl: './caregiver-details.component.html',
  styleUrls: ['./caregiver-details.component.scss']
})
export class CaregiverDetailsComponent implements OnChanges {
  
  /** Comes from caregiver-list.component.html */
  @Input()
  caregiverId: number;

  @Input()
  addCaregiver: boolean;

  @Output()
  onSaveSuccess: EventEmitter<boolean> = new EventEmitter();

  /** Caregiver data */
  caregiverData: CaregiverViewInterface;
  /** Copy of the initial client data */
  _caregiverData: CaregiverViewInterface;
  /** Flag for letting know the user that save is in progress */
  /* Used in caregiver-details.component.html*/
  isSaving: boolean;
  /** Flag for letting know the user that remove is in progress */
  /* Used in caregiver-details.component.html*/
  isRemoving: boolean;
/** Flag for letting know the user that inserting is in progress */
  /* Used in caregiver-details.component.html*/
  isInserting: boolean;

  caregiverIdForm = new FormControl();
  nameForm = new FormControl();
  birthDateForm = new FormControl();
  genderForm = new FormControl();
  addressForm= new FormControl();
  medicalRecordForm = new FormControl();
  usernameForm = new FormControl();
  passwordForm = new FormControl();
  roleForm = new FormControl();


  caregiverInsert: CaregiverInsertInterface;

  @Input()
  isCaregiver: boolean;

  @Input()
  username: string;

  private serverUrl = 'http://localhost:8080/api/websocket'
  private stompClient;

  public static webSocketMsg: string;

  constructor(public caregiverModel: CaregiverModel, private apiService: ApiService, private toastr: ToastrService) {
    //this.initializeWebSocketConnection(this.toastr, this.username);
  }


  /**
   * This is an Angular method
   * (one of the lifcecycle component hooks: https://angular.io/guide/lifecycle-hooks)
   * Will detect changes on the @Input data, e.g when user select a diferent caregiver from the list
   */
  ngOnChanges(changes: { caregiverId: SimpleChange, addCaregiver:SimpleChange }) {
    const { caregiverId, addCaregiver } = changes;

    if(!this.isCaregiver)
    {
        
      if (!caregiverId || !caregiverId.currentValue) this.clearComponentData();

      if (caregiverId && caregiverId.currentValue && caregiverId.currentValue !== caregiverId.previousValue) {
        this.clearForm();
        /** If caregiver changes on selection, get the selected caregiver details */
        this.getCaregiverDetailsData(caregiverId.currentValue);
      }
    }
    else
    {
      console.log("you are a caregiver");
      this.getCaregiverDetailsDataByUsername(this.username);
      this.initializeWebSocketConnection(this.toastr, this.username);
    }
    
  }

  initializeWebSocketConnection(toastr: ToastrService, username: String){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      console.log("WHOLE USERNAME IS: " + username);
      var url = "/topic/"+username;
      console.log("WHOLE URL IS: "+url);
      that.stompClient.subscribe(url, (message) => {
        var id = message.body.split(" ")[4];
        //console.log(id);
      
        if(message.body) {
          toastr.warning(message.body);
          //console.log(message.body);
          // storageService.set("websocketmessage", message.body);
          // console.log(storageService.get("websocketmessage"));
          
        }

      });
    });

  }

  clearForm()
  {
    this.caregiverIdForm.reset();
    this.nameForm.reset();
    this.birthDateForm.reset();
    this.genderForm.reset();
    this.addressForm.reset();
    this.medicalRecordForm.reset();
    this.usernameForm.reset();
    this.passwordForm.reset();
    this.roleForm.reset();
  }

  /**
   * Requesting caregiver details data through the API Service
   * @param caregiverId  {number}
   */
  getCaregiverDetailsData(caregiverId: number): void {
      this.apiService.getCaregiverDetails(caregiverId).subscribe(
          (data: CaregiverViewInterface) => {
        /** Saving the obtained caregiver data into a variable */
        this.caregiverData = data;
        /** Making copy of the initial caregiver data (for comparing purpose only) */
        this._caregiverData = Object.assign({}, this.caregiverData);
      },
      (error: HttpErrorResponse) => console.error(error)
    );
  }

  /**
   * Requesting caregiver details data through the API Service using the username
   * @param username  {string}
   */
  getCaregiverDetailsDataByUsername(username: string): void {
    this.apiService.getCaregiverDetailsByUsername(username).subscribe(
      (data: CaregiverViewInterface) => {
        /** Saving the obtained caregiver data into a variable */
        this.caregiverData = data;
        //console.log(data.caregiverDTO.caregiverId);
        /** Making copy of the initial caregiver data (for comparing purpose only) */
        this._caregiverData = Object.assign({}, this.caregiverData);
        console.log("aici");
      },
      (error: HttpErrorResponse) => console.error(error)
    );
  }

  /**
   * Will submit the caregiver data if has been changed
   * This method is it bind to the `Update Caregiver` button
   * in caregiver-details.component.html
   */
  onCaregiverUpdate(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the clientData*/
    this.apiService.updateCaregiverDetails(this.caregiverData).subscribe(
      /** On Success */
      (data: CaregiverViewInterface) => {
        /** Update the copy of the initial client data */
        this._caregiverData = data;
        /** Notify the parent component to refresh the client list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Caregiver details updated!');
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


  /**
   * Will remove the caregiver data through the API service
   * This method is it bind to the `Delete` button
   */
  onCaregiverRemove(): void {
    /** Initialize the isRemoving flag */
    this.isRemoving = true;
    /** Trigger the removing method from the API Service passing the client ssn*/
    this.apiService.deleteCaregiver(this.caregiverData.caregiverId).subscribe(
      () => {
        /** End the isRemoving flag */
        this.isRemoving = false;
        /** Clear the component data */
        this.clearComponentData();
        /** Notify the parent component to refresh the client list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Caregiver was removed!');
      },
      (error: HttpErrorResponse) => {
        /** Notify the user about the error */
        this.toastr.error(error.message);
        /** End the isRemoving flag */
        this.isRemoving = false;
      }
    );
  }

  /**
   * Will determinate if the data has been changed or not
   * Buttons will remaing disabled if not change has happened
   * 
   * This is used in caregiver-details.component.html
   */
  hasCaregiverDataChanged(): boolean {
    return JSON.stringify(this.caregiverData) !== JSON.stringify(this._caregiverData);
  }

  /** This method will clear the `caregiverData` value and `_caregiverData` copy value */
  clearComponentData(): void {
    this.caregiverData = undefined;
    this._caregiverData = undefined;
  }

  /**
   * Will submit the caregiver data if has been changed
   * This method is it bind to the `Insert New Caregiver` button
   * in caregiver-details.component.html
   */
  onCaregiverInsert(): void {
    /** Initialize the isInserting flag */
    this.isInserting = true;

    //initialize caregiver and user needed for insert
    this.caregiverInsert = 
    {
      name: this.nameForm.value,
      birthDate: this.birthDateForm.value,
      gender: this.genderForm.value,
      address: this.addressForm.value,
    }
    

    /** Trigger the saving method from the API Service passing the caregiverInsert and user */
    this.apiService.insertCaregiverDetails(this.caregiverInsert, this.usernameForm.value, 
      this.passwordForm.value, this.roleForm.value).subscribe(
      /** On Success */
      (data: any) => {
        /** Notify the parent component to refresh the caregiver list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Caregiver inserted!');
        this.addCaregiver = false;
        this.clearForm();
      },
      /** On Error */
      (error: HttpErrorResponse) => {
        /** Notify the user about the error */
        this.toastr.error(error.message);
        /** End the isSaving flag */
        this.isInserting = false;
        this.clearForm();
        this.caregiverData = undefined;
      },
      /** End the isSaving flag */
      () => (this.isInserting = false)
    );
  }
  
//     /**
//    * Will submit the patient data if has been changed
//    * This method is it bind to the `Change Caregiver` button
//    * in patient-details.component.html
//    */
//   onChangeCaregiver(): void {
//     /** Initialize the isSaving flag */
//     this.isSaving = true;
//     /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
//     this.apiService.changeCaregiver(this.patientIdForm.value, this.caregiverIdForm.value).subscribe(
//       /** On Success */
//       (data: PatientViewInterface) => {
//         /** Update the copy of the initial patient data */
//         this._patientData = data;
//         /** Notify the parent component to refresh the patient list */
//         this.onSaveSuccess.emit(true);
//         /** Notify the user with a successful message */
//         this.toastr.success('Caregiver changed!');
//       },
//       /** On Error */
//       (error: HttpErrorResponse) => {
//         /** Notify the user about the error */
//         this.toastr.error(error.message);
//         /** End the isSaving flag */
//         this.isSaving = false;
//       },
//       /** End the isSaving flag */
//       () => (this.isSaving = false)
//     );
//   } 
  
}
