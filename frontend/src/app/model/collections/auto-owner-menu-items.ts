import {MenuItem} from "primeng/primeng";
import {Router} from "@angular/router";
import {MockMoikaObjectService} from "app/model/services/mock-moika-objects.service";
import {Injectable} from "@angular/core";

export class AutoOwnerMenuItems {
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
          label: 'Записаться',
          icon: 'fa-car',
          command: (click) => {
            this.router.navigate(['/auto-owner/reserve-order']);}
        },
        {
          label: 'Для проб',
          icon: 'fa-car',
          command: (click) => {
            this.router.navigate(['auto-owner/for-samples']);}
        },
      {
        label: 'О программе',
        icon: 'fa-commenting-o ',
        command: (click) => {
          this.router.navigate(['/about']);}
      },
      {
        label: 'Отзывы и предложения',
        icon: 'fa-envelope-open-o ',
        command: (click) => {
          this.router.navigate(['/about']);}
      },
      {
        label: 'Вход в личный кабинет',
        icon: 'fa-sign-in',
        command: (click) => {
          }
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
