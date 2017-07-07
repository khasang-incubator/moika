import {MenuItem} from "primeng/primeng";
import {Router} from "@angular/router";
import {MockMoikaObjectService} from "app/model/services/mock-moika-objects.service";
import {Injectable} from "@angular/core";

export class FcltOwnerMenuItems {
  private _items: MenuItem[];

  constructor(private objectService: MockMoikaObjectService, private router: Router) {
    this.prepareMainItems();
  }


  mapItems(element): MenuItem {
    return {label: element.name};
  }


  get items(): MenuItem[] {
    return this._items;
  }


  /**
   * Готовим полный список пунктов пменю
   */
  private prepareMainItems(): void {
    this._items = [
      {
        label: 'Мойки',
        icon: 'fa-car',
        command: (click) => {
          this.router.navigate(['/fclt/facilitiesDash']);}
      },
      {
        label: 'Услуги',
        icon: 'fa-commenting-o ',
        command: (click) => {
          this.router.navigate(['/about']);}
      },
      {
        label: 'Финансы',
        icon: 'fa-commenting-o ',
        command: (click) => {
          this.router.navigate(['/about']);}
      },
      {
        label: 'Отчеты',
        icon: 'fa-envelope-open-o ',
        command: (click) => {
          this.router.navigate(['/about']);}
      },
      {
        label: 'Выход',
        icon: 'fa-sign-in',
        command: (click) => {
          window.close();}
      },
    ];
  }

}
