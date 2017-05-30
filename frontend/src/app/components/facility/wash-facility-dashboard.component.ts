import { Component, OnInit } from '@angular/core';
import {WashFacilityService} from "../../model/services/wash-facility.service";
import {WashFacility} from "../../model/entities/wash-facility";

@Component({
  selector: 'app-wash-facility-dashboard',
  templateUrl: './wash-facility-dashboard.component.html',
  styleUrls: ['./wash-facility-dashboard.component.css']
})
export class WashFacilityDashboardComponent implements OnInit {

  washFacilities: Array<WashFacility> = [];
  selectedFclt: WashFacility;

  constructor(private washFacilityService: WashFacilityService) {
    this.washFacilityService = washFacilityService;
  }

  getShortFacilityList(cnt: number): void {
    this.washFacilityService.getAll().then(washFacilityList => this.washFacilities = washFacilityList.slice(0,cnt)).catch(this.handleError);
  }

  ngOnInit(): void {
    this.getShortFacilityList(3);
  }

  onSelect(washFacility: WashFacility): void {
    this.selectedFclt = washFacility;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
