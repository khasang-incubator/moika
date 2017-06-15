import {Component, OnInit} from '@angular/core';
import {PanelMenuModule, MenuItem} from 'primeng/primeng';
import {MockMoikaObjectService} from "../../../model/services/mock-moika-objects.service";
import {Router} from "@angular/router";
import {AdminMenuItems} from "../../../model/collections/admin-menu-items";
import {InterfaceSwitchingService} from "../../../model/services/interface-switching.service";
import {Subscription} from "rxjs/Subscription";
import {DefaultMenuItems} from "app/model/collections/default-menu-items";
import {UserMenuItems} from "../../../model/collections/user-menu-items";
import {AutoOwnerMenuItems} from "../../../model/collections/auto-owner-menu-items";
import {FcltOwnerMenuItems} from "../../../model/collections/fclt-owner-menu-items";


@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {

  private interfaceMode: number = 0;
  private items: MenuItem[];
  private adminMenuItems: AdminMenuItems;
  private defaultMenuItems: DefaultMenuItems;
  private userMenuItems: UserMenuItems;
  private autoOwnerMenuItems: AutoOwnerMenuItems;
  private fcltOwnerMenuItems: FcltOwnerMenuItems;
  private interfaceSubscription: Subscription;


  constructor(private objectService: MockMoikaObjectService,
              private router: Router,
              private interfaceSwitcher: InterfaceSwitchingService) {
    // Сразу инициализируем необходимые наботы меню под разные интерфейсы
    this.adminMenuItems = new AdminMenuItems(objectService, router);
    this.defaultMenuItems = new DefaultMenuItems(objectService, router);
    this.userMenuItems = new UserMenuItems(objectService, router);
    this.autoOwnerMenuItems = new AutoOwnerMenuItems(objectService, router);
    this.fcltOwnerMenuItems = new FcltOwnerMenuItems(objectService, router);
    this.interfaceSubscription = this.interfaceSwitcher.getInterface().subscribe(mode => this.onInterfaceChanges(mode));
  }


  ngOnInit() {
    this.items = this.adminMenuItems.items;
  }

  onInterfaceChanges(mode: number): void {
    console.log(`MainMenuComponent. Interface switched to ${mode}`);
    this.interfaceMode = mode;
    switch (this.interfaceMode) {
      case 0:
        this.items = this.defaultMenuItems.items;
        break;
      case 1:
        this.items = this.autoOwnerMenuItems.items;
        break;
      case 2:
        this.items = this.fcltOwnerMenuItems.items;
        break;
      case 3:
        this.items = this.adminMenuItems.items;
        break;
      case 4:
        this.items = this.userMenuItems.items;
        break;
      default:
        this.items = this.defaultMenuItems.items;
        break;
    }
  }
}
