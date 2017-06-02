import {Injectable} from '@angular/core';
import {Http, Response, Headers } from '@angular/http';
import {WashBox} from '../entities/wash-box';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {CrudService} from "./crud.service";
//import {WashBoxList} from "./mock-wash-box";

@Injectable()
export class WashBoxService extends CrudService<WashBox> {

  constructor(http: Http) {
    super(http);
    this.workUrl = 'http://localhost:8080/api/washBox';
  }


  /*
  get(id: number): Promise<WashBox> {
     const washBox = this.http.get(`${this.baseUrl}/${id}`, {headers: this.getHeaders()})
       .toPromise()
       .then(response => response.json().data as WashBox )
       .catch(this.handleError);
      // let washBox = this.getAll()
      //   .then(list => list.find(washBox => washBox.id === id)); //getting from mock-wash-facility
    return washBox;
  }

  getAll(): Promise<WashBox[]> {
    const  washBoxArr = this.http.get(`${this.baseUrl}/list`, {headers: this.getHeaders()})
      .map((res: Response) => res.json()).toPromise();
    // let washBoxArr =  Promise.resolve(WashBoxList); //getting from mock-wash-facility
   return washBoxArr;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список боксов. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
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
 */
}
