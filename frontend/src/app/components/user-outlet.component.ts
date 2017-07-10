import {Component} from '@angular/core';

@Component({
  selector: 'app-user-outlet',
  template: `
    <nav>
      <a routerLink="about" routerLinkActive="active"></a>
      <a routerLink="fclt" routerLinkActive="active"></a>
      <a routerLink="facilitiesDash" routerLinkActive="active"></a>
      <a routerLink="facilitiesTable" routerLinkActive="active"></a>
      <a routerLink="washFacilitiesList" routerLinkActive="active"></a>   
    </nav>
    <router-outlet></router-outlet>`
})
export class UserOutletComponent {


}
