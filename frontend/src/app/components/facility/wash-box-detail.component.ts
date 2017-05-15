import {Component, Input, OnInit} from '@angular/core';
import {WashBox} from "../../model/entities/wash-box";

@Component({
  selector: 'wash-box-detail',
  templateUrl: './wash-box-detail.component.html',
  styleUrls: ['./wash-box-detail.component.css']
})
export class WashBoxDetailComponent implements OnInit {

  @Input() washBox: WashBox;

  private input() {

  }
  constructor() { }

  ngOnInit() {
  }

}
