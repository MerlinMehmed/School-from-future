import { User } from "./user.model";

export class Message {
    from: User;
    to: User;
    content: string;

    constructor(from: User, to: User, content: string) {
        this.from = from;
        this.to = to;
        this.content = content;
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
}

export class ChatMessage extends Message {
    constructor(from: User, to: User, content: string) {
        super(from, to, content);
    }
}