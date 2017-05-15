import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {SomeType} from "../../model/entities/some-type";
import {CrudService} from "../../model/services/crud.service";

@Component({
  selector: 'app-common-type',
  templateUrl: './common-type.component.html',
  styleUrls: ['./common-type.component.css']
})
export class CommonTypeComponent implements OnChanges{

  @Input() refType: any;

  someTypeList: SomeType[];
  private typeUrl: string;
  private startUrl: string;
  private typeFullName: string;

  constructor(private typeService: CrudService<SomeType>) {
     this.startUrl = typeService.getBaseUrl();
  }

  getAll(): void {
    this.typeService.getAll().then(someTypeList => this.someTypeList = someTypeList).catch(this.handleError);
  }


  ngOnChanges(): void {
    console.log(this.refType);
    if (this.refType) {
      this.typeUrl = this.refType.url;
      this.typeFullName = this.refType.descr;
      console.log(this.typeFullName +" at "+this.typeUrl);
      this.typeService.setBaseUrl(`${this.startUrl }/${this.typeUrl}`);
      this.getAll();
    }
  }


  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
