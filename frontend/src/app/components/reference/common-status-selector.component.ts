import { Component, OnInit } from '@angular/core';
import {SelectItem} from "primeng/primeng";
import {MockMoikaObjectService} from "../../model/services/mock-moika-objects.service";

@Component({
  selector: 'app-common-status-selector',
  templateUrl: './common-status-selector.component.html',
  styleUrls: ['./common-status-selector.component.css']
})
export class CommonStatusSelectorComponent implements OnInit {

  statusRefs: SelectItem[];
  selectedStatusRef: string;

  constructor(private objectService: MockMoikaObjectService) {
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
    this.getStatusRefs();
  }

}
