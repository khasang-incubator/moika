import {Injectable} from '@angular/core';
import {WashFacility} from '../entities/wash-facility';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import {CrudService} from "./crud.service";
import {AppSettings} from "../collections/app-settings";


@Injectable()
export class WashFacilityService extends CrudService<WashFacility> {


  constructor(http: Http) {
    super(http);
    this.workUrl = AppSettings.backSiteUrl+'/washFacility';
  }

  getEntity(id: number): Promise<WashFacility> {
    //TODO change Promise to Observable
    const url = this.workUrl ? this.workUrl : this.baseUrl;
    const entity = this.http.get(`${url}/${id}`, {headers: this.getHeaders()})
      .toPromise()
      .then(response => {
     //   console.log(response.json().data as WashFacility);
        return Promise.resolve(this.parseTo(response.json().data as WashFacility));
      })
      .catch(this.handleError);
    return entity;

  }

  private parseTo(jsonObj : any) :WashFacility{
    let entity : WashFacility = new WashFacility();
    let jsonEntity  =  jsonObj as WashFacility;
    entity.fromJsonObj(jsonEntity);
    return entity ;
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
*/
   getAll(): Promise<WashFacility[]> {
   const url = this.workUrl ? this.workUrl : this.baseUrl;
    let  washFcltArr = this.http.get(`${url}/list`, {headers: this.getHeaders()})
     .map((res: Response) => res.json())
     .toPromise()
      .then(response  => {
        if (response) {
          return Promise.resolve(this.parseArr(response as any[]))
        }
     })
     .catch(this.handleError);
   //console.log(washFcltArr.toString());
   return washFcltArr;
   }

  getByCity(cityId: number): Promise<WashFacility[]> {
    const url = this.workUrl ? this.workUrl : this.baseUrl;
    console.log(`Requesting ${url}/inCity/${cityId}`);
    let  washFcltArr = this.http.get(`${url}/inCity/${cityId}`, {headers: this.getHeaders()})
      .map((res: Response) => res.json())
      .toPromise()
      .then(response  => {
        if (response) {
          console.log(response);
          return Promise.resolve(this.parseArr(response as any[]))
        }
      })
      .catch(this.handleError);
    //console.log(washFcltArr.toString());
    return washFcltArr;
  }

   private parseArr(jsonObjArr : any[]): WashFacility[]{
     let entity : WashFacility[] = new Array<WashFacility>();
      jsonObjArr.forEach(item => {
        console.log(item);
        entity.push(this.parseTo(item))
      });
     return entity;
   }

  /*
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
 */

 /* private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  } */


}
