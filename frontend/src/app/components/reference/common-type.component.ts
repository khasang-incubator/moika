import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {SomeType} from "../../model/entities/some-type";
import {CrudService} from "../../model/services/crud.service";

@Component({
  selector: 'app-common-type',
  templateUrl: './common-type.component.html',
  styleUrls: ['./common-type.component.css']
})
export class CommonTypeComponent implements OnChanges, OnInit {

  @Input() refType: any;

  someTypeList: SomeType[];
  typeRec: SomeType;
  isNewTypeRec: boolean;
  displayAddDialog: boolean;
  displayConfirmDialog: boolean;
  selectedType: SomeType;

  private typeUrl: string;
  private startUrl: string;
  private typeFullName: string;


  constructor(private typeService: CrudService<SomeType>) {
    this.startUrl = typeService.getBaseUrl();
  }

  getAll(): void {
    this.typeService.getAll()
      .then(someTypeList => this.someTypeList = someTypeList)
      .catch(this.handleError);
  }

  ngOnInit(): void {
    this.typeRec = new SomeType();
  }

  ngOnChanges(): void {
    console.log(this.refType);
    if (this.refType) {
      this.typeUrl = this.refType.url;
      this.typeFullName = this.refType.descr;
      console.log(this.typeFullName + " at " + this.typeUrl);
      this.typeService.setBaseUrl(`${this.startUrl }/${this.typeUrl}`);
      this.getAll();
    }
  }

  showDialogToAdd() {
    this.isNewTypeRec = true;
    this.typeRec = new SomeType();
    this.displayAddDialog = true;
  }

  showConfirmDialog() {
    this.displayConfirmDialog = true;
  }

  save() {
    let tmpList = [...this.someTypeList];
    if (this.isNewTypeRec) {
      this.typeService.createEntity(this.typeRec)
        .then(
           resRec => {
             console.log("Length before %d", tmpList.length);
             tmpList.push(resRec);
             console.log("Length after %d", tmpList.length);
             console.log("Just create rec ID: " + JSON.stringify(resRec));
           } )
        .catch(
          error => this.handleError(<any>error));
    } else {
      // tmpList[this.findSelectedTypeIdx()] = this.typeRec;
      this.typeService.updateEntity(this.typeRec)
        .then(
          resRec => tmpList[this.findSelectedTypeIdx()] = resRec)
        .catch(
          error => this.handleError(<any>error));
    }
    console.log("Length someTypeList before %d", this.someTypeList.length);
    this.someTypeList = tmpList;
    console.log("Length  someTypeList after %d", this.someTypeList.length);
    this.typeRec = null;
    this.displayAddDialog = false;
  }


  delete() {
    let index = this.findSelectedTypeIdx();
    let id = this.selectedType.id;
    this.someTypeList = this.someTypeList.filter((val, i) => i != index);
    this.typeRec = null;
    this.displayConfirmDialog = false;
    console.log("About to delete ID: %d", id);
    this.typeService.deleteEntity(id);
  }


  onRowDblclick(event) {
    this.isNewTypeRec = false;
    this.typeRec = this.cloneTypeRec(event.data);
    this.displayAddDialog = true;
  }

  cloneTypeRec(aType: SomeType): SomeType {
    let t = new SomeType();
    for (let prop in aType) {
      t[prop] = aType[prop];
    }
    return t;
  }

  findSelectedTypeIdx(): number {
    return this.someTypeList.indexOf(this.selectedType);
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
