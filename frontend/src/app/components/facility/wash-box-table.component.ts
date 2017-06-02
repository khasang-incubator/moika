import {Component, Input, OnInit} from '@angular/core';
import {WashBox} from "../../model/entities/wash-box";
import {WashBoxService} from "../../model/services/wash-box.service";
import {ActivatedRoute} from "@angular/router";
import {SomeStatus} from "../../model/entities/some-status";
import {SomeType} from "../../model/entities/some-type";
import {MoikaObject} from "../../model/entities/moika-object";
import {MockMoikaObjects} from "../../model/entities/mock-moika-objects";

@Component({
  selector: 'app-wash-box-table',
  templateUrl: './wash-box-table.component.html',
  styleUrls: ['./wash-box-table.component.css']
})
export class WashBoxTableComponent implements OnInit {

  @Input() washBoxList: Array<WashBox> = [];
  selectedBox: WashBox;
  boxRec: WashBox;
  refType: MoikaObject;
  refStatus: MoikaObject;

  actionMsg: string = "...";

  isNewRec: boolean;
  displayAddDialog: boolean;
  displayConfirmDialog: boolean;


  constructor(private activateRoute: ActivatedRoute,
              private washBoxService: WashBoxService) {
    const objList = new MockMoikaObjects();
    this.refType = objList.getObject('boxType');
    this.refStatus = objList.getObject('boxStatus');
  }

  getAll(): void {
    this.actionMsg = 'Обработка данных. Ждите...';
    this.washBoxService.getAll()
      .then(washBoxList => {
        this.washBoxList = washBoxList;
        this.actionMsg = 'Двойной клик для редактирования';
      })
      .catch(this.handleError);
  }

  ngOnInit(): void {
    console.log("Current route "+this.activateRoute.snapshot.url.toString());
    this.boxRec = new WashBox();
   // this.getAll();
  }


  showDialogToAdd() {
    this.isNewRec = true;
    this.boxRec = new WashBox();
    this.displayAddDialog = true;
  }

  showConfirmDialog() {
    this.displayConfirmDialog = true;
  }

  save() {
    let tmpList = [...this.washBoxList];
    this.actionMsg = 'Обработка данных. Ждите...';
    if (this.isNewRec) {
      this.washBoxService.createEntity(this.boxRec)
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
      // tmpList[this.findSelectedTypeIdx()] = this.typeRec;
      this.washBoxService.updateEntity(this.boxRec)
        .then(
          resRec => {
            tmpList[this.findSelectedBoxIdx()] = resRec;
            this.updateList(tmpList);
            this.actionMsg = 'Двойной клик для редактирования';
          })
        .catch(
          error => this.handleError(<any>error));
    }
    this.displayAddDialog = false;
  }

  updateList(newBoxList: WashBox[]) {
    //   console.log("Length someTypeList before %d", this.someTypeList.length);
    this.washBoxList = newBoxList;
    //  console.log("Length  someTypeList after %d", this.someTypeList.length);
    this.boxRec = null;
  }

  delete() {
    let index = this.findSelectedBoxIdx();
    let id = this.selectedBox.id;
    this.displayConfirmDialog = false;
    console.log("About to delete ID: %d", id);
    if (this.washBoxService.deleteEntity(id) ) { //удалили из БД
      this.washBoxList = this.washBoxList.filter((val, i) => i != index); //удалили из view
      this.boxRec = null;
    }
     else {
      let error = new Error();
      error.message = "Удаление невозможно"
      this.handleError(error);
    }
  }

  refresh(){
    this.getAll();
  }

  onRowDblclick(event) {
    this.isNewRec = false;
    this.boxRec = this.cloneBoxRec(event.data);
    this.displayAddDialog = true;
  }

  cloneBoxRec(aBox: WashBox): WashBox {
    let t = new WashBox();
    for (let prop in aBox) {
      t[prop] = aBox[prop];
    }
    return t;
  }

  findSelectedBoxIdx(): number {
    return this.washBoxList.indexOf(this.selectedBox);
  }

  private handleError(error: any): String {
    console.error('Не могу получить список моек из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    this.actionMsg = 'Двойной клик для редактирования';
    alert(error.message);
    return error.message || error;
  }
}
