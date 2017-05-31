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


  constructor(private objectService: MockMoikaObjectService) {
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
