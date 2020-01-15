import { Component, Input, Output, OnChanges, SimpleChange, EventEmitter, ViewChild, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from 'src/app/shared/services/api.service';

import { PatientViewInterface } from 'src/app/shared/models/interfaces/patientView';
import { PatientModel } from 'src/app/shared/models/patient.model';


import {FormControl} from '@angular/forms';


import { JustPatientInterface } from 'src/app/shared/models/interfaces/justPatient';
import { CaregiverForPatientInterface } from 'src/app/shared/models/interfaces/caregiverForPatient';
import { PatientInsertInterface } from 'src/app/shared/models/interfaces/patientInsert';
import { MedPlanInsertInterface } from 'src/app/shared/models/interfaces/medPlanInsert';
import { MedPlanUpdateInterface } from 'src/app/shared/models/interfaces/medPlanUpdate';
import { MonitoredDataInterface } from 'src/app/shared/models/interfaces/monitoredData';
import { GrpcMedicationInterface } from 'src/app/shared/models/interfaces/grpcMedication';

import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material';
import { RecommendationInterface } from 'src/app/shared/models/interfaces/recommendation';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.scss']
})




export class PatientDetailsComponent implements OnChanges, OnInit {
  
  /** Comes from patient-list.component.html */
  @Input()
  patientId: number;

  @Input()
  addPatient: boolean;

  @Input()
  changeCaregiver: boolean;

  @Input()
  createMedPlan: boolean;

  @Input()
  activityReport: boolean;

  @Input()
  medicationHistory: boolean;

  @Input()
  isPatient: boolean;

  @Input()
  username: string;

  /**
   * Emits a boolean variable = reloadPatients in patient-list.component.ts
   * so that whenever a succesfull change is made, the patient list is reloaded
   */
  @Output()
  onSaveSuccess: EventEmitter<boolean> = new EventEmitter();

  /** Patient data */
  patientData: PatientViewInterface;
  /** Copy of the initial client data */
  _patientData: PatientViewInterface;

  /** Monitored data response */

  /** Flag for letting know the user that save is in progress */
  /* Used in patient-details.component.html*/
  isSaving: boolean;
  /** Flag for letting know the user that remove is in progress */
  /* Used in patient-details.component.html*/
  isRemoving: boolean;
/** Flag for letting know the user that inserting is in progress */
  /* Used in patient-details.component.html*/
  isInserting: boolean;
/** All form displayed in patient-details.component.html
 *  in differrent scenarios
 */
  patientIdForm = new FormControl();
  nameForm = new FormControl();
  birthDateForm = new FormControl();
  genderForm = new FormControl();
  addressForm= new FormControl();
  medicalRecordForm = new FormControl();
  usernameForm = new FormControl();
  passwordForm = new FormControl();
  roleForm = new FormControl();
  caregiver: CaregiverForPatientInterface;
  caregiverIdForm = new FormControl();
  caregiverNameForm = new FormControl();
  medPlansForm = new FormControl();



  justPatient: JustPatientInterface;
  patientInsert: PatientInsertInterface;

  /*****Forms for creating medication plan*** */
  medPlanNameForm = new FormControl();
  medPlanStartDateForm = new FormControl();
  medPlanEndDateForm = new FormControl();
  /******Data to insert medication plan***** */
  medPlanInsert: MedPlanInsertInterface;

  /******* Assignment 4 *********/
  recommendationForm = new FormControl();

  /**** Chart properties */
  public pieChartLabels = ['Sleeping', 'Toileting', 'Showering', 'Breakfast', 'Grooming', 'Spare Time/TV', 'Leaving', 'Lunch', 'Snack'];
  public pieChartType = 'pie';
  public pieChartData = new Array<number>();
  chartReady: boolean = false;

  /*** Table of activities (patient history) */
  allActivities : Array<MonitoredDataInterface> = new Array<MonitoredDataInterface>();
  displayedColumns = ['monitoredDataId', 'patientId', 'name', 'startTime', 'endTime', 'behavior'];
  dataSource = new MatTableDataSource<MonitoredDataInterface>();
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  /*** Table of medications (medication history) */
  allmedications : Array<GrpcMedicationInterface> = new Array<GrpcMedicationInterface>();
  displayedColumns2 = ['grpcMedicationId', 'patientId', 'name', 'status'];
  dataSource2 = new MatTableDataSource<GrpcMedicationInterface>();
  @ViewChild(MatPaginator, {static: true}) paginator2: MatPaginator;



  
  constructor(public patientModel: PatientModel, private apiService: ApiService, private toastr: ToastrService) {}

  ngOnInit() {}

