import { Component, Input, Output, OnChanges, SimpleChange, EventEmitter } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

import { MedicationInterface } from 'src/app/shared/models/interfaces/medication';
import { MedicationInsertInterface } from 'src/app/shared/models/interfaces/medicationInsert'

import { MedicationModel } from 'src/app/shared/models/medication.model';
import { ApiService } from 'src/app/shared/services/api.service';
import {  ReactiveFormsModule, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-medication-details',
  templateUrl: './medication-details.component.html',
  styleUrls: ['./medication-details.component.scss']
})
export class MedicationDetailsComponent implements OnChanges {
  @Input()
  medicationId: number;
  
  @Input()
  addMedication: boolean;

  @Input()
  addMedicationToMedPlan: boolean;

  @Output()
  onSaveSuccess: EventEmitter<boolean> = new EventEmitter();

  /** Medication data */
   medicationData: MedicationInterface;
  /** Copy of the initial medication data */
   _medicationData: MedicationInterface;
  /** Flag for letting know the user that save is in progress */
   isSaving: boolean;
  /** Flag for letting know the user that remove is in progress */
   isRemoving: boolean;

  /** Flag for letting know the user that inserting a medication is in progress */
  isInserting: boolean;


  /** For adding new Medication */
  medicationIdForm = new FormControl();
  nameForm = new FormControl();
  sideEffectsForm = new FormControl();
  dosageForm = new FormControl();
  intakeIntervalForm = new FormControl();
  medPlanIdForm = new FormControl();


  /**for insert */
  private medicationInsertData: MedicationInsertInterface;




  constructor(public medicationModel: MedicationModel, private apiService: ApiService, private toastr: ToastrService) {}

  /**
   * This is an Angular method
   * (one of the lifcecycle component hooks: https://angular.io/guide/lifecycle-hooks)
   * Will detect changes on the @Input data, e.g when user select a diferent medication from the list
   */
  ngOnChanges(changes: { medicationId: SimpleChange, addMedication: SimpleChange, payBill: SimpleChange, transferMoney: SimpleChange }) {
    const { medicationId, addMedication, payBill, transferMoney } = changes;

    if (!medicationId || !medicationId.currentValue) this.clearComponentData();

    if (medicationId && medicationId.currentValue && medicationId.currentValue !== medicationId.previousValue) {
      /** If medicationId changes on selection, get the selected medication details */
      this.clearForm();
      this.getMedicationDetailsData(medicationId.currentValue);
    }
  }

  /**
   * Requesting medication details data through the API Service
   * @param medicationId  {number}
   */
  getMedicationDetailsData(medicationId: number): void {
    this.apiService.getMedicationDetails(medicationId).subscribe(
      (data: MedicationInterface) => {
        /** Saving the obtained medication data into a variable */
        this.medicationData = data;
        /** Making copy of the initial medication data (for comparing purpose only) */
        this._medicationData = Object.assign({}, this.medicationData);
      },
      (error: HttpErrorResponse) => console.error(error)
    );
  }


   /**
   * Will submit the medication data if has been changed
   * This method is it bind to the `Update Medication` button
   */
  onMedicationUpdate(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the medicationData*/
    this.apiService.updateMedicationDetails(this.medicationData).subscribe(
      /** On Success */
      (data: MedicationInterface) => {
        /** Update the copy of the initial medication data */
        this._medicationData = data;
        /** Notify the parent component to refresh the medication list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Medication details updated!');
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
   * Will submit the medication data if has been changed
   * This method is it bind to the `Save New Medication` button
   * in medication-details.component.html
   */
  onNewMedicationInsert(): void {
    /** Initialize the isSaving flag */
    this.isInserting = true;
    this.medicationInsertData={
      name: this.nameForm.value,
      sideEffects: this.sideEffectsForm.value,
      dosage: this.dosageForm.value,
      intakeInterval: this.intakeIntervalForm.value
    };

    /** Trigger the update method from the API Service passing the medicationData*/
    this.apiService.insertMedicationDetails(this.medicationInsertData).subscribe(
      
      (data: MedicationInterface) => {
        this.medicationData = Object.assign(data);

        /** Notify the parent component to refresh the medication list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('New Medication Inserted!');

      },

      /** On Error */
      (error: HttpErrorResponse) => {
        /** Notify the user about the error */
        this.toastr.error(error.message);
        /** End the isSaving flag */
        this.isInserting = false;
      }

    );

  }


  /**
   * Will remove the medication data through the API service
   * This method is it bind to the `Delete` button
   */
  onMedicationDelete(): void {
    /** Initialize the isRemoving flag */
    this.isRemoving = true;
    /** Trigger the delete method from the API Service passing the medicationId*/
    this.apiService.deleteMedication(this.medicationData.medicationId).subscribe(
      () => {
        /** End the isRemoving flag */
        this.isRemoving = false;
        /** Clear the component data */
        this.clearComponentData();
        /** Notify the parent component to refresh the medication list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Medication was deleted!');
      },
      (error: HttpErrorResponse) => {
        /** Notify the user about the error */
        this.toastr.error(error.message);
        /** End the isRemoving flag */
        this.isRemoving = false;
      }
    );
  }


  clearForm()
  {
    this.medicationIdForm.setValue(null);
    this.nameForm.setValue(null);
    this.dosageForm.setValue(null);
    this.sideEffectsForm.setValue(null);
  }

  clearFormInsert()
  {
    this.nameForm.reset();
    this.sideEffectsForm.reset();
    this.dosageForm.reset();
    this.intakeIntervalForm.reset();
  }


  /**
   * Will add medication to medication plan
   * This method is it bind to the `Add Medication` button
   * in medication-details.component.html
   */
  onAddMedicationToMedPlan(): void {
    /** Initialize the isSaving flag */
    this.isSaving = true;
    /** Trigger the saving method from the API Service passing the patient and caregiver IDs*/
    this.apiService.addMedicationToMedPlan(this.medicationIdForm.value, this.medPlanIdForm.value).subscribe(
      /** On Success */
      (data: MedicationInterface) => {
        /** Update the copy of the initial medication data */
        this._medicationData = data;
        /** Notify the parent component to refresh the mediaction list */
        this.onSaveSuccess.emit(true);
        /** Notify the user with a successful message */
        this.toastr.success('Medication added to Medication Plan');
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
   * Will determinate if the data has been changed or not
   * Buttons will remaing disabled if not change has happened
   */
  hasMedicationDataChanged(): boolean {
    return JSON.stringify(this.medicationData) !== JSON.stringify(this._medicationData);
  }

  /** This method will clear the `medicationData` value and `_medicationData` copy value */
  clearComponentData(): void {
    this.medicationData = undefined;
    this._medicationData = undefined;
  }
}
