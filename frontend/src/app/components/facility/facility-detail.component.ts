import {Component, Input, OnInit} from '@angular/core';
import {WashFacility} from "../../entities/wash-facility";

@Component({
  selector: 'facility-detail',
  templateUrl: './facility-detail.component.html',
  styleUrls: ['./facility-detail.component.css']
})
export class FacilityDetailComponent {

  @Input() washFacility: WashFacility;

  constructor() { }


}
