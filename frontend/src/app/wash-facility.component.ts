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
    this.washFacilityService.getAll().subscribe(washFacilityList => this.washFacilityList = washFacilityList);
  }

  ngOnInit(): void {
    this.getAll();
  }

  onSelect(washFacility: WashFacility): void {
    this.selectedFclt = washFacility;
  }

}
