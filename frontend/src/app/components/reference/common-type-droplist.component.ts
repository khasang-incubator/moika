import {Component, Input, OnInit} from '@angular/core';
import {SomeType} from "../../model/entities/some-type";
import {CrudService} from "../../model/services/crud.service";
import {SelectItem} from "primeng/primeng";

@Component({
  selector: 'app-common-type-droplist',
  templateUrl: './common-type-droplist.component.html',
  styleUrls: ['./common-type-droplist.component.css']
})
export class CommonTypeDroplistComponent implements OnInit {

  @Input() refType: any;

  someTypeItemList: SelectItem[];
  retList : SomeType[];
  selectedType: SomeType;

  private typeUrl: string;
  private startUrl: string;
  private typeFullName: string;


  constructor(private typeService: CrudService<SomeType>) {
    this.startUrl = typeService.baseUrl;
  }

  getAll(): void {
    this.typeService.getAll()
      .then(typeItemList => {
        this.retList = typeItemList;
        this.someTypeItemList = this.retList.map(this.makeDropDownItem);
      })
      .catch(error => {
        this.handleError(<any>error);
        let errMsg = `Не могу получить список из БД. Error code: ${error.status}, URL: ${error.url}`;
        alert(errMsg);
      });
  }


  makeDropDownItem(element): SelectItem {
    return{label: element.typeName, value:element};
  }

  ngOnInit(): void {

  }

  ngOnChanges(): void {
   // console.log("RefType is" + JSON.stringify(this.refType));

    if (this.refType) {
      this.someTypeItemList = [];
      this.typeUrl = this.refType.url;
      this.typeFullName = this.refType.descr;
      this.typeService.workUrl =`${this.startUrl }/${this.typeUrl}`;
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
