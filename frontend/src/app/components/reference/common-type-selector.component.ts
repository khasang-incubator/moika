import {Component, OnInit} from '@angular/core';
import {DropdownModule, SelectItem} from 'primeng/primeng';
import {MockMoikaObjectService} from "../../model/services/mock-moika-objects.service";


@Component({
  selector: 'app-common-type-selector',
  templateUrl: './common-type-selector.component.html',
  styleUrls: ['./common-type-selector.component.css']
})
export class CommonTypeSelectorComponent implements OnInit {

  typeRefs: SelectItem[];
  statusRefs: SelectItem[];
  selectedTypeRef: string;
  selectedStatusRef: string;

  constructor(private objectService: MockMoikaObjectService) {
    /* this.typeRefs = [];
     this.typeRefs.push({label:'Типы клиентов', value:{id:1, name: 'clientType', descr: 'Типы клиентов' ,url: 'clientType'}});
     this.typeRefs.push({label:'Типы услуг', value:{id:2, name: 'serviceType', descr: 'Типы услуг', url: 'serviceType'}});
     this.typeRefs.push({label:'Типы машин', value:{id:3, name: 'carType', descr: 'Типы услуг', url: 'api/car/type'}});
     this.typeRefs.push({label:'Типы боксов', value:{id:4, name: 'boxType', descr:'Типы боксов' , url: 'api/washBox/type'}});
     this.typeRefs.push({label:'Типы загрязнений', value:{id:6, name: 'dirtType', descr: 'Типы загрязнений', url: 'dirtType'}}); */
  }

  getTypeRefs(): void {
    this.objectService.getTypeRefs().then(
      typeItemList =>
        this.typeRefs = typeItemList.map(this.makeDropDownItem)
    )
  }

  getStatusRefs(): void {
    this.objectService.getStatusRefs().then(
     statusItemList =>
        this.statusRefs = statusItemList.map(this.makeDropDownItem)
    )
  }

  makeDropDownItem(element): SelectItem {
    return {label: element.name, value: element};
  }


  ngOnInit() {
    this.getTypeRefs();
  }

}
