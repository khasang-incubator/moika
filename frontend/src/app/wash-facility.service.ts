import {Injectable} from '@angular/core';
import {WashFacility} from './wash-facility';
import {Http, Headers, Response} from '@angular/http';
//import 'rxjs/add/operator/toPromise';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
//import {WashFacilityList} from "./mock-wash-facility";

@Injectable()
export class WashFacilityService {

  private baseUrl: string = 'http://localhost:8080/api/washFacility';

  constructor(private http: Http) {
  }

  /*
   get(id: number): Promise<WashFacility> {
   let washFacility = this.http.get(`${this.baseUrl}/${id}`, {headers: this.getHeaders()})
   .toPromise()
   .then(response => response.json().data as WashFacility)
   .catch(this.handleError);
   console.log(washFacility.toString());
   // let washFacility = this.getAll()
   //   .then(list => list.find(washFacility => washFacility.idFclt === id));

   return washFacility;
   }

   getAll(): Promise<WashFacility[]> {
   let  washFcltArr = this.http.get(`${this.baseUrl}/list`, {headers: this.getHeaders()})
   .toPromise()
   .then(response => response.json().data as WashFacility[])
   .catch(this.handleError);
   console.log(washFcltArr.toString());
   //let washFcltArr =  Promise.resolve(WashFacilityList);
   return washFcltArr;
   }
   */

  getAll(): Observable<WashFacility[]> {
    let washFcltArr = this.http.get(`${this.baseUrl}/list`, {headers: this.getHeaders()})
      .map(this.mapFacilities);
    console.log(washFcltArr[0].toString());
    return washFcltArr;
  }

  function

  mapFacilities(response: Response): WashFacility[] {
    return response.json().map(this.toWashFacility);
  }

  toWashFacility(r: any): WashFacility {
    return r;
  }


  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  private getHeaders() {
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Access-Control-Allow-Credentials', 'true');
    return headers;
  }


}
