import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {BaseMoikaEntity} from "../entities/base-moika-entity";
import {ICrudService} from './icrud.service';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class CrudService<T extends BaseMoikaEntity>  implements ICrudService<T> {

  private _baseUrl = 'http://localhost:8080';

  constructor(private http: Http) {
  }

  getBaseUrl(): string {
    return this._baseUrl;
  }

  setBaseUrl(value: string) {
    this._baseUrl = value;
  }

  getEntity(id: number): Promise<T> {
    //TODO change Promise to Observable
    const entity = this.http.get(`${this._baseUrl}/${id}`, {headers: this.getHeaders()})
      .toPromise()
      .then(response => response.json().data as T)
      .catch(this.handleError);
    return entity;
  }

  getAll(): Promise<T[]> {
    //TODO change Promise to Observable
    const entityArr = this.http.get(`${this._baseUrl}/list`, {headers: this.getHeaders()})
      .map((res: Response) => res.json()).toPromise();
    return entityArr;
  }

  createEntity<T>(entity: T): Promise<T> {
    //TODO change Promise to Observable
    let headers = this.getHeaders();
    let options = new RequestOptions({headers: headers});
    const url = `${this._baseUrl}/add`;
    const body = JSON.stringify(entity);

    console.log("Create at Url: %s -> body %s", url, body);

    return this.http.post(url, body, options)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }


  deleteEntity<T>(id: number): Boolean {
    let headers = this.getHeaders();
    let options = new RequestOptions({headers: headers});
    let resp : number = 404;
    const url = `${this._baseUrl}/delete/${id}`;

    console.log("Delete at Url: %s -> Id %d", url, id);

    this.http.delete(url, options)
      .toPromise()
      .then(response => resp = response.status)
      .catch(this.handleError);
    return (resp > 300 ? false: true);
  }

  updateEntity<T>(entity: T): Promise<T> {
    //TODO change Promise to Observable
    let headers = this.getHeaders();
    let options = new RequestOptions({headers: headers});
    const url = `${this._baseUrl}/update`;
    const body = JSON.stringify(entity);

    console.log("Update at Url: %s -> body %s", url, body);

    return this.http.put(url, body, options)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Process the response object
   * @param res
   * @returns {{}}
   */
  private extractData(res: Response) {
    let body = res.json();
    console.log("HTTP reult status %d", res.status);
    if (body) {
      console.log("Body %s", JSON.stringify(body));
    }
    return body || {} ;
  }

  /**
   * Error handler
   * @returns {Headers}
   */
  private handleError(error: Response | any): Promise<any> {
    // TODO we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(JSON.stringify(error));
    console.error('Can`t get entity. Error code: %s, URL: %s ', error.status, error.url);
    return Promise.reject(error.message || error);
  }

  /**
   * Make HTTP header object
   * @returns {Headers}
   */
  private getHeaders() {
    const headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Access-Control-Allow-Credentials', 'true');
    return headers;
  }

}
