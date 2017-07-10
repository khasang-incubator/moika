import {MenuItem} from "primeng/primeng";
import {Router} from "@angular/router";
import {MockMoikaObjectService} from "app/model/services/mock-moika-objects.service";
import {Injectable} from "@angular/core";

export class AdminMenuItems {
  private _items: MenuItem[];
  private refTypeItems: MenuItem[];
  private refStatusItems: MenuItem[];
  private reportItems: MenuItem[];

  constructor(private objectService: MockMoikaObjectService, private router: Router) {
      this.prepareRefTypeItems();
      this.prepareRefStatusItems();
      this.prepareReportItems();
      this.prepareMainItems();
  }


  mapItems(element): MenuItem {
    return {label: element.name};
  }


  get items(): MenuItem[] {
    return this._items;
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

  /**
   * Готовим список пунктов под-меню для всяческих статусов
   */
  private prepareRefStatusItems(): void {
    this.objectService.getStatusRefs().then(
      typeItemList =>
        this.refTypeItems = typeItemList.map(this.mapItems)
    )
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
   * Готовим полный список пунктов пменю
   */
  prepareMainItems() {
    this._items = [
      {
        label: 'Мойки',
        icon: 'fa-car',
        items: [{
          label: 'Carousel',
          icon: 'fa-th-large',
          command: (click) => {
            this.router.navigate(['facilitiesDash']);
          }
        }, {
          label: 'Table',
          icon: 'fa-table',
          command: (click) => {
            this.router.navigate(['facilitiesTable']);
          }
        }, {
          label: 'List',
          icon: ' fa-list-ol ',
          command: (click) => {
            this.router.navigate(['washFacilitiesList']);
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
        command: (click) => {
          window.close();;
        }
      },
    ];
  }

}
