import {Component, OnInit} from '@angular/core';
import {TieredMenuModule, MenuItem} from 'primeng/primeng';
import {MockMoikaObjectService} from "../../../../model/services/mock-moika-objects.service";
import {Router} from "@angular/router";

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
  private showLoginDialog = false;


  constructor(private objectService: MockMoikaObjectService,
              private router: Router) {
  }


  ngOnInit() {
    this.prepareRefTypeItems();
    this.prepareRefStatusItems();
    this.prepareReportItems();
    this.prepareMainItems();
  }

  /**
   * Готовим список пунктов под-меню для всяческих типов
   */
  private prepareRefTypeItems(): void {
    this.objectService.getTypeRefs().then(
      typeItemList =>
        this.refTypeItems = typeItemList.map(this.mapItems)
    )
  }

  mapItems(element): MenuItem {
    return {label: element.name};
  }

  /**
   * Готовим список пунктов под-меню для всяческих статусов
   */
  private prepareRefStatusItems(): void {
    this.objectService.getStatusRefs().then(
      typeItemList =>
        this.refTypeItems = typeItemList.map(this.mapItems)
    )
  }

  private aboutClick(): void{
    this.router.navigate(['/about']);
  }

  /**
   * Готовим список пунктов под-меню для всяческих отчетов
   */
  private prepareReportItems(): void {
    this.reportItems = [
      {
        label: 'Клиенты',
      },
      {
        label: 'Работы'
      }
    ]
  }


  /**
   * Готовим список пунктов пменю
   */

  private prepareMainItems(): void {
    this.items = [
      {
        label: 'Мойки',
        icon: 'fa-car',
        items: [{
          label: 'Carousel',
          icon: 'fa-th-large',
          command: (click) => {
            this.router.navigate(['/facilitiesDash']);
          }
        }, {
          label: 'Table',
          icon: 'fa-table',
          command: (click) => {
            this.router.navigate(['/facilitiesTable']);
          }
        },{
          label: 'List',
          icon: ' fa-list-ol ',
          command: (click) => {
            this.router.navigate(['/washFacilitiesList']);
          }
        }]
      },
      {
        label: 'Справочники',
        icon: 'fa-edit',
        items: [{
          label: 'Справочники типов',
          items: this.refTypeItems,
          command: (click) => {
            this.router.navigate(['/typeRefs']);
          }
        },
          {
            label: 'Справочники статусов',
            items: this.refStatusItems,
            command: (click) => {
              this.router.navigate(['/statusRefs']);
            }
          }]
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
