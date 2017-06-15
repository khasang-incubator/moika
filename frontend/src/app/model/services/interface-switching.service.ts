import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class InterfaceSwitchingService {
  private interfaceSubj = new Subject<number>();

  constructor() { }

  setInterface(interfaceNum: number) {
    this.interfaceSubj.next( interfaceNum );
  }

  defaulInterface() {
    this.interfaceSubj.next(0);
  }

  getInterface(): Observable<number> {
    return this.interfaceSubj.asObservable();
  }

}
