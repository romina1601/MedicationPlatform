import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

/** Application models:
 * needed for making them available across the entire application */
import { MedicationModel } from './shared/models/medication.model';
import { DoctorModel } from './shared/models/doctor.model';
import { PatientModel } from './shared/models/patient.model';
import { CaregiverModel } from './shared/models/caregiver.model';

/** Application routing and starting point */
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StorageService } from './shared/services/storage.service';

/** Assignment 4 - Chart.js */
import {ChartsModule} from 'ng2-charts';
import {MatPaginatorModule} from '@angular/material/paginator';
//import {MatTableDataSource} from '@angular/material/table';


@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      preventDuplicates: true
    }),
    AppRoutingModule, 
    ChartsModule,
    MatPaginatorModule,
    
     
  ],
  providers: [MedicationModel, DoctorModel, PatientModel, CaregiverModel, StorageService], 
  bootstrap: [AppComponent]
})
export class AppModule {}
