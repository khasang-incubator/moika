import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent implements OnInit {
  @Input() displayLoginDialog: boolean ;

  user: string;
  pwd: string;
  interfaceMode: number = 2;


  constructor() { }

  ngOnInit() {
  }

  loginUser(){
    this.displayLoginDialog = false;
    console.log(`User ${this.user} logged in whit pwd ${this.pwd}`);
    switch (this.user){
      case "admin":
       this.interfaceMode = 0;

       break;
      case "user":
        this.interfaceMode = 1;
        break;
      default:
        this.interfaceMode = 2;
        break;
    }
  }
}
