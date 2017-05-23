import { Component, OnInit } from '@angular/core';
import {TieredMenuModule,MenuItem} from 'primeng/primeng';
import {MockMoikaObjectService} from "../../../../model/services/mock-moika-objects.service";

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent implements OnInit {


  private items: MenuItem[];
  private refTypeItems: MenuItem[];
  private refStatusItems: MenuItem[];
  private reportItems: MenuItem[];


  constructor(private objectService: MockMoikaObjectService) { }


  ngOnInit() {
    this.prepareRefTypeItems();
    this. prepareRefStatusItems();
    this.prepareReportItems();
    this.prepareMainItems();
  }


  private prepareRefTypeItems(): void {
    this.objectService.getTypeRefs().then(
      typeItemList =>
        this.refTypeItems = typeItemList.map(this.mapItems)
    )
  }

  mapItems(element): MenuItem {
    return {label: element.name};
  }

  private prepareRefStatusItems() : void {
    this.objectService.getStatusRefs().then(
      typeItemList =>
        this.refTypeItems = typeItemList.map(this.mapItems)
    )
  }

  private prepareReportItems() : void {
    this.reportItems = [
      {
        label: 'Клиенты',
      },
      {
        label: 'Работы'
      }
    ]
  }

  private prepareMainItems() : void{
    this.items = [
      {
        label: 'Мойки',
        icon: 'fa-car',
        url:'/washFacilities'
      },
      {
        label: 'Справочники',
        icon: 'fa-edit',
        items: [{
          label: 'Справочники типов',
          items:this.refTypeItems
        },
        {
            label: 'Справочники статусов',
            items:this.refStatusItems
        }
        ]
      },
      {
        label: 'Пользователи',
        icon: 'fa-users',
      },
      {
        label: 'Отчеты',
        icon: 'fa-bar-chart',
        items: this.reportItems
      },
      {
        label: 'Выход',
        icon: 'fa-sign-out',
      },
    ];
  }
}
