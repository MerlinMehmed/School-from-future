import { User } from './user';
import { Action } from './action';

export class Message {
    from: User;
    to: User;
    content: string;
    action?: Action;

    constructor(from: User, to: User, content: string, action?: Action) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.action = action;
    }

    // getFrom() {
    //     return this.from;
    // }

    // getTo() {
    //     return this.to;
    // }

    // getContent() {
    //     return this.content;
    // }

    // getAction() {
    //     return this.action;
    // }
}