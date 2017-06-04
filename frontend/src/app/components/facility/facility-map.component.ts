import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {WashFacilityService} from "../../model/services/wash-facility.service";
import {WashFacility} from "../../model/entities/wash-facility";
import {ActivatedRoute} from "@angular/router";
import {MouseEvent} from 'angular2-google-maps/core';

@Component({
  selector: 'app-facility-map',
  templateUrl: './facility-map.component.html',
  styleUrls: ['./facility-map.component.css']
})
export class FacilityMapComponent implements OnInit, OnChanges {
  @Input() washFcltList: WashFacility[];
  @Input() initLat: number;
  @Input() initLon: number;
  // google maps zoom level
  zoom: number = 8;
  // initial center position for the map

  constructor(private activateRoute: ActivatedRoute,
              private fcltService: WashFacilityService) {
    this.initLat = 55.7498137;
    this.initLon = 37.5131377;
  }

  ngOnInit() {

  }

  ngOnChanges() {
    //console.log(`washFcltList length: ${this.washFcltList.length}`) ;
  /*  if (this.washFcltList) {
      if (this.washFcltList.length > 0) {
        //   console.log(JSON.stringify(this.washFcltList[0]));
        console.log(`Changed point: lat: ${this.washFcltList[0].facilityAddr.coordinate.lat} : lon: ${this.washFcltList[0].facilityAddr.coordinate.lon}`)
        this.initLat = this.washFcltList[0].facilityAddr.coordinate.lat;
        this.initLon = this.washFcltList[0].facilityAddr.coordinate.lon;
      }
    } */
  }

  clickedMarker(label: string, index: number) {
    console.log(`clicked the facility: ${label || index}`)
  }

  mapClicked($event: MouseEvent) {
    console.log(`clicked the point: lat: ${$event.coords.lat} : lon: ${$event.coords.lng}`);
  }

}
