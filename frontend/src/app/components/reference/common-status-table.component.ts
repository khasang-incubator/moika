import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {SomeStatus} from "../../model/entities/some-status";
import {CrudService} from "../../model/services/crud.service";

@Component({
  selector: 'app-common-status-table',
  templateUrl: './common-status-table.component.html',
  styleUrls: ['./common-status-table.component.css']
})
export class CommonStatusTableComponent implements OnChanges, OnInit {

  @Input() refStatus: any;

  actionMsg: string = "...";
  someStatusList: SomeStatus[];
  statusRec: SomeStatus;
  isNewStatusRec: boolean;
  displayAddDialog: boolean;
  displayConfirmDialog: boolean;
  selectedStatus: SomeStatus;

  private statusUrl: string;
  private startUrl: string;
  private statusFullName: string;


  constructor(private statusService: CrudService<SomeStatus>) {
    this.startUrl = statusService.baseUrl;
  }

  getAll(): void {
    this.actionMsg = 'Обработка данных. Ждите...';
    this.statusService.getAll()
      .then(someStatusList => {
        this.someStatusList = someStatusList;
        this.actionMsg = 'Двойной клик для редактирования';
      })
      .catch(this.handleError);
  }

  ngOnInit(): void {
    this.statusRec = new SomeStatus();
  }

  ngOnChanges(): void {
    // console.log(this.refStatus);
    if (this.refStatus) {
      this.statusUrl = this.refStatus.url;
      this.statusFullName = this.refStatus.descr;
      console.log(this.statusFullName + " at " + this.statusUrl);
      this.statusService.workUrl =`${this.startUrl }/${this.statusUrl}`;
      this.getAll();
    }
  }

  showDialogToAdd() {
    this.isNewStatusRec = true;
    this.statusRec = new SomeStatus();
    this.displayAddDialog = true;
  }

  showConfirmDialog() {
    this.displayConfirmDialog = true;
  }

  save() {
    let tmpList = [...this.someStatusList];
    this.actionMsg = 'Обработка данных. Ждите...';
    if (this.isNewStatusRec) {
      this.statusService.createEntity(this.statusRec)
        .then(
          resRec => {
            //  console.log("Length before %d", tmpList.length);
            tmpList.push(resRec);
            //   console.log("Length after %d", tmpList.length);
            console.log("Just create rec ID: " + JSON.stringify(resRec));
            this.updateList(tmpList);
            this.actionMsg = 'Двойной клик для редактирования';
          })
        .catch(
          error => this.handleError(<any>error));
    } else {
      // tmpList[this.findSelectedStatusIdx()] = this.statusRec;
      this.statusService.updateEntity(this.statusRec)
        .then(
          resRec => {
            tmpList[this.findSelectedStatusIdx()] = resRec;
            this.updateList(tmpList);
            this.actionMsg = 'Двойной клик для редактирования';
          })
        .catch(
          error => this.handleError(<any>error));
    }
    this.displayAddDialog = false;
  }

  updateList(newLIst: SomeStatus[]) {
    //   console.log("Length someStatusList before %d", this.someStatusList.length);
    this.someStatusList = newLIst;
    //  console.log("Length  someStatusList after %d", this.someStatusList.length);
    this.statusRec = null;
  }

  delete() {
    let index = this.findSelectedStatusIdx();
    let id = this.selectedStatus.id;
    this.someStatusList = this.someStatusList.filter((val, i) => i != index);
    this.statusRec = null;
    this.displayConfirmDialog = false;
    console.log("About to delete ID: %d", id);
    if (!this.statusService.deleteEntity(id)) {
      let error = new Error();
      error.message = "Удаление невозможно"
      this.handleError(error);
    }
  }

  refresh() {
    this.getAll();
  }

  onRowDblclick(event) {
    this.isNewStatusRec = false;
    this.statusRec = this.cloneStatusRec(event.data);
    this.displayAddDialog = true;
  }

  cloneStatusRec(aStatus: SomeStatus): SomeStatus {
    let t = new SomeStatus();
    for (let prop in aStatus) {
      t[prop] = aStatus[prop];
    }
    return t;
  }

  findSelectedStatusIdx(): number {
    return this.someStatusList.indexOf(this.selectedStatus);
  }

  private handleError(error: any): String {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    this.actionMsg = 'Двойной клик для редактирования';
    alert(error.message);
    return error.message || error;
  }
}
