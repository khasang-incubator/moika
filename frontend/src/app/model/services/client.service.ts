import { Injectable } from '@angular/core';
import {CrudService} from "./crud.service";
import {Client} from "../entities/client";
import {Http} from "@angular/http";

@Injectable()
export class ClientService<T extends Client> extends CrudService<T> {

  constructor( http: Http) {
    super(http);
    this.workUrl ='http://localhost:8080/api/client';
  }
}
