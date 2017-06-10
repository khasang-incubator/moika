import { Component, OnInit } from '@angular/core';
import {ButtonModule} from 'primeng/primeng';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  text: string ='Поиск';
  clicks: number = 0;
  search() {
    this.clicks++;
  }
  constructor() { }

  ngOnInit() {
  }

}
