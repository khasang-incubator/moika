import { Component, OnInit, Input } from '@angular/core';
import {WashFacility} from "../../model/entities/wash-facility";
import {WashFacilityService} from "app/model/services/wash-facility.service";
import {Address} from "../../model/entities/address";
import {WashBox} from "../../model/entities/wash-box";
import {Person} from "../../model/entities/person";
import {ActivatedRoute} from "@angular/router";
import {City} from "../../model/entities/city";

@Component({
  selector: 'app-wash-facility-table',
  templateUrl: './wash-facility-table.component.html',
  styleUrls: ['./wash-facility-table.component.css']
})
export class WashFacilityTableComponent implements OnInit {

  @Input() selectedCity: City;

  actionMsg: string = "...";
  washFcltList: WashFacility[];
  fcltAddr: Address;
  wasBoxes: WashBox[];
  manager: Person;
  fcltRec: WashFacility;
  isNewRec: boolean;
  displayAddDialog: boolean;
  displayConfirmDialog: boolean;
  selectedFclt: WashFacility;

  private typeUrl: string;
  private startUrl: string;
  private typeFullName: string;


  constructor(private activateRoute: ActivatedRoute,
    private fcltService: WashFacilityService) {
  }

  getAll(): void {
    this.actionMsg = 'Обработка данных. Ждите...';
    this.fcltService.getAll()
      .then(washFcltList => {
        this.washFcltList = washFcltList;
        this.actionMsg = 'Двойной клик для редактирования';
      })
      .catch(this.handleError);
  }

  ngOnInit(): void {
    console.log("Current route "+this.activateRoute.snapshot.url.toString());
    this.fcltRec = new WashFacility();
    this.getAll();
  }


  showDialogToAdd() {
    this.isNewRec = true;
    this.fcltRec = new WashFacility();
    this.displayAddDialog = true;
  }

  showConfirmDialog() {
    this.displayConfirmDialog = true;
  }

  save() {
    let tmpList = [...this.washFcltList];
    this.actionMsg = 'Обработка данных. Ждите...';
    if (this.isNewRec) {
      this.fcltService.createEntity(this.fcltRec)
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
      this.fcltService.updateEntity(this.fcltRec)
        .then(
          resRec => {
            tmpList[this.findSelectedFcltIdx()] = resRec;
            this.updateList(tmpList);
            this.actionMsg = 'Двойной клик для редактирования';
          })
        .catch(
          error => this.handleError(<any>error));
    }
    this.displayAddDialog = false;
  }

  updateList(newFcltList: WashFacility[]) {
    //   console.log("Length someTypeList before %d", this.someTypeList.length);
    this.washFcltList = newFcltList;
    //  console.log("Length  someTypeList after %d", this.someTypeList.length);
    this.fcltRec = null;
  }

  delete() {
    let index = this.findSelectedFcltIdx();
    let id = this.selectedFclt.id;
    this.displayConfirmDialog = false;
    console.log("About to delete ID: %d", id);
    if (this.fcltService.deleteEntity(id) ) { //удалили из БД
      this.washFcltList = this.washFcltList.filter((val, i) => i != index); //Удалили из view
      this.fcltRec = null;
    }
    else{
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
    this.fcltRec = this.cloneFcltRec(event.data);
    console.log(JSON.stringify(this.fcltRec));
    this.fcltAddr = this.fcltRec.facilityAddr;
    console.log(JSON.stringify(this.fcltAddr));
    this.displayAddDialog = true;
  }

  onCitySelect(aCity: City){
    console.log("Selected city "+ aCity.name);
    this.selectedCity = aCity;
  }

  cloneFcltRec(aFclt: WashFacility): WashFacility {
    let t = new WashFacility();
    for (let prop in aFclt) {
      t[prop] = aFclt[prop];
    }
    return t;
  }

  findSelectedFcltIdx(): number {
    return this.washFcltList.indexOf(this.selectedFclt);
  }

  private handleError(error: any): String {
    console.error('Не могу получить список моек из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    this.actionMsg = 'Двойной клик для редактирования';
    alert(error.message);
    return error.message || error;
  }

}
