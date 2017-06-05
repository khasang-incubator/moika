import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {InterfaceMode} from "./model/entities/interface-enum";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnChanges, OnInit {
  //InterfaceMode : InterfaceMode;
  // interfaceMode : InterfaceMode.user;
  // TODO разобраться c ENUM в шаблонах
  @Input() interfaceMode = 0;
           title: string;


  ngOnInit(): void {
    this.interfaceMode = 0;
    this.title = 'Интерфейс администратора';
  }

  ngOnChanges(changes: SimpleChanges): void {
    switch (this.interfaceMode){
      case 0:
       this.title = 'Интерфейс администратора';
        break;
      case 1:
        this.title = 'Интерфейс пользователя'
        break;
      default:
        this.title = 'Интерфейс клиента'
        break;
    }
  }
}

