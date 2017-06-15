import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {InterfaceMode} from "./model/entities/interface-enum";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements  OnInit {
  //InterfaceMode : InterfaceMode;
  // interfaceMode : InterfaceMode.user;
  // TODO разобраться c ENUM в шаблонах

  ngOnInit(): void {

  }

}

