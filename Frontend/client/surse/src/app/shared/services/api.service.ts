import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { MedicationInterface } from '../models/interfaces/medication';
import { MedicationInsertInterface } from '../models/interfaces/medicationInsert';
import { DoctorInterface } from '../models/interfaces/doctor';
import { DoctorViewInterface } from '../models/interfaces/doctorView';
import { PatientViewInterface } from '../models/interfaces/patientView';
import { JustPatientInterface } from '../models/interfaces/justPatient';
import { PatientInsertInterface } from '../models/interfaces/patientInsert';
import { UsersInterface } from '../models/interfaces/users';
import { CaregiverViewInterface } from '../models/interfaces/caregiverView';
import { CaregiverInsertInterface } from '../models/interfaces/caregiverInsert';
import { JustCaregiverInterface } from '../models/interfaces/justCaregiver';
import { MedPlanUpdateInterface } from '../models/interfaces/medPlanUpdate';
import { MedPlanInsertInterface } from '../models/interfaces/medPlanInsert';
import { MonitoredDataInterface } from '../models/interfaces/monitoredData';
import { RecommendationInterface } from '../models/interfaces/recommendation';
import { GrpcMedicationInterface } from '../models/interfaces/grpcMedication';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  /** API Common URL for all end points */
  public BASE_URL: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

/*********************************MEDICATION SERVICES********************************* */

/**
   * [GET] Fetch the list of Medicaitons
   * @return {Array<MedicationInterface>}
   */
  public getMedications(): Observable<Array<MedicationInterface>> {
    const url = `${this.BASE_URL}/medication`;
    return this.http.get<Array<MedicationInterface>>(url);
  }

  /**
   * [GET] Fetch single Medication details
   * @param medicationId  {number}
   * @return {MedicationInterface}
   */
  public getMedicationDetails(medicationId: number): Observable<MedicationInterface> {
    const url = `${this.BASE_URL}/medication/${medicationId}`;
    return this.http.get<MedicationInterface>(url);
  }

    /**
   * [POST] Insert new medication details data
   * @param medicationData {MedicationInsertInterface}
   */
  public insertMedicationDetails(medicationData: MedicationInsertInterface): Observable<MedicationInsertInterface> {
    const url = `${this.BASE_URL}/medication/`;
    return this.http.post<MedicationInsertInterface>(url, medicationData);
  }

  /**
   * [PUT] Update Medication details data
   * @param medicationData {MedicationInterface}
   */
  public updateMedicationDetails(medicationData: MedicationInterface): Observable<MedicationInterface> {
    const url = `${this.BASE_URL}/medication/${medicationData.medicationId}`;
    return this.http.put<MedicationInterface>(url, medicationData);
  }

  /**
   * [DELETE] Remove a Medication
   * @param medicationId {number}
   */
  public deleteMedication(medicationId : number): Observable<any> {
    const url = `${this.BASE_URL}/medication/${medicationId}`;
    return this.http.delete<MedicationInterface>(url);
  }

  /**
   * [PUT] Update Medication details data
   */
  public addMedicationToMedPlan(medicationId: number, medPlanId: number): Observable<MedicationInterface> {
    const url = `${this.BASE_URL}/medication/${medicationId}/${medPlanId}`;
    return this.http.put<MedicationInterface>(url, null);
  }



