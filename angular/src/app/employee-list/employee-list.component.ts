import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { AuthService } from '@auth0/auth0-angular';
import { map } from 'rxjs/internal/operators/map';
import { switchMap } from 'rxjs/internal/operators/switchMap';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  filteredEmployees!: Employee[];
  searchText!: String;
  employees!: Employee[];
  currentPage: number = 0;
  pageSize: number= 10;
  


  constructor(private employeeService: EmployeeService, private route: Router, public auth: AuthService){}
  ngOnInit(): void {
      
  }
  public getEmployees(){
    
    
    this.employeeService.getEmployeesList(this.currentPage, this.pageSize).subscribe(
      
      (data: any) => { 
        this.employees = data.content;
        this.filteredEmployees = data.content;
      }
    )
  }

 public onPageChange(page: number){
  this.currentPage=page;
  this.getEmployees();
 }

  public updateEmployee(empId: Number){
    console.log(empId);
    this.route.navigate([`/update-employee/${empId}`]);
  }

  public deleteEmployee(empId: Number){
    this.employeeService.deleteEmployee(empId).subscribe(
      data => {
        console.log(data);
      this.getEmployees();
      }
    )
  }

  public onSearchChange(){
    console.log(this.searchText);
    this.filteredEmployees = this.employees.filter(employee =>
      employee.firstName.toLowerCase().includes(this.searchText.toLowerCase())
      || employee.lastName.toLowerCase().includes(this.searchText.toLowerCase())
      || employee.designation.toLowerCase().includes(this.searchText.toLowerCase())
      || employee.emailId.toLowerCase().includes(this.searchText.toLowerCase())
    );
    console.log(this.filteredEmployees);
  
  }

}