import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

/** Authorization Guardian which allow the user to continue or not
 * if it's a valid user */
import { AuthGuard } from '../shared/services/auth.guard.service';

/** Module components */
import { DoctorListComponent } from './doctor-list/doctor-list.component';

const routes: Routes = [
  { path: '', component: DoctorListComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorRoutingModule { }
