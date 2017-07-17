import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { FreeboxDays } from "../entities/freebox-days";


@Injectable()
export class FreeboxDaysService {
  d: Date;
  constructor(private http: Http) {}
  getData(){

    return this.http.get('assets/data/freebox-days.json')
      .toPromise()
      .then(res => <FreeboxDays[]> res.json().data)
      .then(data => { return data; });
  }
}