  /**
   * This is an Angular method
   * (one of the lifcecycle component hooks: https://angular.io/guide/lifecycle-hooks)
   * Will detect changes on the @Input data, e.g when user select a diferent patient from the list
   */
  ngOnChanges(changes: { patientId: SimpleChange, addPatient:SimpleChange, changeCaregiver: SimpleChange, createMedPlan: SimpleChange }) {
    const { patientId, addPatient, changeCaregiver, createMedPlan } = changes;

    /** if a doctor is logged in, render the details of the selected patient */
    if(!this.isPatient)
    {
      if (!patientId || !patientId.currentValue) this.clearComponentData();

      if (patientId && patientId.currentValue && patientId.currentValue !== patientId.previousValue) {
        this.clearForm();
        /** If patientId changes on selection, get the selected patient details */
        this.getPatientDetailsData(patientId.currentValue);
      }
    }
    /** If a patient is logged in, render its details;
     * The selected user is decided at login and comes from
     * patient-list.component.ts (from the storageService)
     */
    else
    {
      console.log("you are a patient");
      this.getPatientDetailsDataByUsername(this.username);
    }
    
  }
  

  /**
   * Requesting patient details data through the API Service,
   * to render the data of the selected patient
   * @param patientId  {number}
   */
  getPatientDetailsData(patientId: number): void {
    this.apiService.getPatientDetails(patientId).subscribe(
      (data: PatientViewInterface) => {
        /** Saving the obtained patient data into a variable */
        this.patientData = data;
        /** Making copy of the initial patient data (for comparing purpose only) */
        this._patientData = Object.assign({}, this.patientData);
      },
      (error: HttpErrorResponse) => console.error(error)
    );
  }

  /**
   * Requesting patient details data through the API Service using the username
   * (used when a patient is logged in)
   * @param username  {string}
   */
  getPatientDetailsDataByUsername(username: string): void {
    this.apiService.getPatientDetailsByUsername(username).subscribe(
      (data: PatientViewInterface) => {
        /** Saving the obtained patient data into a variable */
        this.patientData = data;
        /** Don't need to emit true, as the patient list is not visible,
         * so thre's no need to reload it
         */
      },
      (error: HttpErrorResponse) => console.error(error)
    );
  }

