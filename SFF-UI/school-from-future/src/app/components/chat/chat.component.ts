import { Component, OnInit } from '@angular/core';
import { SocketService } from 'src/app/services/socket/socket.service';
import { Action } from 'src/app/model/Action';
import { Event } from 'src/app/model/Event';
import { User } from 'src/app/model/user';
import { Message } from 'src/app/model/message';
import { LoginService } from 'src/app/services/login/login.service';
import { MessageService } from 'src/app/services/message/message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  action = Action;
  user: User;
  messages: Message[] = [];
  messageContent: string;
  ioConnection: any;

  to: User;

  activeUsers: User[];

  constructor(
    private socketService: SocketService,
    private loginService: LoginService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getActiveUsers();
    this.initUser();
    this.initIoConnection();
  }

  private getMessages() {
    this.messageService.getMessages(this.user.email, this.to.email).subscribe(res => {
      this.messages = res;
      console.log(this.messages);
    })
  }

  private getActiveUsers() {
    this.loginService.getActiveUsers().subscribe(res => {
      console.log(res);
      this.activeUsers = res;
      let thisUser = this.loginService.getLoginUser().email;
      this.activeUsers = this.activeUsers.filter(u => u.email != thisUser);
    })
  }

  private initIoConnection(): void {
    this.socketService.initSocket(this.user);

    this.ioConnection = this.socketService.onMessage()
      .subscribe((message: Message) => {
        console.log("Msg came: " + message);
        console.log(this.messages);
        this.messages.push(message);
      });

    this.socketService.onEvent(Event.CONNECT)
      .subscribe(() => {
        console.log('connected');
      });

    this.socketService.onEvent(Event.DISCONNECT)
      .subscribe(() => {
        console.log('disconnected');
      });
  }

  public getName(from) {
    if(from.name != undefined)
      return from.name;
    else if (this.to.email === from)
      return this.to.name;
    else if (this.user.email === from)
      return this.user.name;
    else return "";
  }

  public sendMessage(message: string): void {
    console.log("Sending")
    if (!message) {
      return;
    }

    // let to = new User("Admin Adminov", "admin@gmail.com");
    let msg = new Message(this.user, this.to, message);
    this.messageService.addMessage(msg).subscribe(() => console.log("finished"));
    this.messages.push(msg)
    this.socketService.send(msg);
    this.messageContent = null;
  }

  // public sendNotification(params: any, action: Action): void {
  //   let message: Message;

  //   if (action === Action.JOINED) {
  //     message = new Message(from, new User("",""), "", action) {
  //       from: this.user,
  //       // action: action
  //     }
  //   }

  //   this.socketService.send(message);
  // }

  initUser() {
    let loggedUser = JSON.parse(sessionStorage.getItem('user'));
    this.user = new User(loggedUser.firstName + " " + loggedUser.lastName, loggedUser.email);
    console.log(this.user);
  }

  chat(user: User) {
    this.to = user;
    console.log(this.to);
    this.getMessages();
  }
}