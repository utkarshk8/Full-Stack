import { Component, OnInit } from '@angular/core';
import { Message } from '../message';
import { MessageService } from '../message.service';


@Component({
  selector: 'app-queue-send',
  templateUrl: './queue-send.component.html',
  styleUrls: ['./queue-send.component.css']
})
export class QueueSendComponent implements OnInit {
  message: Message = new Message();
  response!: String;
  constructor(private messageService: MessageService ){}
  ngOnInit(): void {
    
  }
  sendMessageToQueue(){
    
    console.log(this.message.email);
    console.log(this.message.empName);
    this.messageService.sendMessage(this.message).subscribe(
      response => {
        console.log(response); // The string response from the server
      },
      error => {
        console.error(error);
        // Handle error appropriately
      }
    )}
}
