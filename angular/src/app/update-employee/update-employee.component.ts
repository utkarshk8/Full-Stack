import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  empid!: Number;
  employee: Employee = new Employee();
  onSubmit(){
    console.log(this.employee);
    this.updateEmployeeById(this.empid, this.employee);
  }

  constructor(private routes: ActivatedRoute, private employeeService: EmployeeService, private route: Router){}
  ngOnInit(): void {
    this.routes.params.subscribe(params => {
        this.empid = params['id'];
        console.log(this.empid);
        this.getEmployeeById(this.empid);
    })
  }
  getEmployeeById(empId: Number){
    this.employeeService.getEmployeeById(empId).subscribe(data => {
      console.log(data);
      this.employee = data;
      console.log(this.employee);
    },
    (error) => {
      console.log(error);
    }
    )      
  }
  updateEmployeeById(empId: Number, data: Employee){
    this.employeeService.updateEmployeeById(empId, data).subscribe(data=>{
      console.log(data);
      this.route.navigate(['employees']);
    });
  }
}
