import {Injectable} from '@angular/core';
import {Http, Response, Headers, HttpModule } from '@angular/http';
import {WashBox} from './washbox';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class WashBoxService {

  private baseUrl: string = 'http://localhost:8080/api/washBox/';

  constructor(private http: Http) {
  }

  get(id: number): Promise<WashBox> {
     let washBox = this.http.get(`${this.baseUrl}/${id}`, {headers: this.getHeaders()})
       .toPromise()
       .then(response => response.json().data as WashBox)
       .catch(this.handleError);
    return washBox;
  }

  getAll(): Promise<WashBox[]> {
   let  washBoxArr = this.http.get(`${this.baseUrl}/WashBox/list`, {headers: this.getHeaders()})
     .toPromise()
     .then(response => response.json().data as WashBox[])
     .catch(this.handleError);
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
    return headers;
  }

}
