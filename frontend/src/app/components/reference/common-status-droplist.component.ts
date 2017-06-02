import {Component, Input, OnInit} from '@angular/core';
import {SelectItem} from "primeng/primeng";
import {SomeStatus} from "../../model/entities/some-status";
import {CrudService} from "../../model/services/crud.service";

@Component({
  selector: 'app-common-status-droplist',
  templateUrl: './common-status-droplist.component.html',
  styleUrls: ['./common-status-droplist.component.css']
})
export class CommonStatusDroplistComponent implements OnInit {

  @Input() refStatus: any;

  someStatusItemList: SelectItem[];
  retList: SomeStatus[];
  selectedStatus: SomeStatus;

  private statusUrl: string;
  private startUrl: string;
  private statusFullName: string;


  constructor(private statusService: CrudService<SomeStatus>) {
    this.startUrl = statusService.baseUrl;
  }

  getAll(): void {
    this.statusService.getAll()
      .then(statusItemList => {
        this.retList = statusItemList;
        this.someStatusItemList = this.retList.map(this.makeDropDownItem);
      })
      .catch(error => {
        this.handleError(<any>error);
        let errMsg = `Не могу получить список из БД. Error code: ${error.status}, URL: ${error.url}`;
        alert(errMsg);
      });
  }


  makeDropDownItem(element): SelectItem {
    return {label: element.statusName, value: element};
  }

  ngOnInit(): void {

  }

  ngOnChanges(): void {
    // console.log("RefStatus is" + JSON.stringify(this.refStatus));
    if (this.refStatus) {
      this.someStatusItemList = [];
      this.statusUrl = this.refStatus.url;
      this.statusFullName = this.refStatus.descr;
      this.statusService.workUrl =`${this.startUrl }/${this.statusUrl}`;
      this.getAll();
    }
  }


  refresh() {
    this.getAll();
  }

  private handleError(error: any): String {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return error.message || error;
  }
}
