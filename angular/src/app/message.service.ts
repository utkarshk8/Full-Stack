import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from './message';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private baseUrl = "http://localhost:8080/dev/employee-api";

  constructor(private httpClient: HttpClient) { }
  sendMessage(message: Message): Observable<Object>{
    console.log("From Service");
    console.log(message.email);
    return this.httpClient.post(`${this.baseUrl}/queue`, message);
  }
}
