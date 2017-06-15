import {Component, Input, OnInit} from '@angular/core';
import {InterfaceSwitchingService} from "../../../model/services/interface-switching.service";

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent implements OnInit {
  @Input() displayLoginDialog: boolean ;

  user: string;
  pwd: string;
  interfaceMode: number = 0;

  constructor(private interfaceSwitcher: InterfaceSwitchingService) { }

  ngOnInit() {
  }

  loginUser(){
    this.displayLoginDialog = false;
    console.log(`User ${this.user} logged in with pwd ${this.pwd}`);
    //TODO сделать системы регистрации и заменить обманку
    switch (this.user){
      case "client":
        this.interfaceMode = 1;
        break;
      case "owner":
        this.interfaceMode = 2;
        break;
      case "admin":
       this.interfaceMode = 3;
       break;
      case "user":
        this.interfaceMode = 4;
        break;
      default:
        this.interfaceMode = 0;
        break;
    }
    console.log(`Interface switched to ${this.interfaceMode}`);
    this.interfaceSwitcher.setInterface(this.interfaceMode );
  }
}