/*********************************DOCTOR SERVICES************************************* */

  /**
   * [GET] Fetch the list of Doctors
   * @return {Array<DoctorViewInterface>}
   */
  public getDoctors(): Observable<Array<DoctorViewInterface>> {
    const url = `${this.BASE_URL}/doctor`;
    return this.http.get<Array<DoctorViewInterface>>(url);
  }

  /**
   * [GET] Fetch single Doctor details
   * @param doctorId  {number}
   * @return {DoctorViewInterface}
   */
  public getDoctorDetails(doctorId: number): Observable<DoctorViewInterface> {
    const url = `${this.BASE_URL}/doctor/${doctorId}`;
    return this.http.get<DoctorViewInterface>(url);
  }

  /**
   * [PUT] Save doctor details data
   * @param doctorData {DoctorViewInterface}
   */
  public saveDoctorDetails(doctorData: DoctorViewInterface): Observable<DoctorViewInterface> {
    const url = `${this.BASE_URL}/doctor/${doctorData.doctorId}`;
    return this.http.put<DoctorViewInterface>(url, doctorData);
  }




  /*********************************CAREGIVER SERVICES************************************* */
  /**
   * [GET] Fetch the list of Caregivers
   * @return {Array<CaregiverViewInterface>}
   */
  public getCaregivers(): Observable<Array<CaregiverViewInterface>> {
    const url = `${this.BASE_URL}/caregiver`;
    return this.http.get<Array<CaregiverViewInterface>>(url);
  }

  /**
   * [GET] Fetch single Caregiver details
   * @param caregiverId  {number}
   * @return {CaregiverViewInterface}
   */
  public getCaregiverDetails(caregiverId: number): Observable<CaregiverViewInterface> {
    const url = `${this.BASE_URL}/caregiver/${caregiverId}`;
    return this.http.get<CaregiverViewInterface>(url);
  }


  /**
   * [GET] Fetch single Caregiver details using its username
   * @param username  {string}
   * @return {CaregiverViewInterface}
   */
  public getCaregiverDetailsByUsername(username: string): Observable<CaregiverViewInterface> {
    const url = `${this.BASE_URL}/caregiver/username/${username}`;
    return this.http.get<CaregiverViewInterface>(url);
  }
  


  /**
   * [POST] Save new caregiver details data
   * @param caregiverData {CaregiverInsertInterface}
   */
  public insertCaregiverDetails(caregiverData: CaregiverInsertInterface, username: string, password: string, 
                                role: string): Observable<JustCaregiverInterface> {
    const url = `${this.BASE_URL}/caregiver/${username}/${password}/${role}`;
    return this.http.post<JustCaregiverInterface>(url, caregiverData);
  }


  /**
   * [PUT] Save caregiver details data
   * @param caregiverData {CaregiverViewInterface}
   */
  public updateCaregiverDetails(caregiverData: CaregiverViewInterface): Observable<CaregiverViewInterface> {
    const url = `${this.BASE_URL}/caregiver/${caregiverData.caregiverId}`;
    return this.http.put<CaregiverViewInterface>(url, caregiverData);
  }

  /**
   * [DELETE] Remove a caregiver  
   * @param caregiverId {number}
   */
  public deleteCaregiver(caregiverId: number): Observable<any> {
    const url = `${this.BASE_URL}/caregiver/${caregiverId}`;
    return this.http.delete<CaregiverViewInterface>(url);
  }




  /*********************************PATIENT SERVICES************************************* */
  /**
   * [GET] Fetch the list of Patients
   * @return {Array<PatientViewInterface>}
   */
  public getPatients(): Observable<Array<PatientViewInterface>> {
    const url = `${this.BASE_URL}/patient`;
    return this.http.get<Array<PatientViewInterface>>(url);
  }
 
  /**
   * [GET] Fetch single Patient details
   * @param patientId  {number}
   * @return {PatientViewInterface}
   */
  public getPatientDetails(patientId: number): Observable<PatientViewInterface> {
    const url = `${this.BASE_URL}/patient/${patientId}`;
    return this.http.get<PatientViewInterface>(url);
  }

  /**
   * [GET] Fetch single Patient details using its username
   * @param username  {string}
   * @return {PatientViewInterface}
   */
  public getPatientDetailsByUsername(username: string): Observable<PatientViewInterface> {
    const url = `${this.BASE_URL}/patient/username/${username}`;
    return this.http.get<PatientViewInterface>(url);
  }
  
  /**
   * [POST] Save new patient details data
   * @param patientData {PatientInsertInterface}
   */
  public insertPatientDetails(patientData: PatientInsertInterface, username: string, password: string, 
                              role: string, caregiverId: number): Observable<JustPatientInterface> {
    const url = `${this.BASE_URL}/patient/${username}/${password}/${role}/${caregiverId}`;
    return this.http.post<JustPatientInterface>(url, patientData);
  }

  /**
   * [PUT] Save patient details data
   * @param patientData {PatientViewInterface}
   */
  public updatePatientDetails(patientData: PatientViewInterface): Observable<PatientViewInterface> {
    const url = `${this.BASE_URL}/patient/${patientData.patientId}`;
    return this.http.put<PatientViewInterface>(url, patientData);
  }

  /**
   * [DELETE] Remove a patient  
   * @param patientId {number}
   */
  public deletePatient(patientId: number): Observable<any> {
    const url = `${this.BASE_URL}/patient/${patientId}`;
    return this.http.delete<PatientViewInterface>(url);
  }

  /**
   * [PUT] Change caregiver of patient
   * @param patientId {number}
   * @param caregiverId {number}
   */
  public changeCaregiver(patientId: number, caregiverId: number): Observable<PatientViewInterface> {
    const url = `${this.BASE_URL}/patient/${patientId}/${caregiverId}`;
    return this.http.put<PatientViewInterface>(url, null);
  }

  /**** ASSIGNMENT 4 *****/

  /**
   * [POST] Get Medication history
   */
  public getMedicationHistory( patientId: number): Observable<Array<GrpcMedicationInterface>> {
    const url = `${this.BASE_URL}/patient/getMedicationHistory/${patientId}`;
    return this.http.post<Array<GrpcMedicationInterface>>(url, null);
  }

  /**
   * [POST] Get Patient history
   */
  public getPatientHistory( patientId: number): Observable<Array<MonitoredDataInterface>> {
    const url = `${this.BASE_URL}/patient/getPatientHistory/${patientId}`;
    return this.http.post<Array<MonitoredDataInterface>>(url, null);
  }

  /**
   * [POST] Insert Patient Recommendation
   */
  public insertRecommendation( patientId: number, description: string): Observable<RecommendationInterface> {
    const url = `${this.BASE_URL}/patient/insertRecommendation/${patientId}/${description}`;
    return this.http.post<RecommendationInterface>(url, null);
  }

  /**
   * [GET] Fetch Recommendation for patient
   * @param patientId  {number}
   * @return {RecommendationInterface}
   */
  public getRecommendation(patientId: number): Observable<RecommendationInterface> {
    const url = `${this.BASE_URL}/patient/getRecommendation/${patientId}`;
    return this.http.get<RecommendationInterface>(url);
  }






  /*********************************MEDICATION PLAN SERVICES************************************* */
  /**
   * [POST] Save new medication plan details data
   * @param patientId {number}
   * @param medPlanData {MedPlanInsertInterface}
   */
  public insertMedPlanDetails(patientId: number, medPlanData: MedPlanInsertInterface): Observable<MedPlanUpdateInterface> {
    const url = `${this.BASE_URL}/medicationPlan/${patientId}`;
    return this.http.post<MedPlanUpdateInterface>(url, medPlanData);
  }

}



