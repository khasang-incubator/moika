import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {SomeType} from "../../model/entities/some-type";
import {CrudService} from "../../model/services/crud.service";

@Component({
  selector: 'app-common-type-table',
  templateUrl: './common-type-table.component.html',
  styleUrls: ['./common-type-table.component.css']
})
export class CommonTypeComponent implements OnChanges, OnInit {

  @Input() refType: any;

  actionMsg: string = "...";
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
    this.actionMsg = 'Обработка данных. Ждите...';
    this.typeService.getAll()
      .then(someTypeList => {
        this.someTypeList = someTypeList;
        this.actionMsg = 'Двойной клик для редактирования';
      })
      .catch(this.handleError);
  }

  ngOnInit(): void {
    this.typeRec = new SomeType();
  }

  ngOnChanges(): void {
   // console.log(this.refType);
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
    this.actionMsg = 'Обработка данных. Ждите...';
    if (this.isNewTypeRec) {
      this.typeService.createEntity(this.typeRec)
        .then(
          resRec => {
            console.log("Length before %d", tmpList.length);
            tmpList.push(resRec);
            console.log("Length after %d", tmpList.length);
            console.log("Just create rec ID: " + JSON.stringify(resRec));
            this.updateList(tmpList);
            this.actionMsg = 'Двойной клик для редактирования';
          })
        .catch(
          error => this.handleError(<any>error));
    } else {
      // tmpList[this.findSelectedTypeIdx()] = this.typeRec;
      this.typeService.updateEntity(this.typeRec)
        .then(
          resRec => {
            tmpList[this.findSelectedTypeIdx()] = resRec;
            this.updateList(tmpList);
            this.actionMsg = 'Двойной клик для редактирования';
          })
        .catch(
          error => this.handleError(<any>error));
    }
    this.displayAddDialog = false;
  }

  updateList(newLIst: SomeType[]) {
    console.log("Length someTypeList before %d", this.someTypeList.length);
    this.someTypeList = newLIst;
    console.log("Length  someTypeList after %d", this.someTypeList.length);
    this.typeRec = null;
  }

  delete() {
    let index = this.findSelectedTypeIdx();
    let id = this.selectedType.id;
    this.someTypeList = this.someTypeList.filter((val, i) => i != index);
    this.typeRec = null;
    this.displayConfirmDialog = false;
    console.log("About to delete ID: %d", id);
    if (!this.typeService.deleteEntity(id) ){
      let error = new Error();
      error.message = "Удаление невозможно"
      this.handleError(error);
    }
  }

  refresh(){
    this.getAll();
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

  private handleError(error: any): String {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    this.actionMsg = 'Двойной клик для редактирования';
    alert(error.message);
    return error.message || error;
  }
}
