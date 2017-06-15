import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class MenuToggleService {

  private menuSubj = new Subject<boolean>();

  constructor() { }

  hideMenu() {
    this.menuSubj.next( false );
  }

  showMenu() {
    this.menuSubj.next( true );
  }

  setMenuStatus( isShow: boolean) {
    this.menuSubj.next( isShow );
  }
  getMenuStatus(): Observable<boolean> {
    return this.menuSubj.asObservable();
  }
}
