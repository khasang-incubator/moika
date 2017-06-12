import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/primeng';
import {Router} from "@angular/router";


@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent  {
  private items: MenuItem[];
  constructor(private router: Router) {
  }
  ngOnInit() {
    this.items = [ // все меню
      {//Клиентам
        label: 'Клиентам',
        items: [{
          label: 'Пункт1',
          icon: 'fa-plus',
          items: [
            {label: 'подпункт1-1'},
            {label: 'подпункт1-1'},
          ]
        },
          {label: 'Пункт2'},
          {label: 'Пункт3'}
        ]
      }, //клиентам
        {
         label: 'Менеджерам',
         icon: 'fa-edit',
        items: []
       },
       {label: 'Владельцам'},
       {label: 'Администраторам'},
       {
        label: 'Разработка',
        items: [
          {label: 'Страница Любарева'},
          {label: 'Страница Мадорина',routerLink: ['/dev-madv']},
          {label: 'Страница Скворцова'}
        ]
      }
    ];
  }
//
}
