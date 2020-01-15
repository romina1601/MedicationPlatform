import { Component, OnInit } from '@angular/core';
import { MedicationModel } from 'src/app/shared/models/medication.model';
import { ApiService } from 'src/app/shared/services/api.service';
import { MedicationInterface } from 'src/app/shared/models/interfaces/medication';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-medication-list',
  templateUrl: './medication-list.component.html',
  styleUrls: ['./medication-list.component.scss']
})
export class MedicationListComponent implements OnInit {
    /** Flag for letting know the user that something is loading/happening */
    isProcessing: boolean;

    //flag to see if add medication button is selected
    addMedication: boolean = false;

    //flag to see if add medication button is selected
    addMedicationToMedPlan: boolean = false;

    /** Flag for letting know the user that save is in progress */
    isSaving: boolean;
  

  constructor(public medicationModel: MedicationModel, private apiService: ApiService) {}

  ngOnInit() {
    this.getAllMedications();
  }

  getAllMedications(reloadMedications: boolean = false) {
    this.addMedication = false;
    if (!reloadMedications) {
      /** Initialize the loading process */
      this.isProcessing = true;
      /** Clear the Medication Model values (if it was instantiated before) */
      this.medicationModel.clear();
    }
    this.apiService.getMedications().subscribe(
      /** On Success: save the list of medications in the medication model */
      (data: Array<MedicationInterface>) => (this.medicationModel.all = data),
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


  onMedicationIdSelected(medicationId: number) 
  {
    this.medicationModel.selectedMedicationId = medicationId;
    this.addMedication = false;
    this.addMedicationToMedPlan = false;
  }

  onAddMedication()
  {
    this.addMedication = true;
    this.addMedicationToMedPlan = false;
  }

  onAddMedicationToMedPlan()
  {
    this.addMedication = false;
    this.addMedicationToMedPlan = true;
  }

}
