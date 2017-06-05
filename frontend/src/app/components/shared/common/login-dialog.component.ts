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


  constructor() { }

  ngOnInit() {
  }

  loginUser(){
    this.displayLoginDialog = false;
    console.log(`User ${this.user} logged in whit pwd ${this.pwd}`);
  }
}
