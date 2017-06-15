import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {InterfaceSwitchingService} from "../../../model/services/interface-switching.service";
import {MenuToggleService} from "../../../model/services/menu-toggle.service";


@Component({
  selector: 'app-common-navbar',
  templateUrl: './common-navbar.component.html',
  styleUrls: ['./common-navbar.component.css']
})
export class CommonNavbarComponent implements OnInit {

  private showLoginDialog = false;
  private interfaceSelected: number = 0;
  private menuShowed: boolean = false;


  constructor(
    private interfaceSwitcher: InterfaceSwitchingService,
    private menuToggler: MenuToggleService
  ) {

  }

  ngOnInit() {
    this.interfaceSelected = 0;
    this.interfaceSwitcher.setInterface(this.interfaceSelected );
    this.menuToggler.hideMenu();
    console.log("Interface %d, Menu status %s ", this.interfaceSelected, this.menuShowed );
   // this.router.navigate(['/about']);
  }

  menuClick(){
    this.menuShowed = !this.menuShowed;
    this.menuToggler.setMenuStatus( this.menuShowed);
    console.log("Interface %d, Menu status %s ", this.interfaceSelected, this.menuShowed );
    //this.router.navigate(['/adminMenu']);
  }

  homeClick() {
    this.interfaceSelected = 0;
    this.interfaceSwitcher.setInterface(this.interfaceSelected );
    console.log("Interface %d, Menu status %s ", this.interfaceSelected, this.menuShowed );
  //  this.router.navigate(['/home']);
  }

  autoOwnerClick() {
    this.interfaceSelected = 1;
    this.interfaceSwitcher.setInterface(this.interfaceSelected );
    console.log("Interfase %d, Menu status %s ", this.interfaceSelected, this.menuShowed );
  //  this.router.navigate(['/autoOwnerHome']);
  }

  fcltOwnerClick() {
    this.interfaceSelected = 2;
    this.interfaceSwitcher.setInterface(this.interfaceSelected );
    console.log("Interfase %d, Menu status %s ", this.interfaceSelected, this.menuShowed );
  // this.router.navigate(['/fcltOwnerHome']);
  }

  loginDialogShow(){
    this.showLoginDialog=true;
    //TODO поменять на реальное значение после регистрации пользователя
    this.interfaceSelected = 3;
  }

}
