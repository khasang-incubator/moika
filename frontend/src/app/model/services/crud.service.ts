import {Injectable} from '@angular/core';
import {Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {BaseMoikaEntity} from "../entities/base-moika-entity";
import {ICrudService} from './icrud.service';

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

  getEntity(id: number): Promise <T> {
     const entity  = this.http.get(`${this._baseUrl}/${id}`, {headers: this.getHeaders()})
       .toPromise()
       .then(response => response.json().data as T )
       .catch(this.handleError);
    return entity;
  }

  getAll(): Promise<T[]> {
    const  entityArr = this.http.get(`${this._baseUrl}/list`, {headers: this.getHeaders()})
      .map((res: Response) => res.json()).toPromise();
   return entityArr;
  }

  createEntity<T>(entity: T): T {  return entity; }

  deleteEntity<T>(entity: T): Boolean { return true; }

  updateEntity<T>(entity: T): T { return entity; }

  private handleError(error: any): Promise<any> {
    console.error('Can`t get entity. Error code: %s, URL: %s ', error.status, error.url);
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
