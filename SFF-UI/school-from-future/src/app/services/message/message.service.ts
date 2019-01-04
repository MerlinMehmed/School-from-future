import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from 'src/app/model/message';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  messagesUrl = environment.url + "/messages";
  addMessageUrl = environment.url + "/message";
  namesUrl = environment.url + "/names";

  constructor(private http: HttpClient) { }

  getMessages(from: string, to: string): Observable<Message[]> {
    var data = {
      from: from,
      to: to
    }
    return this.http.post<Message[]>(this.messagesUrl, data);
  }

  addMessage(message: Message): Observable<any> {
    var data = {
      from: message.from.email,
      to: message.to.email,
      content: message.content,
      date: message.date
    }
    return this.http.post(this.addMessageUrl, data);
  }
}
