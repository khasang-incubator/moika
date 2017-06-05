import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/primeng";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customer-navbar',
  templateUrl: './customer-navbar.component.html',
  styleUrls: ['./customer-navbar.component.css']
})
export class CustomerNavbarComponent implements OnInit {


  private items: MenuItem[];
  private showLoginDialog = false;


  constructor(private router: Router) {
  }


  ngOnInit() {
    this.prepareMainItems();
  }


  mapItems(element): MenuItem {
    return {label: element.name};
  }


  private aboutClick(): void{
    this.router.navigate(['/about']);
  }

  /**
   * Готовим список пунктов пменю
   */

  private prepareMainItems(): void {
    this.items = [
      {
        label: 'Записаться',
        icon: 'fa-car',
        command: (click) => {
          this.router.navigate(['/facilitiesDash']);}
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
        label: 'Вход',
        icon: 'fa-sign-in',
        command: (click) => {
          this.showLoginDialog=true;}
      },
     ];
  }
}