  /**
   * Will submit the patient data if has been changed
   * This method is it bind to the `Update Patient` button
   * in patient-details.component.html
   */
  onPatientUpdate(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the clientData*/
    this.apiService.updatePatientDetails(this.patientData).subscribe(
      /** On Success */
      (data: PatientViewInterface) => {
        /** Update the copy of the initial client data */
        this._patientData = data;
        /** Notify the parent component to refresh the client list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Patient details updated!');
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
   * Will remove the patient data through the API service
   * This method is it bind to the `Delete` button
   */
  onPatientRemove(): void {
    /** Initialize the isRemoving flag */
    this.isRemoving = true;
    /** Trigger the removing method from the API Service passing the client ssn*/
    this.apiService.deletePatient(this.patientData.patientId).subscribe(
      () => {
        /** End the isRemoving flag */
        this.isRemoving = false;
        /** Clear the component data */
        this.clearComponentData();
        /** Notify the parent component to refresh the client list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Patient was removed!');
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
   * Will submit the patient data if has been changed
   * This method is it bind to the `Insert New Patient` button
   * in patient-details.component.html
   */
  onPatientInsert(): void {
    /** Initialize the isInserting flag */
    this.isInserting = true;

    //initialize patient and user needed for insert
    this.patientInsert = 
    {
      name: this.nameForm.value,
      birthDate: this.birthDateForm.value,
      gender: this.genderForm.value,
      address: this.addressForm.value,
      medicalRecord: this.medicalRecordForm.value
    }

    

    /** Trigger the saving method from the API Service passing the patientInsert and user */
    this.apiService.insertPatientDetails(this.patientInsert, this.usernameForm.value, 
      this.passwordForm.value, this.roleForm.value, this.caregiverIdForm.value).subscribe(
      /** On Success */
      (data: any) => {
        /** Notify the parent component to refresh the patient list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Patient inserted!');
        this.addPatient = false;
        this.clearForm();
      },
      /** On Error */
      (error: HttpErrorResponse) => {
        /** Notify the user about the error */
        this.toastr.error(error.message);
        /** End the isSaving flag */
        this.isInserting = false;
        this.clearForm();
        this.patientData = undefined;
      },
      /** End the isSaving flag */
      () => (this.isInserting = false)
    );
  }
  
  /**
   * Will change the caregiver of a patient
   * This method is it bind to the `Change Caregiver` button
   * in patient-details.component.html
   */
  onChangeCaregiver(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
    this.apiService.changeCaregiver(this.patientIdForm.value, this.caregiverIdForm.value).subscribe(
      /** On Success */
      (data: PatientViewInterface) => {
        /** Update the copy of the initial patient data */
        this._patientData = data;
        /** Notify the parent component to refresh the patient list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Caregiver changed!');
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
   * Will create a new medication plan
   * This method is it bind to the `Create MedPlan` button
   * in patient-details.component.html
   */
  onCreateMedPlan(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Create med plan data to be inserted */
    this.medPlanInsert = 
    {
      name : this.medPlanNameForm.value,
      startDate : this.medPlanStartDateForm.value,
      endDate : this.medPlanEndDateForm.value
    }

    /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
    this.apiService.insertMedPlanDetails(this.patientIdForm.value, this.medPlanInsert).subscribe(
      /** On Success */
      (data: MedPlanUpdateInterface) => {
        /** Notify the parent component to refresh the patient list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Medication Plan Created');
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
   * Will display the Activity Report for a patient
   * This method is it bind to the `Generate Activity Report` button
   * in patient-details.component.html
   */
  onGetMedicationHistory(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
    this.apiService.getMedicationHistory(this.patientIdForm.value).subscribe(
      /** On Success */
      (data: Array<GrpcMedicationInterface>) => {

        //this.allActivities = data;
		console.log(data);
		
        this.dataSource2 = new MatTableDataSource(data);
		console.log(this.dataSource2);
        this.dataSource2.paginator = this.paginator2;
        
        /** Notify the parent component to refresh the patient list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Medication History Displayed!');
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
   * Will display the Activity Report for a patient
   * This method is it bind to the `Generate Activity Report` button
   * in patient-details.component.html
   */
  onGetPatientHistory(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
    this.apiService.getPatientHistory(this.patientIdForm.value).subscribe(
      /** On Success */
      (data: Array<MonitoredDataInterface>) => {

        //this.allActivities = data;
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        
        /** Notify the parent component to refresh the patient list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Report Generated!');

        this.pieChartData = new Array<number>();

        //console.log(data);
        this.computeActivitiesOccurances(data);
        this.chartReady = true;
        console.log(this.pieChartData);
      
               
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
   * Will insert a Recommendation for a patient
   * This method is it bind to the `Generate Activity Report` button
   * in patient-details.component.html
   */
  onInsertRecommendation(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
    this.apiService.insertRecommendation(this.patientIdForm.value, this.recommendationForm.value).subscribe(
      /** On Success */
      (data: RecommendationInterface) => {

                
        /** Notify the parent component to refresh the patient list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Recommendation Inserted!');

        console.log(data);
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

  computeActivitiesOccurances( data: Array<MonitoredDataInterface>): void {

    var sleeping = 0;
    var toileting = 0;
    var showering = 0; 
    var breakfast = 0;
    var spareTime = 0;
    var leaving = 0;
    var lunch = 0;
    var grooming = 0;
    var snack = 0;

    data.forEach(element => {

      if (element.name === "Sleeping")
      {
        sleeping++;
      }
      else if(element.name === "Toileting")
      {
        toileting++;
      }
      else if(element.name === "Showering")
      {
        showering++;
      }
      else if(element.name === "Breakfast")
      {
        breakfast++;
      }
      else if(element.name === "Grooming")
      {
        grooming++;
      }
      else if(element.name === "Spare_Time/TV")
      {
        spareTime++;
      }
      else if(element.name === "Leaving")
      {
        leaving++;
      }
      else if(element.name === "Lunch")
      {
        lunch++;
      }
      else if(element.name === "Snack")
      {
        snack++;
      }
    });

    this.pieChartData.push(sleeping, toileting, showering, breakfast, grooming, spareTime, leaving, lunch, snack);

  }


  /**
   * Will determinate if the data has been changed or not
   * Buttons will remaing disabled if not change has happened
   * 
   * This is used in patient-details.component.html
   */
  hasPatientDataChanged(): boolean {
    return JSON.stringify(this.patientData) !== JSON.stringify(this._patientData);
  }

  /** This method will clear the `patientData` value and `_patientData` copy value */
  clearComponentData(): void {
    this.patientData = undefined;
    this._patientData = undefined;
  }

  /** reset form whenever Clear form button is pressed
   * (used in multiple scenarios)
   */
  clearForm()
  {
    this.patientIdForm.reset();
    this.nameForm.reset();
    this.birthDateForm.reset();
    this.genderForm.reset();
    this.addressForm.reset();
    this.medicalRecordForm.reset();
    this.usernameForm.reset();
    this.passwordForm.reset();
    this.roleForm.reset();
    this.caregiverIdForm.reset();
    this.medPlanNameForm.reset();
    this.medPlanStartDateForm.reset();
    this.medPlanEndDateForm.reset();
    this.recommendationForm.reset();
  }
  
}
