import { Injectable } from '@angular/core';
import {CrudService} from "./crud.service";
import {Client} from "../entities/client";
import {Http} from "@angular/http";
import {AppSettings} from "../collections/app-settings";

@Injectable()
export class ClientService extends CrudService<Client> {

  constructor( http: Http) {
    super(http);
    this.workUrl = AppSettings.backSiteUrl+'/client';
  }
}
