import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from './employee';
import { Observable } from 'rxjs/internal/Observable';
import { AuthService } from '@auth0/auth0-angular';
import { switchMap } from 'rxjs/internal/operators/switchMap';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = "http://localhost:8080/dev/employee-api";

  constructor(private httpClient: HttpClient, public auth: AuthService) { }

  getEmployeesList(page: Number, pageSize: Number): Observable<Employee[]>{
    // const params = {
    //   page: page.toString(),
    //   size: pageSize.toString(),
    // }
    // const headers = {
    //   Authorization: 'Bearer ${accessToken}',
    // }
    // return this.httpClient.get<Employee[]>(`${this.baseUrl}/get-all-employees`, {headers, params});


    return this.auth.getAccessTokenSilently({ detailedResponse: true, 
      authorizationParams :{
        audience: "http://localhost:4200",
    } }).pipe(
      switchMap((response) => {
        const accessToken = response.access_token;
        console.log("Access Token:", accessToken);
  
        // Define headers with the access token
        const headers = {
          Authorization: `Bearer ${accessToken}`,
        };
  
        // Define the request parameters
        const params = {
          page: page.toString(),
          size: pageSize.toString(),
        };
  
        // Make the HTTP request with the access token
        return this.httpClient.get<Employee[]>(`${this.baseUrl}/get-all-employees`, { headers, params });
      })
    );
  }
  createEmployeeList(employee: Employee): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/create-employee`, employee);
  }
  deleteEmployee(empId: Number): Observable<Object> {
    // Use the headers in the DELETE request
    return this.httpClient.delete(`${this.baseUrl}/delete-employee/${empId}`);
  }
  getEmployeeById(empId: Number): Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseUrl}/get-employee/${empId}`);
  }
  updateEmployeeById(empId:Number, data: Employee): Observable<any>{
    return this.httpClient.patch(`${this.baseUrl}/update-employee/${empId}`, data);
  }
}
