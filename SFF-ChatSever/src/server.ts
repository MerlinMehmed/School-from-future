import { createServer, Server } from 'http';
import * as express from 'express';
import * as socketIo from 'socket.io';

import { Message } from './model/message.model';
import { User } from './model/user.model';
import { isObject } from 'util';

export class ChatServer {
    public static readonly PORT: number = 8070;
    private app: express.Application;
    private server: Server;
    private io: SocketIO.Server;
    private port: string | number;

    private clients: {};

    constructor() {
        this.clients = {};
        this.createApp();
        this.config();
        this.createServer();
        this.sockets();
        this.listen();
    }

    private createApp(): void {
        this.app = express();
    }

    private createServer(): void {
        this.server = createServer(this.app);
    }

    private config(): void {
        this.port = process.env.PORT || ChatServer.PORT;
    }

    private sockets(): void {
        this.io = socketIo(this.server);
    }

    private listen(): void {
        this.server.listen(this.port, () => {
            console.log('Running server on port %s', this.port);
        });

        // this.io.on('connect', (socket: any) => {
        //     console.log('Connected client on port %s.', this.port);
        //     socket.on('message', (m: Message) => {
        //         console.log('[server](message): %s', JSON.stringify(m));
        //         this.io.emit('message', m);
        //     });

        //     socket.on('disconnect', () => {
        //         console.log('Client disconnected');
        //     });
        // });

        this.io.on('connect', (socket: any) => {
            // console.log(JSON.stringify(this.io))

            socket.on('add-user', (data: User) => {
                console.log(data);
                this.clients[data.email] = {
                    socket: socket.id
                };
                console.log(this.clients);
            });

            socket.on('message', (m: Message) => {
                console.log('[server](message): %s', JSON.stringify(m));
                if (this.clients[m.to.email]) {
                    // this.io.sockets.connected
                    this.io.to(this.clients[m.to.email].socket).emit(
                        'message', m
                    )
                    // console.log(JSON.stringify(this.io))


                    // this.io.clients[this.clients[m.to.email].socket].emit(
                    //     'message', m
                    // );
                } else {
                    console.log("User is not logged: " + m.to.email);
                }
            });

            socket.on('disconnect', () => {
                console.log('Client disconnected');
                for (var user in this.clients) {
                    if (this.clients[user].socket === socket.id) {
                        delete this.clients[user];
                        break;
                    }
                }
            })
        })
    }

    public getApp(): express.Application {
        return this.app;
    }
}