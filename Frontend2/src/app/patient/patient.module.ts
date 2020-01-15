import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

/** Necessary services and interceptors for this module to work properly */
import { AuthInterceptor } from '../shared/interceptor/http.interceptor';
import { ApiService } from '../shared/services/api.service';
import { AuthGuard } from '../shared/services/auth.guard.service';
import { AuthService } from '../shared/services/auth.service';
import { StorageService } from '../shared/services/storage.service';

/** Importing SharedModule in order to re-use certain components (from an external module) */
import { SharedComponentsModule } from '../shared/components/shared-components.module';

/** Module routing and avialable components (at the same module level) */
import { PatientRoutingModule } from './patient-routing.module';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientItemComponent } from './patient-list/patient-item/patient-item.component';
import { PatientDetailsComponent } from './patient-details/patient-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {MatFormFieldModule} from '@angular/material'
import {MatSelectModule} from '@angular/material'
import {MatCardModule, MatInputModule } from '@angular/material';
import {MatListModule} from '@angular/material/list'
import {MatTableModule} from '@angular/material/table';
import {ChartsModule} from 'ng2-charts';
import {MatPaginatorModule} from '@angular/material/paginator';



@NgModule({
  declarations: [PatientListComponent, PatientItemComponent, PatientDetailsComponent],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, PatientRoutingModule, 
    SharedComponentsModule,  MatFormFieldModule, MatSelectModule, MatCardModule, 
    MatInputModule, MatListModule, MatTableModule, ChartsModule, MatPaginatorModule],
  providers: [
    ApiService,
    AuthGuard,
    AuthService,
    StorageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
})
export class PatientModule {}
