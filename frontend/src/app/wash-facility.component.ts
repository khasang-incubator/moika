import { Component, OnInit } from '@angular/core';
import {WashFacility} from './wash-facility';
import {WashFacilityService} from "./wash-facility.service";

@Component({
  selector: 'wash-facilities',
  templateUrl: './wash-facility.component.html',
  styleUrls: ['./wash-facility.component.css']
})
export class WashFacilityComponent implements OnInit {

  washFacilityList: Array<WashFacility> = [];
  selectedFclt: WashFacility;
  washFacilityService: WashFacilityService;

  constructor(washFacilityService: WashFacilityService) {
    this.washFacilityService = washFacilityService;
  }

  getAll(): void {
    this.washFacilityService.getAll().then(washFacilityList => this.washFacilityList = washFacilityList).catch(this.handleError);
  }

  ngOnInit(): void {
    this.getAll();
  }

  onSelect(washFacility: WashFacility): void {
    this.selectedFclt = washFacility;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
