import * as socketIo from 'socket.io-client';

import { Observable } from 'rxjs';
import { Message } from 'src/app/model/message';
import { Event } from 'src/app/model/Event';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';

const SERVER_URL = 'http://localhost:8070';

@Injectable({
  providedIn: 'root'
})

export class SocketService {
    private socket;

    public initSocket(user: User): void {
        this.socket = socketIo(SERVER_URL);
        console.log("initialize user");
        this.socket.emit('add-user', user);
    }

    public send(message: Message): void {
        this.socket.emit('message', message);
    }

    public onMessage(): Observable<Message> {
        return new Observable<Message>(observer => {
            this.socket.on('message', (data: Message) => observer.next(data));
        });
    }

    public onEvent(event: Event): Observable<any> {
        return new Observable<Event>(observer => {
            this.socket.on(event, () => observer.next());
        });
    }
}