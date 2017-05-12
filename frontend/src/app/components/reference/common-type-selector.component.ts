import { Component, OnInit } from '@angular/core';
import {TypeRefList} from "../../entities/ref-type-list";
import {DropdownModule, SelectItem} from 'primeng/primeng';


@Component({
  selector: 'app-common-type-selector',
  templateUrl: './common-type-selector.component.html',
  styleUrls: ['./common-type-selector.component.css']
})
export class CommonTypeSelectorComponent implements OnInit {

  typeRefs:  SelectItem[];
  selectedType: string;

  constructor() {
    this.typeRefs = [];
    this.typeRefs.push({label:'Типы клиентов', value:{id:1, name: 'clientType', descr: 'Типы клиентов' ,url: 'clientType'}});
    this.typeRefs.push({label:'Типы услуг', value:{id:2, name: 'serviceType', descr: 'Типы услуг', url: 'serviceType'}});
    this.typeRefs.push({label:'Типы машин', value:{id:3, name: 'carType', descr: 'Типы услуг', url: 'api/car/carType'}});
    this.typeRefs.push({label:'Типы боксов', value:{id:4, name: 'boxType', descr:'Типы боксов' , url: 'api/washBox/boxType'}});
    this.typeRefs.push({label:'Типы загрязнений', value:{id:6, name: 'dirtType', descr: 'Типы загрязнений', url: 'dirtType'}});
  }

  ngOnInit() {
  }

}
