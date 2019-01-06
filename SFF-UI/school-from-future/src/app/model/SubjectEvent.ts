import { Time } from '@angular/common';

export class SubjectEvent {
    subject: number;
    date: string;
    time: string;
    longitude: number;
    latitude: number;

    constructor(subject: number, date: string, time: string, latitude: number, longitude: number) {
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}