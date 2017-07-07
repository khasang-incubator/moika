import {Component, Input, OnInit, Output} from '@angular/core';
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
  @Output() @Input()  selectedCity: City;


  private cityItemList: SelectItem[];
  private cityList : City[];

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

  private handleError(error: any): String {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return error.message || error;
  }


}
