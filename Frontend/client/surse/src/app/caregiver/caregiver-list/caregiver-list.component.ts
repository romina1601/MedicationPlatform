import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CaregiverModel } from 'src/app/shared/models/caregiver.model';
import { ApiService } from 'src/app/shared/services/api.service';
import { CaregiverViewInterface } from 'src/app/shared/models/interfaces/caregiverView';
import { HttpErrorResponse } from '@angular/common/http';
import { StorageService } from 'src/app/shared/services/storage.service';

@Component({
  selector: 'app-caregiver-list',
  templateUrl: './caregiver-list.component.html',
  styleUrls: ['./caregiver-list.component.scss']
})
export class CaregiverListComponent implements OnInit {
  /** Flag for letting know the user that something is loading/happening */
  isProcessing: boolean;

  //flag to see if add caregiver button is selected
  addCaregiver: boolean = false;


  //flag to see if caregiver is logged in
  isCaregiver: boolean;
  //username of logged in caregiver
  username: string;


  constructor(public caregiverModel: CaregiverModel, private apiService: ApiService,
              private storageService: StorageService) {}

  ngOnInit() {
    if (this.storageService.get(this.storageService.role_token) != 'ROLE_CAREGIVER')
      {
        this.isCaregiver = false;
        this.getAllCaregivers();
      }
    else
    {
      this.isCaregiver = true;
      this.username = this.storageService.get("username");
    }
    
    
  }

  getAllCaregivers(reloadCaregivers: boolean = false) {
    if (!reloadCaregivers) {
      /** Initialize the loading process */
      this.isProcessing = true;
      /** Clear the caregiver Model values (if it was instantiated before) */
      this.caregiverModel.clear();
    }
    this.apiService.getCaregivers().subscribe(
      /** On Success: save the list of caregivers in the caregiver model */
      (data: Array<CaregiverViewInterface>) => (this.caregiverModel.all = data),
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

  onAddCaregiver()
  {
    this.addCaregiver = true;
    this.caregiverModel.selectedCaregiverId = null;
  }

  onCaregiverIdSelected(caregiverId: number) {
    this.caregiverModel.selectedCaregiverId = caregiverId;
    this.addCaregiver = false;
  }

}
