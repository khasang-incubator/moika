import { Component, OnInit } from '@angular/core';
import {WashFacility} from '../../model/entities/wash-facility';
import {WashFacilityService} from "../../model/services/wash-facility.service";
import {ActivatedRoute} from "@angular/router";
import {City} from "../../model/entities/city";

@Component({
  selector: 'wash-facilities-list',
  templateUrl: './wash-facility-list.component.html',
  styleUrls: ['./wash-facility-list.component.css']
})

export class WashFacilityListComponent implements OnInit {

  selectedCity: City;
  washFacilityList: Array<WashFacility> = [];
  selectedFclt: WashFacility;
  private washFacilityService: WashFacilityService;

  constructor(private activateRoute: ActivatedRoute,
    washFacilityService: WashFacilityService)  {
    this.washFacilityService = washFacilityService;
  }

  getAll(): void {
    this.washFacilityService.getAll().then(washFacilityList => this.washFacilityList = washFacilityList).catch(this.handleError);
  }

  getByCity(): void {
    if (this.selectedCity) {
      this.washFacilityService.getByCity(this.selectedCity.id)
        .then(washFacilityList => {
          this.washFacilityList = washFacilityList;
          this.selectedFclt = this.washFacilityList[0];
        })
        .catch(this.handleError);
    }
  }

  ngOnInit(): void {
    console.log("Current route "+this.activateRoute.snapshot.url.toString());
    this.getByCity();
  }

  onSelect(washFacility: WashFacility): void {
    this.selectedFclt = washFacility;
    console.log( this.selectedFclt);
  }

  onCitySelect(aCity: City){
  //  console.log("Selected city "+ aCity.name);
    this.selectedCity = aCity;
    this.getByCity();
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s, Js.error %s ', error.status, error.url, error.JavascriptError); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
