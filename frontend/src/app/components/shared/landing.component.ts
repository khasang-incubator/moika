import {Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {InterfaceSwitchingService} from "../../model/services/interface-switching.service";
import {MenuToggleService} from "../../model/services/menu-toggle.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit, OnDestroy {
  private interfaceMode: number = 0;
  private menuShowed: boolean = false;
  private title: string;
  interfaceSubscription: Subscription;
  menuSubscriptiomn: Subscription;

  constructor(private router: Router,
              private interfaceSwitcher: InterfaceSwitchingService,
              private menuToggler: MenuToggleService) {
    this.interfaceSubscription = this.interfaceSwitcher.getInterface().subscribe(mode => this.onInterfaceChanges(mode));
    this.menuSubscriptiomn = this.menuToggler.getMenuStatus().subscribe(status => { this.menuShowed = status; })
  }

  ngOnInit(): void {
  }

  onInterfaceChanges(mode: number): void {
    this.interfaceMode = mode;
    switch (this.interfaceMode) {
      case 0:
        this.title = 'Интерфейс по-умолчанию';
        break;
      case 1:
        this.title = 'Интерфейс автовладельца';
        break;
      case 2:
        this.title = 'Интерфейс владельца автомойки';
        break;
      case 3:
        this.title = 'Интерфейс администратора';
        break;
      case 4:
        this.title = 'Интерфейс пользователя/манагера';
        break;
      default:
        this.title = 'Интерфейс по-умолчанию';
        break;
    }
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.interfaceSubscription.unsubscribe();
    this.menuSubscriptiomn.unsubscribe();
  }
}
