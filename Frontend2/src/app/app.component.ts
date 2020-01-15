import { Component, OnInit, Output } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';
import { StorageService } from './shared/services/storage.service';


@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`,
  styles: []
})
export class AppComponent {
  title = 'Medication Platform';
  private serverUrl = 'http://localhost:8080/api/websocket'
  private stompClient;
  public isCaregiver: boolean = false;
  public username : string;

  public message : string;

  constructor( private toastr: ToastrService, private storageService: StorageService){}

  ngOnInit()
  {
    console.log(this.storageService.get("username"));
    if (this.storageService.get(this.storageService.role_token) == 'ROLE_CAREGIVER') {
      this.isCaregiver = true;
      this.username = this.storageService.get("username");
      this.initializeWebSocketConnection(this.toastr, this.storageService);
    }

  }

  initializeWebSocketConnection(toastr: ToastrService, storageService: StorageService){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/topic", (message) => {
        var id = message.body.split(" ")[4];
        //console.log(id);
      
        if(message.body) {
          toastr.warning(message.body);
          //console.log(message.body);
          storageService.set("websocketmessage", message.body);
          console.log(storageService.get("websocketmessage"));
          
        }

      });
    });

  }


  
  

  
}
