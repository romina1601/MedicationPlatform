import { Component, OnInit } from '@angular/core';
import { PatientModel } from 'src/app/shared/models/patient.model';
import { ApiService } from 'src/app/shared/services/api.service';
import { PatientViewInterface } from 'src/app/shared/models/interfaces/patientView';
import { HttpErrorResponse } from '@angular/common/http';
import { StorageService } from 'src/app/shared/services/storage.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss']
})
export class PatientListComponent implements OnInit {
  /** Flag for letting know the user that something is loading/happening */
  isProcessing: boolean;

  //flag to see if add patient button is selected
  addPatient: boolean = false;

  //flag to see if change caregiver button is selected
  changeCaregiver: boolean = false;

  //flag to see if create medPlan button is selected
  createMedPlan: boolean = false;

  //flag to see if create Activity Report button is selected
  activityReport: boolean = false;

  //flag to see if create Medication History button is selected
  medicationHistory: boolean = false;

  /** flag to see if a patient is logged in
   * used in patient-list.component.html
   * to know if the list of patients should be displayed 
   */ 
  isPatient: boolean;

  //username of logged in patient
  username: string;


  constructor(public patientModel: PatientModel, private apiService: ApiService,
              private storageService: StorageService) {}

  ngOnInit() {
    if (this.storageService.get(this.storageService.role_token) != 'ROLE_PATIENT') {
        this.isPatient = false;
        this.getAllPatients();
      }
    else {
      this.isPatient = true;
      this.username = this.storageService.get("username");
    }
  }

  /**
   * 
   * Sets the list of all patients in the patient module
   */
  getAllPatients(reloadPatients: boolean = false) {
    if (!reloadPatients) {
      /** Initialize the loading process */
      this.isProcessing = true;
      /** Clear the patient Model values (if it was instantiated before) */
      this.patientModel.clear();
    }
    this.apiService.getPatients().subscribe(
      /** On Success: save the list of patients in the patient model */
      (data: Array<PatientViewInterface>) => (this.patientModel.all = data),
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

  /**
   * Modify the flags to know what forms to render
   * flags are used in patient-details.component.html
   */
  onAddPatient()
  {
    this.addPatient = true;
    this.patientModel.selectedPatientId = null;
    this.changeCaregiver = false;
    this.createMedPlan = false;
    this.activityReport = false;
    this.medicationHistory = false;
  }

  onPatientIdSelected(patientId: number) {
    this.patientModel.selectedPatientId = patientId;
    this.addPatient = false;
    this.changeCaregiver = false;
    this.createMedPlan = false;
    this.activityReport = false;
    this.medicationHistory = false;
  }

  onChangeCaregiver()
  {
    this.patientModel.selectedPatientId = null;
    this.addPatient = false;
    this.changeCaregiver = true;
    this.createMedPlan = false;
    this.activityReport = false;
    this.medicationHistory = false;
  }

  onCreateMedPlan()
  {
    this.patientModel.selectedPatientId = null;
    this.addPatient = false;
    this.changeCaregiver = false;
    this.createMedPlan = true;
    this.activityReport = false;
    this.medicationHistory = false;
  }

  onGetPatientHistory()
  {
    this.addPatient = false;
    this.patientModel.selectedPatientId = null;
    this.changeCaregiver = false;
    this.createMedPlan = false;
    this.activityReport = true;
    this.medicationHistory = false;
  }

  onGetMedicationHistory()
  {
    this.addPatient = false;
    this.patientModel.selectedPatientId = null;
    this.changeCaregiver = false;
    this.createMedPlan = false;
    this.activityReport = false;
    this.medicationHistory = true;
  }
}
