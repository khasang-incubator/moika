import {Injectable} from '@angular/core';
import {WashFacility} from '../entities/wash-facility';
import {Http, Headers, Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
// import {WashFacilityList} from "./mock-wash-facility";

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

  getAll(): Promise<WashFacility[]> {
    const washFcltArr = this.http.get(`${this.baseUrl}/list`, {headers: this.getHeaders()})
      .map((res: Response) => res.json()).toPromise();
           // .map(this.mapFacilities);
    return washFcltArr;
  }

  mapFacilities(response: Response): WashFacility[] {
    console.log(response.json().toString());
    return response.json().map(this.toWashFacility);
  }

  toWashFacility(r: any): WashFacility {
    let z: WashFacility = r.resolve();
    return z;
  }


  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  private getHeaders() {
    const headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Access-Control-Allow-Credentials', 'true');
    return headers;
  }


}
