import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

/** The AuthGuard service will determinate whther if the user
 * as access to continue or not */
import { AuthGuard } from '../shared/services/auth.guard.service';

/** Importing the starting point for Dashboard module */
import { DashboardMainComponent } from './main/main.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardMainComponent,
    canActivate: [AuthGuard],
    children: [
      /** The following will automatically redirect to /doctors route
       * whenever the user is logged in and try to go the index (/) route
       * eg. http://localhost:4200/ will send the user to http://localhost:4200/doctor */
      //{ path: '', redirectTo: 'doctor', pathMatch: 'full' },
      { path: 'medication', loadChildren: () => import('../medication/medication.module').then(m => m.MedicationModule)},
      { path: 'doctor', loadChildren: () => import('../doctor/doctor.module').then(m => m.DoctorModule) },
      { path: 'caregiver', loadChildren: () => import('../caregiver/caregiver.module').then(m => m.CaregiverModule) },
      { path: 'patient', loadChildren: () => import('../patient/patient.module').then(m => m.PatientModule) }
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {}
