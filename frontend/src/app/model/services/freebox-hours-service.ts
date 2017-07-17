import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {FreeboxHours} from "../entities/freebox-hour";

@Injectable()
export class FreeboxHoursService {
  freeBoxH: FreeboxHours[] =[{time: '6:00'},{time: '12:00'},{time: '23:00'}];

  constructor(private http: Http) {}

  getFreeTime(){
    return this.freeBoxH;
  }
}


