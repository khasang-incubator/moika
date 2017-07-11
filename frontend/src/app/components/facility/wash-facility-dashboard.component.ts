import {Component, Input, OnInit} from '@angular/core';
import {WashFacilityService} from "../../model/services/wash-facility.service";
import {WashFacility} from "../../model/entities/wash-facility";
import {ActivatedRoute} from "@angular/router";
import {City} from "../../model/entities/city";

@Component({
  selector: 'app-wash-facility-dashboard',
  templateUrl: './wash-facility-dashboard.component.html',
  styleUrls: ['./wash-facility-dashboard.component.css']
})
export class WashFacilityDashboardComponent implements OnInit {

  selectedCity: City;
  washFacilities: WashFacility[];
  selectedFclt: WashFacility;
  actionMsg: string;

  constructor(private activateRoute: ActivatedRoute,
    private washFacilityService: WashFacilityService) {

  }

  getShortFacilityList(cnt: number): void {
    this.washFacilityService.getAll().then(washFacilityList => this.washFacilities = washFacilityList.slice(0,cnt)).catch(this.handleError);
  }

  getAll(): void {
    this.actionMsg = 'Обработка данных. Ждите...';
    this.washFacilityService.getAll()
      .then(washFcltList => {
        this.washFacilities = washFcltList;
        this.actionMsg = '';
      })
      .catch(this.handleError);
  }

  ngOnInit(): void {
    console.log("Current route "+this.activateRoute.snapshot.url.toString());
    this.getAll();
  }

  onSelect(washFacility: WashFacility): void {
    this.selectedFclt = washFacility;
  }

  onCitySelect(aCity: City){
    console.log("Selected city "+ aCity.name);
    this.selectedCity = aCity;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
