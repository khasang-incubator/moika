import { Component } from '@angular/core';
import {InterfaceMode} from "./model/entities/interface-enum";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  //InterfaceMode : InterfaceMode;
 // interfaceMode : InterfaceMode.user;
  // TODO разобраться c ENUM в шаблонах
  interfaceMode = 0;
  title = 'АвтоМойка ждет вас';
}
