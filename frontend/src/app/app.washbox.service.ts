import {Injectable} from '@angular/core';
import {Http, Response, Headers } from '@angular/http';
import {WashBox} from './washbox';
import 'rxjs/add/operator/toPromise';
//import {WashBoxList} from "./mock-wash-box";

@Injectable()
export class WashBoxService {

  private baseUrl: string = 'http://localhost:8080/api/washBox';

  constructor(private http: Http) {
  }

  get(id: number): Promise<WashBox> {
     let washBox = this.http.get(`${this.baseUrl}/${id}`, {headers: this.getHeaders()})
       .toPromise()
       .then(response => response.json().data as WashBox )
       .catch(this.handleError);
      //   console.log(washBox.then(prWashBox => console.info(prWashBox.id + ' '+prWashBox.boxName)));
      // let washBox = this.getAll()
      //   .then(list => list.find(washBox => washBox.id === id));
    return washBox;
  }

  getAll(): Promise<WashBox[]> {
    let  washBoxArr;
    washBoxArr = this.http.get(this.baseUrl+'/list', {headers: this.getHeaders()})
     .toPromise()
     .then(response => response.json().data as WashBox[])
     .catch(this.handleError);
      console.log("Getting recs: "+ washBoxArr.length );
    //let washBoxArr =  Promise.resolve(WashBoxList);
   return washBoxArr;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список боксов. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
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
