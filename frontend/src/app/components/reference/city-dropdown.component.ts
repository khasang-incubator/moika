import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {City} from "../../model/entities/city";
import {SelectItem} from "primeng/primeng";
import {CrudService} from "../../model/services/crud.service";
import {AppSettings} from "../../model/collections/app-settings";

@Component({
  selector: 'app-city-dropdown',
  templateUrl: './city-dropdown.component.html',
  styleUrls: ['./city-dropdown.component.css']
})
export class CityDropdownComponent implements OnInit {
  @Input() curCity: string;
  @Input() selectedCity: City;
  @Output() selectionEvent : EventEmitter<City> = new EventEmitter<City>();

  private cityItemList: SelectItem[];
  private cityList : City[];
  private prevSelectedCity: City;

  private cityListUrl = AppSettings.backSiteUrl+'/washAddr/city/' ;

  constructor(private dbService: CrudService<City>){
    this.dbService.workUrl = this.cityListUrl ;
  }

  getAll(): void {
    this.dbService.getAll()
      .then(cityList => {
        this.cityList = cityList;
        this.cityItemList = this.cityList.map(this.makeDropDownItem);
      })
      .catch(error => {
        this.handleError(<any>error);
        let errMsg = `Не могу получить список из БД. Error code: ${error.status}, URL: ${error.url}`;
        alert(errMsg);
      });
  }


  makeDropDownItem(element): SelectItem {
    return{label: element.name, value:element};
  }

  ngOnInit(): void {
    this.getAll();
  }

  selectCity(){
    console.log(this.cityItemList);
    console.log(this.selectedCity);
    if ( this.prevSelectedCity !== this.selectedCity ) {
      this.prevSelectedCity = this.cloneCityRec(this.selectedCity);
      this.selectionEvent.emit(this.selectedCity);
    }
  }

  cloneCityRec(aCity: City): City {
    let t = new City();
    for (let prop in aCity) {
      t[prop] = aCity[prop];
    }
    return t;
  }


  private handleError(error: any): String {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return error.message || error;
  }


}
