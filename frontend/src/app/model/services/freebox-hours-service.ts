import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class FreeboxHoursService {
  time: string[]=["6:00","6:20","6:40","7:00","7:00","12:00","23:00"]; // Время в течении дня когда бокс свободен

  constructor(private http: Http) {}

  getFreeTime(){
    return this.time;
  }
}


